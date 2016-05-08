package com.epul.oeuvres.metier;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the reservation database table.
 * 
 */
@Embeddable
public class ReservationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_oeuvrevente", insertable=false, updatable=false)
	private int idOeuvrevente;

	@Column(name="id_adherent", insertable=false, updatable=false)
	private int idAdherent;

	public ReservationPK() {
	}
	public int getIdOeuvrevente() {
		return this.idOeuvrevente;
	}
	public void setIdOeuvrevente(int idOeuvrevente) {
		this.idOeuvrevente = idOeuvrevente;
	}
	public int getIdAdherent() {
		return this.idAdherent;
	}
	public void setIdAdherent(int idAdherent) {
		this.idAdherent = idAdherent;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReservationPK)) {
			return false;
		}
		ReservationPK castOther = (ReservationPK)other;
		return 
			(this.idOeuvrevente == castOther.idOeuvrevente)
			&& (this.idAdherent == castOther.idAdherent);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idOeuvrevente;
		hash = hash * prime + this.idAdherent;
		
		return hash;
	}
}