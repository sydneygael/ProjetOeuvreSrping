package com.epul.oeuvres.metier;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proprietaire database table.
 * 
 */
@Entity
@NamedQuery(name="Proprietaire.findAll", query="SELECT p FROM Proprietaire p")
public class Proprietaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_proprietaire")
	private int idProprietaire;

	@Column(name="nom_proprietaire")
	private String nomProprietaire;

	@Column(name="prenom_proprietaire")
	private String prenomProprietaire;

	//bi-directional many-to-one association to Oeuvrepret
	@OneToMany(mappedBy="proprietaire")
	private List<Oeuvrepret> oeuvreprets;

	//bi-directional many-to-one association to Oeuvrevente
	@OneToMany(mappedBy="proprietaire")
	private List<Oeuvrevente> oeuvreventes;

	public Proprietaire() {
	}

	public int getIdProprietaire() {
		return this.idProprietaire;
	}

	public void setIdProprietaire(int idProprietaire) {
		this.idProprietaire = idProprietaire;
	}

	public String getNomProprietaire() {
		return this.nomProprietaire;
	}

	public void setNomProprietaire(String nomProprietaire) {
		this.nomProprietaire = nomProprietaire;
	}

	public String getPrenomProprietaire() {
		return this.prenomProprietaire;
	}

	public void setPrenomProprietaire(String prenomProprietaire) {
		this.prenomProprietaire = prenomProprietaire;
	}

	public List<Oeuvrepret> getOeuvreprets() {
		return this.oeuvreprets;
	}

	public void setOeuvreprets(List<Oeuvrepret> oeuvreprets) {
		this.oeuvreprets = oeuvreprets;
	}

	public Oeuvrepret addOeuvrepret(Oeuvrepret oeuvrepret) {
		getOeuvreprets().add(oeuvrepret);
		oeuvrepret.setProprietaire(this);

		return oeuvrepret;
	}

	public Oeuvrepret removeOeuvrepret(Oeuvrepret oeuvrepret) {
		getOeuvreprets().remove(oeuvrepret);
		oeuvrepret.setProprietaire(null);

		return oeuvrepret;
	}

	public List<Oeuvrevente> getOeuvreventes() {
		return this.oeuvreventes;
	}

	public void setOeuvreventes(List<Oeuvrevente> oeuvreventes) {
		this.oeuvreventes = oeuvreventes;
	}

	public Oeuvrevente addOeuvrevente(Oeuvrevente oeuvrevente) {
		getOeuvreventes().add(oeuvrevente);
		oeuvrevente.setProprietaire(this);

		return oeuvrevente;
	}

	public Oeuvrevente removeOeuvrevente(Oeuvrevente oeuvrevente) {
		getOeuvreventes().remove(oeuvrevente);
		oeuvrevente.setProprietaire(null);

		return oeuvrevente;
	}

}