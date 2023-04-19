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
@Table(name = "article")
public class Article {
	@Id
	private long id;
	@Column(length = 100, nullable = true)
	private String name;

	@Column(length = 11, nullable = true)
	private int prixUnitaire;

	@ManyToOne
	@JoinColumn(name = "categorieId", nullable = false)
	private Categorie categorie;

	public Article() {

	}

	public Article(long id, String name, int prixUnitaire, Categorie categorie) {
		super();
		this.id = id;
		this.name = name;
		this.prixUnitaire = prixUnitaire;
		this.categorie = categorie;
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

	public int getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(int prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", prixUnitaire=" + prixUnitaire + ", categorie=" + categorie
				+ "]";
	}

}
