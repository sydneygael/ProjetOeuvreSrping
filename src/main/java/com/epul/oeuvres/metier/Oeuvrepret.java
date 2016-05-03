package com.epul.oeuvres.metier;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oeuvrepret database table.
 * 
 */
@Entity
@NamedQuery(name="Oeuvrepret.findAll", query="SELECT o FROM Oeuvrepret o")
public class Oeuvrepret implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_oeuvrepret")
	private int idOeuvrepret;

	@Column(name="titre_oeuvrepret")
	private String titreOeuvrepret;

	//bi-directional many-to-one association to Proprietaire
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proprietaire")
	private Proprietaire proprietaire;

	public Oeuvrepret() {
	}

	public int getIdOeuvrepret() {
		return this.idOeuvrepret;
	}

	public void setIdOeuvrepret(int idOeuvrepret) {
		this.idOeuvrepret = idOeuvrepret;
	}

	public String getTitreOeuvrepret() {
		return this.titreOeuvrepret;
	}

	public void setTitreOeuvrepret(String titreOeuvrepret) {
		this.titreOeuvrepret = titreOeuvrepret;
	}

	public Proprietaire getProprietaire() {
		return this.proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

}