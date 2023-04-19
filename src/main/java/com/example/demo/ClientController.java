package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	@Autowired
	ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	public ClientController() {

	}

	@GetMapping("/clients")
	public List<Client> getClients() {

		return (List<Client>) clientService.findAll();
	}

	@GetMapping("/clients/{clientId}")
	@ResponseBody
	public DataResult getDiscountMessageByClientId(@PathVariable long clientId) {
		return clientService.getDiscountMessageByClientId(clientId);
	}

	@PostMapping("/clients")
	void addClient(@RequestBody Client client) {
		try {

			clientService.insert(client);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@PutMapping("/clients")
	void UpdateClient(@RequestBody Client client) {
		try {
			// clientRepository.save(client);
			clientService.update(client);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@DeleteMapping("/clients")
	void DeleteClient(@RequestBody Client client) {
		try { //
			clientService.delete(client);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
