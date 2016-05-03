package com.epul.oeuvres.dao;


import java.util.*;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;
import com.epul.oeuvres.persistance.DialogueBd;

public class Service {

	// Mise � jour des caract�ristiques d'un adh�rent
	// Le booleen indique s'il s'agit d'un nouvel adh�rent, auquel cas on fait
	// une cr�ation

	public void insertAdherent(Adherent unAdherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "insert into adherent  (nom_adherent,prenom_adherent,ville_adherent)  " + "values ('"
					+ unAdherent.getNomAdherent();
			mysql += "'" + ",'" + unAdherent.getPrenomAdherent() + "','" + unAdherent.getVilleAdherent() + "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	// gestion des adherents
	// Consultation d'un adh�rent par son num�ro
	// Fabrique et renvoie un objet adh�rent contenant le r�sultat de la requ�te
	// BDD
	public Adherent consulterAdherent(int numero) throws MonException {
		String mysql = "select * from adherent where numero_adherent=" + numero;
		List<Adherent> mesAdh = consulterListeAdherents(mysql);
		if (mesAdh.isEmpty())
			return null;
		else {
			return mesAdh.get(0);
		}
	}

	// Consultation des adh�rents
	// Fabrique et renvoie une liste d'objets adh�rent contenant le r�sultat de
	// la requ�te BDD
	public List<Adherent> consulterListeAdherents() throws MonException {
		String mysql = "select * from adherent";
		return consulterListeAdherents(mysql);
	}

	private List<Adherent> consulterListeAdherents(String mysql) throws MonException {
		List<Object> rs;
		List<Adherent> mesAdherents = new ArrayList<Adherent>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On cr�e un stage
				Adherent unA = new Adherent();
				// il faut redecouper la liste pour retrouver les lignes
				unA.setIdAdherent(Integer.parseInt(rs.get(index + 0).toString()));
				unA.setNomAdherent(rs.get(index + 1).toString());
				unA.setPrenomAdherent(rs.get(index + 2).toString());
				unA.setVilleAdherent(rs.get(index + 3).toString());
				// On incr�mente tous les 3 champs
				index = index + 4;
				mesAdherents.add(unA);
			}

			return mesAdherents;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	/**
	 * permet de mettre � jour un adh�rent
	 * 
	 * @param adherent
	 * @throws MonException
	 */
	public void modifierAdherent(Adherent adherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "UPDATE adherent SET "+
					"nom_adherent = '"+adherent.getNomAdherent()+"', "+
					"prenom_adherent = '"+adherent.getPrenomAdherent()+"', "+
					"ville_adherent = '"+adherent.getVilleAdherent()+"' "+
					"WHERE id_adherent = "+adherent.getIdAdherent();

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	/**
	 * permet de supprimer un adh�rent avec son id
	 * 
	 * @param numero integer
	 * @throws MonException
	 */
	public boolean supprimerAdherent(int id) throws MonException {
		String mysql = "DELETE FROM adherent WHERE id_adherent = "+id;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {


			unDialogueBd.insertionBD(mysql);
			return true;
		} catch (MonException e) {
			throw e;
		}
	}

	/**
	 * Consulter les adh�rents par paquet
	 * Fabrique et renvoie les objets adh�rent contenant le r�sultat de la requ�te
	 * 
	 * @throws MonException
	 */
	public List<Adherent> consulterListeAdherents(int page, int nombreParPage) throws MonException {
		String mysql = "SELECT * FROM adherent "+
				"ORDER BY id_adherent "+
				"LIMIT "+(page*nombreParPage)+","+nombreParPage;
		return consulterListeAdherents(mysql);
	}

	public Adherent getAdherent(int id) {
		Adherent adherent = new Adherent();

		try{
			for(Adherent a : consulterListeAdherents() ){
				if(a.getIdAdherent() == id){
					adherent = a;
					break;
				}
			}
		} catch (MonException e)
		{
			e.printStackTrace();
		}
		return adherent;
	}

}
