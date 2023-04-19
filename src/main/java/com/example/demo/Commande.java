package com.example.demo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "commande")
public class Commande {
	@Id

	private long id;
	@Column(length = 100, nullable = true)
	private int numeroCommande;
	@Column(length = 100, nullable = true)
	private Date dateCommande;
	@Column(length = 100, nullable = true)
	private int montant;

	@ManyToOne
	@JoinColumn(name = "clientId", nullable = false)
	private Client client;

	public Commande() {

	}

	public Commande(long id, int numeroCommande, Date dateCommande, int montant, Client client) {
		super();
		this.id = id;
		this.numeroCommande = numeroCommande;
		this.dateCommande = dateCommande;
		this.montant = montant;
		this.client = client;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumeroCommande() {
		return numeroCommande;
	}

	public void setNumeroCommande(int numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", numeroCommande=" + numeroCommande + ", dateCommande=" + dateCommande
				+ ", montant=" + montant + ", client=" + client + "]";
	}

}
