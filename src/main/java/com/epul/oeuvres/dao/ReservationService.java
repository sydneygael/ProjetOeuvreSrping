package com.epul.oeuvres.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;
import com.epul.oeuvres.persistance.DialogueBd;

public class ReservationService extends EntityManagerService {

	public void insererReservation(Reservation reservation)throws MonException {
		this.connection();
		transaction.begin();
		ReservationPK rPK = new ReservationPK();
		rPK.setIdOeuvrevente(reservation.getOeuvrevente().getIdOeuvrevente());
		rPK.setIdAdherent(reservation.getAdherent().getIdAdherent());
		reservation.setId(rPK);
		em.persist(reservation);
		transaction.commit();
		em.close();
	}

	public void mettreAJourReservation(Reservation reservation) throws MonException {
		this.connection();
		this.mettreAJour(reservation);
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> consulterListeReservation() throws MonException {
		String mysql = "select * from reservation";
		List<Reservation> finded = (List<Reservation>) findAll(mysql);
		return finded;
	}

	public boolean supprimerReservation(int idOeuvre,int idAdherent) throws MonException {
		this.connection();
		transaction.begin();
		ReservationPK rPk = new ReservationPK();
		rPk.setIdOeuvrevente(idOeuvre);
		rPk.setIdAdherent(idAdherent);
		Reservation reservation = em.find(Reservation.class, rPk);
		em.remove(reservation);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return true;
	}


}
