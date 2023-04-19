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
public class CategorieController {
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	@Autowired
	CategorieService categorieService;

	public CategorieController(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

	@GetMapping("/categories")
	public List<Categorie> getCategories() {

		return (List<Categorie>) categorieService.findAll();
	}

	@PostMapping("/categories")
	void addProduit(@RequestBody Categorie categorie) {
		try {

			categorieService.insert(categorie);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@PutMapping("/categories")
	void UpdateCategorie(@RequestBody Categorie categorie) {
		try {
			System.out.println("Je suis dans UpdateCategorie : DEBUT");
			// categorieRepository.save(categorie);
			categorieService.update(categorie);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@DeleteMapping("/categories")
	void DeleteCategorie(@RequestBody Categorie categorie) {
		try { //
			categorieService.delete(categorie);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
