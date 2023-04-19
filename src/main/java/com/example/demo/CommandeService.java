package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class CommandeService {

	private CommandeRepository commandeRepository;

	public CommandeService(CommandeRepository commandeRepository) {
		this.commandeRepository = commandeRepository;
	}

	public List<Commande> findAll() {
		return (List<Commande>) commandeRepository.findAll();
	}

	public void insert(Commande commande) {

		System.out.println("- Ajout d'une commande----------");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(commande);
			trans.commit();

			List<Commande> commandes = entityManager.createQuery("from Commande", Commande.class).getResultList();
			for (Commande commande1 : commandes) {
				commandeRepository.save(commande1);
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

	public long insertGetCommandeId(Commande commande) {

		System.out.println("- Ajout d'une commande----------");
		long maxId = 0;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(commande);
			trans.commit();

			List<Commande> commandes = entityManager.createQuery("from Commande", Commande.class).getResultList();

			for (Commande commande1 : commandes) {
				commandeRepository.save(commande1);
				if (commande1.getId() > maxId) {
					maxId = commande1.getId();
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (entityManager != null)
				entityManager.close();
			if (entityManagerFactory != null)
				entityManagerFactory.close();
		}
		return maxId;

	}

	public Commande insertGetCommande(Commande commande) {

		long id = insertGetCommandeId(commande);
		commande.setId(id);
		return commande;

	}

	public void update(Commande commandeUpd) {
		System.out.println("- Modification d'un commande ----------");
		System.out.println("commandeUpd=" + commandeUpd);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Commande commande = commandeRepository.findById(commandeUpd.getId()).orElse(null);
		if (commande != null) {

			Commande realCommande = commande;
			realCommande.setNumeroCommande(commandeUpd.getNumeroCommande());
			realCommande.setDateCommande(commandeUpd.getDateCommande());
			realCommande.setClient(commandeUpd.getClient());
			System.out.println("realCommande=" + realCommande);
			System.out.println("commande=" + commande);
			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();

				entityManager.merge(commandeUpd);

				trans.commit();

				commandeRepository.save(realCommande);

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

	public void delete(Commande commandeDelete) {
		System.out.println("- Delete d'un commande ----------");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Commande commande = commandeRepository.findById(commandeDelete.getId()).orElse(null);
		if (commande != null) {
			System.out.println("- Delete FOUND d'un commande ----------");

			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();
				entityManager.remove(entityManager.merge(commande));

				trans.commit();
				commandeRepository.delete(commande);

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

	public void refreshRepository() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		List<Commande> results = entityManager.createQuery("from Commande", Commande.class).getResultList();
		commandeRepository.deleteAll();
		for (Commande result : results) {
			System.out.println(result);
			commandeRepository.save(result);
		}
	}
}
