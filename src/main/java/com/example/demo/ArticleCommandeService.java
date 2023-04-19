package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class ArticleCommandeService {
	private ArticleCommandeRepository articleCommandeRepository;

	public ArticleCommandeService(ArticleCommandeRepository articlecommandeRepository) {
		this.articleCommandeRepository = articlecommandeRepository;
	}

	public List<ArticleCommande> findAll() {
		return (List<ArticleCommande>) articleCommandeRepository.findAll();
	}

	public void insert(ArticleCommande articleCommande) {

		System.out.println("- Ajout d'une articleCommande----------");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(articleCommande);
			trans.commit();

			List<ArticleCommande> articleCommandes = entityManager
					.createQuery("from ArticleCommande", ArticleCommande.class).getResultList();
			for (ArticleCommande articleCommande1 : articleCommandes) {
				articleCommandeRepository.save(articleCommande1);
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

	public void update(ArticleCommande articleCommandeUpd) {
		System.out.println("- update : Modification d'un articleCommande ----------");
		System.out.println("articleCommandeUpd=" + articleCommandeUpd);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ArticleCommande articleCommande = articleCommandeRepository.findById(articleCommandeUpd.getId()).orElse(null);
		if (articleCommande != null) {

			ArticleCommande realArticleCommande = articleCommande;
			realArticleCommande.setArticle(articleCommandeUpd.getArticle());
			realArticleCommande.setQuantite(articleCommandeUpd.getQuantite());
			realArticleCommande.setCommande(articleCommandeUpd.getCommande());
			System.out.println("realArticleCommande=" + realArticleCommande);
			System.out.println("articleCommande=" + articleCommande);
			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();

				entityManager.merge(articleCommandeUpd);

				trans.commit();

				articleCommandeRepository.save(realArticleCommande);

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

	public void delete(ArticleCommande articleCommandeDelete) {
		System.out.println("- Delete d'un articleCommande ----------");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		ArticleCommande articleCommande = articleCommandeRepository.findById(articleCommandeDelete.getId())
				.orElse(null);
		if (articleCommande != null) {
			System.out.println("- Delete FOUND d'un articleCommande ----------");

			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();
				entityManager.remove(entityManager.merge(articleCommande));

				trans.commit();
				articleCommandeRepository.delete(articleCommande);

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

		List<ArticleCommande> results = entityManager.createQuery("from ArticleCommande", ArticleCommande.class)
				.getResultList();
		articleCommandeRepository.deleteAll();
		for (ArticleCommande result : results) {
			System.out.println(result);
			articleCommandeRepository.save(result);
		}
	}
}
