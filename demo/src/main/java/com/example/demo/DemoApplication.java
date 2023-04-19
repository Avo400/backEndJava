package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ClientRepository clientRepository, AdresseRepository adresseRepository) {
		return args -> {
			try {
				entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
				entityManager = entityManagerFactory.createEntityManager();
				// ADRESSE
				List<Adresse> adresses = entityManager.createQuery("from Adresse", Adresse.class).getResultList();
				for (Adresse adresse : adresses) {
					adresseRepository.save(adresse);

				}
				adresseRepository.findAll().forEach(System.out::println);

				// Client
				List<Client> clients = entityManager.createQuery("from Client", Client.class).getResultList();
				for (Client client : clients) {
					clientRepository.save(client);

				}
				clientRepository.findAll().forEach(System.out::println);

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				entityManager.close();
			}
		};
	}

}
