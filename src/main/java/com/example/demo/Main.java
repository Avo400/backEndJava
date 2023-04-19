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
public class Main {
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

	}

	@Bean
	CommandLineRunner init(

			AdresseRepository adresseRepository, ClientRepository clientRepository,
			CommandeRepository commandeRepository, CategorieRepository categorieRepository,
			ArticleRepository articleRepository, ArticleCommandeRepository articleCommandeRepository) {
		return args -> {
			try {
				entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
				entityManager = entityManagerFactory.createEntityManager();

				/*
				 * // READ CATEGORIES List<Categorie> categories =
				 * entityManager.createQuery("from Categorie", Categorie.class)
				 * .getResultList(); for (Categorie categorie : categories) {
				 * categorieRepository.save(categorie);
				 * 
				 * } categorieRepository.findAll().forEach(System.out::println);
				 * 
				 * // READ PRODUITS List<Produit> produits =
				 * entityManager.createQuery("from Produit", Produit.class).getResultList(); for
				 * (Produit produit : produits) { produitRepository.save(produit);
				 * 
				 * } produitRepository.findAll().forEach(System.out::println);
				 */

				// READ ADRESSES
				List<Adresse> adresses = entityManager.createQuery("from Adresse", Adresse.class).getResultList();
				for (Adresse adresse : adresses) {
					adresseRepository.save(adresse);

				}

				adresseRepository.findAll().forEach(System.out::println);

				// READ CLIENTS
				List<Client> clients = entityManager.createQuery("from Client", Client.class).getResultList();
				for (Client client : clients) {
					clientRepository.save(client);

				}

				clientRepository.findAll().forEach(System.out::println);

				// READ COMMANDES
				List<Commande> commandes = entityManager.createQuery("from Commande", Commande.class).getResultList();
				for (Commande commande : commandes) {
					commandeRepository.save(commande);

				}
				commandeRepository.findAll().forEach(System.out::println);

				// READ CATEGORIES
				List<Categorie> categories = entityManager.createQuery("from Categorie", Categorie.class)
						.getResultList();

				for (Categorie categorie : categories) {
					categorieRepository.save(categorie);

				}
				categorieRepository.findAll().forEach(System.out::println);

				// READ ARTICLES
				List<Article> articles = entityManager.createQuery("from Article", Article.class).getResultList();
				for (Article article : articles) {
					articleRepository.save(article);

				}
				articleRepository.findAll().forEach(System.out::println);

				// READ ARTICLECOMMANDES
				List<ArticleCommande> articleCommandes = entityManager
						.createQuery("from ArticleCommande", ArticleCommande.class).getResultList();
				for (ArticleCommande articleCommande : articleCommandes) {
					articleCommandeRepository.save(articleCommande);

				}
				articleCommandeRepository.findAll().forEach(System.out::println);

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				entityManager.close();
			}

		};
	}

//	@Bean
//	CommandLineRunner init(CategorieRepository categorieRepository, ProduitRepository produitRepository) {
//		return args -> {
//			try {
//				entityManagerFactory = Persistence.createEntityManagerFactory("javabdd");
//				entityManager = entityManagerFactory.createEntityManager();
//
//				// CATEGORIE
//				List<Categorie> categories = entityManager.createQuery("from Categorie", Categorie.class)
//						.getResultList();
//				for (Categorie categorie : categories) {
//					categorieRepository.save(categorie);
//
//				}
//				categorieRepository.findAll().forEach(System.out::println);
//
//				// PRODUIT
//				List<Produit> produits = entityManager.createQuery("from Produit", Produit.class).getResultList();
//				for (Produit produit : produits) {
//					produitRepository.save(produit);
//
//				}
//
//				produitRepository.findAll().forEach(System.out::println);
//
//			} catch (Exception e) {
//				System.out.println(e);
//			} finally {
//				entityManager.close();
//			}
//
//		};
//	}

}
