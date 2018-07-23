package com.packtpub.springdata.jpa.bar.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="les_absences")
public class MyView {
	
	@Id
	@Column(name="ID")
	private Long Id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_DEBUT")
	private Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FINE")
	private Date dateFin;
	
	@Column(name="DATE_DEBUT_DEMI_JOUR")
	private boolean debutDemiJour;
	
	@Column(name="DATE_FINE_DEMI_JOUR")
	private boolean finDemiJour;
	
	@Column(name="NOMBRE_JOURS")
	private double nbJours;
	
	@Column(name="TRIGRAMME")
	private String username;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isDebutDemiJour() {
		return debutDemiJour;
	}

	public void setDebutDemiJour(boolean debutDemiJour) {
		this.debutDemiJour = debutDemiJour;
	}

	public boolean isFinDemiJour() {
		return finDemiJour;
	}

	public void setFinDemiJour(boolean finDemiJour) {
		this.finDemiJour = finDemiJour;
	}

	public double getNbJours() {
		return nbJours;
	}

	public void setNbJours(double nbJours) {
		this.nbJours = nbJours;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public LocalDate ConvertDateDebut() {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sf.format(this.dateDebut);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		return LocalDate.parse(dateString,formatter);
	}
}
