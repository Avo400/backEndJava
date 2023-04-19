package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProduitController {
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	@Autowired
	ProduitService produitService;

	public ProduitController(ProduitService produitService) {
		this.produitService = produitService;
	}

	public ProduitController() {

	}

	@GetMapping("/produits")
	public List<Produit> getProduits() {

		return (List<Produit>) produitService.findAll();
	}

	@PostMapping("/produits")
	void addProduit(@RequestBody Produit produit) {
		try {

			produitService.insert(produit);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@PutMapping("/produits")
	void UpdateArticle(@RequestBody Produit produit) {
		try {
			// articleRepository.save(article);
			produitService.update(produit);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@DeleteMapping("/produits")
	void DeleteUser(@RequestBody Produit produit) {
		try { //
			produitService.delete(produit);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
