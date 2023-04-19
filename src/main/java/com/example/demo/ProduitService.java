package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class ProduitService {
	private ProduitRepository produitRepository;

	public ProduitService(ProduitRepository produitRepository) {
		this.produitRepository = produitRepository;
	}

	public List<Produit> findAll() {
		return (List<Produit>) produitRepository.findAll();
	}

	public void insert(Produit produit) {

		System.out.println("- Ajout d'un produit----------");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(produit);
			trans.commit();

			List<Produit> produits = entityManager.createQuery("from Produit", Produit.class).getResultList();
			for (Produit produit1 : produits) {
				produitRepository.save(produit1);
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

	public void update(Produit produitUpd) {
		System.out.println("- Modification d'un produit ----------");
		System.out.println("produitUpd=" + produitUpd);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Produit produit = produitRepository.findById(produitUpd.getId()).orElse(null);
		if (produit != null) {

			Produit realProduit = produit;
			realProduit.setName(produitUpd.getName());
			realProduit.setPrixUnitaire(produitUpd.getPrixUnitaire());
			System.out.println("realProduit=" + realProduit);
			System.out.println("produit=" + produit);
			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();

				entityManager.merge(produitUpd);

				trans.commit();

				produitRepository.save(realProduit);

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

	public void delete(Produit produitDelete) {
		System.out.println("- Delete d'un produit ----------");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Produit produit = produitRepository.findById(produitDelete.getId()).orElse(null);
		if (produit != null) {
			System.out.println("- Delete FOUND d'un produit ----------");

			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();
				entityManager.remove(entityManager.merge(produit));

				trans.commit();
				produitRepository.delete(produit);

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

		List<Produit> results = entityManager.createQuery("from Produit", Produit.class).getResultList();
		produitRepository.deleteAll();
		for (Produit result : results) {
			System.out.println(result);
			produitRepository.save(result);
		}
	}
}
