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
@Table(name = "articleCommande")
public class ArticleCommande {
	@Id
	private long id;

	@Column(length = 11, nullable = true)
	private int quantite;

	@ManyToOne
	@JoinColumn(name = "articleId", nullable = false)
	private Article article;

	@ManyToOne
	@JoinColumn(name = "commandeId", nullable = false)
	private Commande commande;

	public ArticleCommande() {

	}

	public ArticleCommande(long id, int quantite, Article article, Commande commande) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.article = article;
		this.commande = commande;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	@Override
	public String toString() {
		return "ArticleCommande [id=" + id + ", quantite=" + quantite + ", article=" + article + ", commande="
				+ commande + "]";
	}

}
