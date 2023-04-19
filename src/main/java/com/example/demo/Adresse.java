package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "adresse")
public class Adresse {
	@Id
	private long id;
	@Column(length = 150, nullable = true)
	private String rue;

	@Column(length = 10, nullable = true)
	private String numeroRue;

	@Column(length = 11, nullable = true)
	private String codePostal;

	@Column(length = 70, nullable = true)
	private String ville;

	public Adresse() {

	}

	public Adresse(long id, String rue, String numeroRue, String codePostal, String ville, Client client) {
		super();
		this.id = id;
		this.rue = rue;
		this.numeroRue = numeroRue;
		this.codePostal = codePostal;
		this.ville = ville;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getNumeroRue() {
		return numeroRue;
	}

	public void setNumeroRue(String numeroRue) {
		this.numeroRue = numeroRue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", rue=" + rue + ", numeroRue=" + numeroRue + ", codePostal=" + codePostal
				+ ", ville=" + ville + "]";
	}

}
