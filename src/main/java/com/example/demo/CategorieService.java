package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class CategorieService {
	private CategorieRepository categorieRepository;

	public CategorieService(CategorieRepository categorieRepository) {
		this.categorieRepository = categorieRepository;
	}

	public List<Categorie> findAll() {
		return (List<Categorie>) categorieRepository.findAll();
	}

	public void insert(Categorie categorie) {

		System.out.println("- Ajout d'une categorie----------");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(categorie);
			trans.commit();

			List<Categorie> categories = entityManager.createQuery("from Categorie", Categorie.class).getResultList();
			for (Categorie categorie1 : categories) {
				categorieRepository.save(categorie1);
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

	public void update(Categorie categorieUpd) {
		System.out.println("- Modification d'un categorie ----------");
		System.out.println("categorieUpd=" + categorieUpd);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Categorie categorie = categorieRepository.findById(categorieUpd.getId()).orElse(null);
		if (categorie != null) {

			Categorie realCategorie = categorie;
			realCategorie.setName(categorieUpd.getName());

			System.out.println("realCategorie=" + realCategorie);
			System.out.println("categorie=" + categorie);
			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();

				entityManager.merge(categorieUpd);

				trans.commit();

				categorieRepository.save(realCategorie);

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

	public void delete(Categorie categorieDelete) {
		System.out.println("- Delete d'un categorie ----------");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Categorie categorie = categorieRepository.findById(categorieDelete.getId()).orElse(null);
		if (categorie != null) {
			System.out.println("- Delete FOUND d'un categorie ----------");

			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();
				entityManager.remove(entityManager.merge(categorie));

				trans.commit();
				categorieRepository.delete(categorie);

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

		List<Categorie> results = entityManager.createQuery("from Categorie", Categorie.class).getResultList();
		categorieRepository.deleteAll();
		for (Categorie result : results) {
			System.out.println(result);
			categorieRepository.save(result);
		}
	}
}
