package com.epul.oeuvres.dao;

import java.util.ArrayList;
import java.util.List;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Oeuvrevente;
import com.epul.oeuvres.metier.Proprietaire;
import com.epul.oeuvres.persistance.DialogueBd;

public class ProprietaireService extends EntityManagerService {

	public Proprietaire consulterProprietaire(int numero) throws MonException {
		connection();
		return (Proprietaire) this.find(Proprietaire.class, numero);
	}

	public List<Proprietaire> consulterListeProprietaires() throws MonException {
		String mysql = "select * from proprietaire";
		List<Proprietaire> finded = (List<Proprietaire>) findAll(mysql);
		return finded;
	}

	public Proprietaire getProprietaire(int idProprietaire) throws MonException{
		Proprietaire proprietaire = new Proprietaire();

		try{
			for(Proprietaire p : consulterListeProprietaires()){
				if(p.getIdProprietaire() == idProprietaire){
					proprietaire = p;
					break;
				}
			}
		} catch (MonException e)
		{
			e.printStackTrace();
		}
		return proprietaire;
	}
}