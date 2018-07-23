package com.packtpub.springdata.jpa.bar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_ABSENCE")
public class Absence {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name ="DATE_DEMANDE")
	private Date dateDemande;
	
	@Temporal(TemporalType.DATE)
	@Column(name ="DATE_DEBUT")
	private Date dateDebut;
	
	@Column(name ="DATE_DEBUT_DEMI_JOUR")
	private boolean dateDebutDemiJournee;
	
	@Temporal(TemporalType.DATE)
	@Column(name ="DATE_FINE")
	private Date dateFin;
	
	@Column(name ="DATE_FINE_DEMI_JOUR")
	private boolean dateFinDemiJournee;

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public boolean isDateDebutDemiJournee() {
		return dateDebutDemiJournee;
	}

	public void setDateDebutDemiJournee(boolean dateDebutDemiJournee) {
		this.dateDebutDemiJournee = dateDebutDemiJournee;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isDateFinDemiJournee() {
		return dateFinDemiJournee;
	}

	public void setDateFinDemiJournee(boolean dateFinDemiJournee) {
		this.dateFinDemiJournee = dateFinDemiJournee;
	}

	public Long getId() {
		return id;
	}
	
}
