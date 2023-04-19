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
public class AdresseController {
	// standard constructors
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	private final AdresseRepository adresseRepository;

	public AdresseController(AdresseRepository adresseRepository) {
		this.adresseRepository = adresseRepository;
	}

	@GetMapping("/adresses")
	public List<Adresse> getAdresses() {

		return (List<Adresse>) adresseRepository.findAll();
	}

	@PostMapping("/adresses")
	void addAdresse(@RequestBody Adresse adresse) {
		try {

			insert(adresse);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void insert(Adresse adresse) {

		System.out.println("- Ajout d'une adresse----------");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(adresse);
			trans.commit();
			adresseRepository.save(adresse);
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