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
public class AdresseController {
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	@Autowired
	AdresseService adresseService;

	public AdresseController(AdresseService adresseService) {
		this.adresseService = adresseService;
	}

	public AdresseController() {

	}

	@GetMapping("/adresses")
	public List<Adresse> getAdresses() {

		return (List<Adresse>) adresseService.findAll();
	}

	@PostMapping("/adresses")
	void addAdresse(@RequestBody Adresse adresse) {
		try {

			adresseService.insert(adresse);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@PutMapping("/adresses")
	void UpdateAdresse(@RequestBody Adresse adresse) {
		try {
			// adresseRepository.save(adresse);
			adresseService.update(adresse);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@DeleteMapping("/adresses")
	void DeleteAdresse(@RequestBody Adresse adresse) {
		try { //
			adresseService.delete(adresse);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
