package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "produit")
public class Produit {
	@Id
	private long id;
	@Column(length = 100, nullable = true)
	private String name;

	@ManyToOne
	@JoinColumn(name = "categorieId", nullable = false)
	private Categorie categorie;

	@Column(length = 50, nullable = true)
	private int prixUnitaire;

	public Produit() {

	}

	public Produit(long id, String name, Categorie categorie, int prixUnitaire) {
		super();
		this.id = id;
		this.name = name;
		this.categorie = categorie;
		this.prixUnitaire = prixUnitaire;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public int getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(int prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", name=" + name + ", categorie=" + categorie + ", prixUnitaire=" + prixUnitaire
				+ "]";
	}

}
