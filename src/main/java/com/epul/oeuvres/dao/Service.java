package com.epul.oeuvres.dao;


import java.util.*;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;
import com.epul.oeuvres.persistance.DialogueBd;

public class Service extends EntityManagerService {

	// Mise � jour des caract�ristiques d'un adh�rent
	// Le booleen indique s'il s'agit d'un nouvel adh�rent, auquel cas on fait
	// une cr�ation

	public void insertAdherent(Adherent unAdherent) throws MonException {
		connection();
		this.inserer(unAdherent);
	}

	// gestion des adherents
	// Consultation d'un adh�rent par son num�ro
	// Fabrique et renvoie un objet adh�rent contenant le r�sultat de la requ�te
	// BDD
	public Adherent consulterAdherent(int numero) throws MonException {
		connection();
		return (Adherent) find(Adherent.class, numero);
	}

	// Consultation des adh�rents
	// Fabrique et renvoie une liste d'objets adh�rent contenant le r�sultat de
	// la requ�te BDD
	public List<Adherent> consulterListeAdherents() throws MonException {
		String mysql = "select * from adherent";
		List<Adherent> finded = (List<Adherent>) findAll(mysql);
		return finded;
	}

	/**
	 * permet de mettre � jour un adh�rent
	 * 
	 * @param adherent
	 * @throws MonException
	 */
	public void modifierAdherent(Adherent adherent) throws MonException {
		this.connection();
		this.mettreAJour(adherent);
	}

	/**
	 * permet de supprimer un adh�rent avec son id
	 * @param numero integer
	 * @throws MonException
	 */
	public boolean supprimerAdherent(int id) throws MonException {
		connection();
		this.supprimer(Adherent.class, id);
		return  true;
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
