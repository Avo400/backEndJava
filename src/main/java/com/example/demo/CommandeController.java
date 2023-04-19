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
public class CommandeController {
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	private final CommandeService commandeService;

	public CommandeController(CommandeService commandeService) {
		this.commandeService = commandeService;
	}

	@GetMapping("/commandes")
	public List<Commande> getCommandes() {

		return (List<Commande>) commandeService.findAll();
	}

	@PostMapping("/postCommandeAndGetCommande")
	Commande addCommande(@RequestBody Commande commande) {
		Commande result = new Commande();
		try {

			result = commandeService.insertGetCommande(commande);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	@PostMapping("/commandes")
	long addCommandeWithId(@RequestBody Commande commande) {
		long result = 0;
		try {

			result = commandeService.insertGetCommandeId(commande);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	/*
	 * @PostMapping("/commandesWithReturnCommande") Commande
	 * addCommandeWithReturnCommande(@RequestBody Commande commande) { Commande
	 * result = 0; try {
	 * 
	 * result = commandeService.insertGetCommande(commande); } catch (Exception e) {
	 * System.out.println(e); } return result; }
	 */

	@PutMapping("/commandes")
	void UpdateCommande(@RequestBody Commande commande) {
		try {
			// adresseRepository.save(adresse);
			commandeService.update(commande);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@DeleteMapping("/commandes")
	void DeleteCommande(@RequestBody Commande commande) {
		try { //
			commandeService.delete(commande);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
