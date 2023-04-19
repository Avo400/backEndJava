package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleCommandeController {
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	private final ArticleCommandeService articleCommandeService;

	public ArticleCommandeController(ArticleCommandeService articleCommandeService) {
		this.articleCommandeService = articleCommandeService;
	}

	@GetMapping("/articleCommandes")
	public List<ArticleCommande> getArticleCommandes() {

		return (List<ArticleCommande>) articleCommandeService.findAll();
	}

	@PostMapping("/articleCommandes")
	void addProduit(@RequestBody ArticleCommande articleCommande) {
		try {

			articleCommandeService.insert(articleCommande);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@PutMapping("/articleCommandes")
	void UpdateArticleCommande(@RequestBody ArticleCommande articleCommande) {
		try {
			System.out.println("UpdateArticleCommande : debut");
			articleCommandeService.update(articleCommande);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@DeleteMapping("/articleCommandes")
	void DeleteArticleCommande(@RequestBody ArticleCommande articleCommande) {
		try { //
			articleCommandeService.delete(articleCommande);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}