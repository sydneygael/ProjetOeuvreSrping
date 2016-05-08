package com.epul.oeuvres.dao;

import java.util.ArrayList;
import java.util.List;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;
import com.epul.oeuvres.persistance.DialogueBd;

/**
 * permet de gérer tous les services d'une oeuvre
 * @author Sydney
 *
 */
public class OeuvreService extends EntityManagerService {

	  private static final String SYSTEME_ERROR = "erreur systeme";
	  
	  public OeuvreService () {
		  super();
	  }

	///////////////////////////////////
	  //traitements pour les oeuvre pret
	 ////////////////////////////////////
	
	/*****
	 * permet d'inserer oeuvre pret
	 * @param oeuvrePret
	 * @throws MonException
	 */
	public void insererOeuvrePret(Oeuvrepret oeuvrePret)throws MonException {
		connection();
		this.inserer(oeuvrePret);
	}

	/**
	 * mets à jour une oeuvre pret
	 * @param oeuvrePret
	 * @throws MonException
	 */
	public void mettreAJourOeuvrePret(Oeuvrepret oeuvrePret) throws MonException {
		connection();
		this.mettreAJour(oeuvrePret);
	}
	

	/**
	 * @param numero
	 * @return
	 * @throws MonException
	 */
	public Oeuvrepret consulterOeuvrePret(int numero) throws MonException {
		connection();
		return em.find(Oeuvrepret.class, numero);
	}

	/**
	 * @return
	 * @throws MonException
	 */
	@SuppressWarnings("unchecked")
	public List<Oeuvrepret> consulterListeOeuvresPret() throws MonException {
		String mysql = "SELECT * FROM oeuvrepret";
		List<Oeuvrepret> finded = (List<Oeuvrepret>) findAll(mysql);
		return finded;
	}

	public boolean supprimerOeuvrePret(int id) throws MonException {
		connection();
		transaction.begin();
		supprimer(Oeuvrepret.class, id);
		return true;
	}
	
	
	/////////////////////////////////////////////////////////////
	//	OEUVRE VENTE
	////////////////////////////////////////////////////////////
	
	/**
	 * permet d'insérer une oeuvre vente en base de données
	 * @param oeuvreVente
	 * @throws MonException
	 */
	public void insererOeuvreVente(Oeuvrevente oeuvreVente)throws MonException {
		connection();
		this.inserer(oeuvreVente);
	}
	
	/**
	 * @param oeuvreVente
	 * @throws MonException
	 */
	public void mettreAJourOeuvreVente(Oeuvrevente oeuvreVente) throws MonException {
		connection();
		this.mettreAJour(oeuvreVente);
	}
	
	public Oeuvrevente consulterOeuvrevente(int numero) throws MonException {
		connection();
		return em.find(Oeuvrevente.class, numero);
	}
	
	@SuppressWarnings("unchecked")
	public List<Oeuvrevente> consulterListeOeuvresVentes() throws MonException {
		connection();
		String mysql = "SELECT * FROM oeuvrevente";
		List<Oeuvrevente> finded = (List<Oeuvrevente>) findAll(mysql);
		return finded;
	}
	
	//permet de suppprimer une oeuvre vente
	public boolean supprimerOeuvreVente(int id) throws MonException {
		connection();
		this.supprimer(Oeuvrevente.class, id);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Oeuvrevente> consulterListOeuvresReservables() throws MonException {
		connection();
		String mysql = "select * from oeuvrevente WHERE etat_oeuvrevente ='L' ";
		List<Oeuvrevente> finded = (List<Oeuvrevente>) findAll(mysql);
		return finded;
	}

	public Oeuvrevente getOeuvre(int id) {
		  Oeuvrevente oeuvre = new Oeuvrevente();

	        try{
	            for(Oeuvrevente o : consulterListeOeuvresVentes() ){
	                if(o.getIdOeuvrevente() == id){
	                    oeuvre = o;
	                    break;
	                }
	            }
	        } catch (MonException e)
	        {
	            e.printStackTrace();
	        }
	        return oeuvre;
	    }
}
