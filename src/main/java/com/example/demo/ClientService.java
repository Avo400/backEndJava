package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class ClientService {
	private ClientRepository clientRepository;
	private CommandeRepository commandeRepository;

	public ClientService(ClientRepository clientRepository, CommandeRepository commandeRepository) {
		this.clientRepository = clientRepository;
		this.commandeRepository = commandeRepository;
	}

	public List<Client> findAll() {
		return (List<Client>) clientRepository.findAll();
	}

	public void insert(Client client) {

		System.out.println("- Ajout d'une client----------");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(client);
			trans.commit();

			List<Client> clients = entityManager.createQuery("from Client", Client.class).getResultList();
			for (Client client1 : clients) {
				clientRepository.save(client1);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (entityManager != null)
				entityManager.close();
			if (entityManagerFactory != null)
				entityManagerFactory.close();
		}

	}

	public void update(Client clientUpd) {
		System.out.println("- Modification d'un client ----------");
		System.out.println("clientUpd=" + clientUpd);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Client client = clientRepository.findById(clientUpd.getId()).orElse(null);
		if (client != null) {

			Client realClient = client;
			realClient.setName(clientUpd.getName());
			realClient.setFirstName(clientUpd.getFirstName());
			realClient.setBirthDate(clientUpd.getBirthDate());
			realClient.setAdresse(clientUpd.getAdresse());
			System.out.println("realClient=" + realClient);
			System.out.println("client=" + client);
			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();

				entityManager.merge(clientUpd);

				trans.commit();

				clientRepository.save(realClient);

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				if (entityManager != null)
					entityManager.close();
				if (entityManagerFactory != null)
					entityManagerFactory.close();
			}

		}

	}

	public void delete(Client clientDelete) {
		System.out.println("- Delete d'un client ----------");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Client client = clientRepository.findById(clientDelete.getId()).orElse(null);
		if (client != null) {
			System.out.println("- Delete FOUND d'un client ----------");

			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();
				entityManager.remove(entityManager.merge(client));

				trans.commit();
				clientRepository.delete(client);

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				if (entityManager != null)
					entityManager.close();
				if (entityManagerFactory != null)
					entityManagerFactory.close();
			}

		}

	}

	public int getCommandeNumber(long clientId) {
		List<Commande> commandes = (List<Commande>) commandeRepository.findAll();
		int commandeNumber = 0;
		for (int i = 0; i < commandes.size(); i++) {
			if (commandes.get(i).getClient().getId() == clientId) {
				commandeNumber += 1;
			}
		}
		return commandeNumber - 1;
	}

	public DataResult getCommandeDiscount(long commandeNumber) {
		String remise = "";
		int intRemise = 0;

		if (commandeNumber >= 1 && commandeNumber < 5) {
			remise = "10%";
			intRemise = 10;

		} else if (commandeNumber >= 5 && commandeNumber < 10) {
			remise = "20%";
			intRemise = 20;

		} else if (commandeNumber >= 10) {
			remise = "30%";
			intRemise = 30;
		}
		String discountMessage = "Vous avez une remise de " + remise + " sur votre prochaine commande";
		if (remise.equals("")) {
			return new DataResult("", intRemise);
		} else {
			return new DataResult(discountMessage, intRemise);
		}

	}

	public DataResult getDiscountMessageByClientId(long clientId) {
		DataResult dataResult = getCommandeDiscount(getCommandeNumber(clientId));
		return dataResult;
	}

	public void refreshRepository() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		List<Client> results = entityManager.createQuery("from Client", Client.class).getResultList();
		clientRepository.deleteAll();
		for (Client result : results) {
			System.out.println(result);
			clientRepository.save(result);
		}
	}
}
