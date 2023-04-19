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
@Table(name = "client")
public class Client {
	@Id
	private long id;

	@Column(length = 100, nullable = true)
	private String name;

	@Column(length = 100, nullable = true)
	private String firstName;

	@Column(length = 100, nullable = true)
	private String birthDate;

	@ManyToOne
	@JoinColumn(name = "adresseId", nullable = false)
	private Adresse adresse;

	public Client() {

	}

	public Client(long id, String name, String firstName, String birthDate, Adresse adresse) {
		super();
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.adresse = adresse;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", firstName=" + firstName + ", birthDate=" + birthDate
				+ ", adresse=" + adresse + "]";
	}

}
