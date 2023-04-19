package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class ArticleService {
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public List<Article> findAll() {
		return (List<Article>) articleRepository.findAll();
	}

	public void insert(Article article) {

		System.out.println("- Ajout d'une article----------");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin();
			entityManager.persist(article);
			trans.commit();

			List<Article> articles = entityManager.createQuery("from Article", Article.class).getResultList();
			for (Article article1 : articles) {
				articleRepository.save(article1);
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

	public void update(Article articleUpd) {
		System.out.println("- Modification d'un article ----------");
		System.out.println("articleUpd=" + articleUpd);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Article article = articleRepository.findById(articleUpd.getId()).orElse(null);
		if (article != null) {

			Article realArticle = article;
			realArticle.setName(articleUpd.getName());
			realArticle.setPrixUnitaire(articleUpd.getPrixUnitaire());
			realArticle.setCategorie(articleUpd.getCategorie());
			System.out.println("realArticle=" + realArticle);
			System.out.println("article=" + article);
			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();

				entityManager.merge(articleUpd);

				trans.commit();

				articleRepository.save(realArticle);

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

	public void delete(Article articleDelete) {
		System.out.println("- Delete d'un article ----------");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Article article = articleRepository.findById(articleDelete.getId()).orElse(null);
		if (article != null) {
			System.out.println("- Delete FOUND d'un article ----------");

			try {
				EntityTransaction trans = entityManager.getTransaction();
				trans.begin();
				entityManager.remove(entityManager.merge(article));

				trans.commit();
				articleRepository.delete(article);

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

	public Article findById(long id) {
		return articleRepository.findById(id).orElse(null);
	}

	public boolean existsById(long id) {
		if (articleRepository.existsById(id)) {
			return true;
		} else
			return false;
	}

	/*
	 * public Article GetByName(String name) { List<Article> articles =
	 * (List<Article>) articleRepository.findAll(); boolean trouve = false; Article
	 * result = new Article(); int i = 0; while (i < articles.size() && !trouve) {
	 * if (articles.get(i).getName().toUpperCase().equals(name.toUpperCase())) {
	 * result = articles.get(i); trouve = true; } i++; } if (trouve) { return
	 * result; } else { return null; } }
	 */

	public List<Article> GetByName(String name) {
		List<Article> articles = (List<Article>) articleRepository.findAll();

		List<Article> articles2 = (List<Article>) articles.stream()
				.filter(p -> p.getName().toUpperCase().contains(name.toUpperCase())).map(p -> p)
				.collect(Collectors.toList());

		System.out.println("liste article tata :" + articles2);
		return articles2;
	}

	public void refreshRepository() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		List<Article> results = entityManager.createQuery("from Article", Article.class).getResultList();
		articleRepository.deleteAll();
		for (Article result : results) {
			System.out.println(result);
			articleRepository.save(result);
		}
	}
}
