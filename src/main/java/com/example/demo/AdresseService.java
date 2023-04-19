package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class AdresseService {
	private AdresseRepository adresseRepository;

	public AdresseService(AdresseRepository adresseRepository) {
		this.adresseRepository = adresseRepository;
	}

	public List<Adresse> findAll() {
		return (List<Adresse>) adresseRepository.findAll();
	}

	public void insert(Adresse adresse) {

		System.out.println("- Ajout d'une adresse----------");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(adresse);
			trans.commit();

			List<Adresse> adresses = entityManager.createQuery("from Adresse", Adresse.class).getResultList();
			for (Adresse adresse1 : adresses) {
				adresseRepository.save(adresse1);
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

	public void update(Adresse adresseUpd) {
		System.out.println("- Modification d'un adresse ----------");
		System.out.println("adresseUpd=" + adresseUpd);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Adresse adresse = adresseRepository.findById(adresseUpd.getId()).orElse(null);
		if (adresse != null) {

			Adresse realAdresse = adresse;
			realAdresse.setRue(adresseUpd.getRue());
			realAdresse.setNumeroRue(adresseUpd.getNumeroRue());
			realAdresse.setCodePostal(adresseUpd.getCodePostal());
			realAdresse.setVille(adresseUpd.getVille());
			System.out.println("realAdresse=" + realAdresse);
			System.out.println("adresse=" + adresse);
			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();

				entityManager.merge(adresseUpd);

				trans.commit();

				adresseRepository.save(realAdresse);

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

	public void delete(Adresse adresseDelete) {
		System.out.println("- Delete d'un adresse ----------");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Adresse adresse = adresseRepository.findById(adresseDelete.getId()).orElse(null);
		if (adresse != null) {
			System.out.println("- Delete FOUND d'un adresse ----------");

			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();
				entityManager.remove(entityManager.merge(adresse));

				trans.commit();
				adresseRepository.delete(adresse);

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

		List<Adresse> results = entityManager.createQuery("from Adresse", Adresse.class).getResultList();
		adresseRepository.deleteAll();
		for (Adresse result : results) {
			System.out.println(result);
			adresseRepository.save(result);
		}
	}
}
