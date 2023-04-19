package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
	// standard constructors
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	private final ClientRepository clientRepository;

	public ClientController(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@GetMapping("/clients")
	public List<Client> getClients() {

		return (List<Client>) clientRepository.findAll();
	}

	@PostMapping("/clients")
	void addAdresse(@RequestBody Client client) {
		try {

			insert(client);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void insert(Client client) {

		System.out.println("- Ajout d'un client----------");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(client);
			trans.commit();
			clientRepository.save(client);
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
