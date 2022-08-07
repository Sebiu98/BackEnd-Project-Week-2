package com.epicode.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQuery(name = "prestitoByTessera", query = "SELECT pr FROM Loan pr WHERE pr.user.number = :number")
@NamedQuery(name = "prestitoScaduto", query = "SELECT pr FROM Loan pr WHERE pr.endLoan < CURRENT_DATE and pr.endLoan IS NULL")

public class Loan {

	@Id
	@SequenceGenerator(name = "prestito_seq", sequenceName = "prestito_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prestito_seq")
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Readable readable;

	private Date startLoan;
	private Date scheduledEndLoan;
	private Date efectiveEndLoan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User utente) {
		this.user = utente;
	}

	public Readable getLoaned() {
		return readable;
	}

	public void setLoaned(Readable loaned) {
		this.readable = loaned;
	}

	public Date getStartLoan() {
		return startLoan;
	}

	public void setStartLoan(Date startLoan) {
		this.startLoan = startLoan;
	}

	public Date getScheduledEndLoan() {
		return scheduledEndLoan;
	}

	public void setScheduledEndLoan(Date scheduledEndLoan) {
		this.scheduledEndLoan = scheduledEndLoan;
	}

	public Date getEfectiveEndLoan() {
		return efectiveEndLoan;
	}

	public void setEfectiveEndLoan(Date efectiveEndLoan) {
		this.efectiveEndLoan = efectiveEndLoan;
	}

}
