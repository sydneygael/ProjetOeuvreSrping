package com.epul.oeuvres.dao;


import java.util.*;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;
import com.epul.oeuvres.persistance.DialogueBd;

public class Service extends EntityManagerService {

	// Mise à jour des caractéristiques d'un adhérent
	// Le booleen indique s'il s'agit d'un nouvel adhérent, auquel cas on fait
	// une création

	public void insertAdherent(Adherent unAdherent) throws MonException {
		connection();
		this.inserer(unAdherent);
	}

	// gestion des adherents
	// Consultation d'un adhérent par son numéro
	// Fabrique et renvoie un objet adhérent contenant le résultat de la requête
	// BDD
	public Adherent consulterAdherent(int numero) throws MonException {
		connection();
		return (Adherent) find(Adherent.class, numero);
	}

	// Consultation des adhérents
	// Fabrique et renvoie une liste d'objets adhérent contenant le résultat de
	// la requête BDD
	public List<Adherent> consulterListeAdherents() throws MonException {
		String mysql = "select * from adherent";
		List<Adherent> finded = (List<Adherent>) findAll(mysql);
		return finded;
	}

	/**
	 * permet de mettre à jour un adhérent
	 * 
	 * @param adherent
	 * @throws MonException
	 */
	public void modifierAdherent(Adherent adherent) throws MonException {
		this.connection();
		this.mettreAJour(adherent);
	}

	/**
	 * permet de supprimer un adhérent avec son id
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
