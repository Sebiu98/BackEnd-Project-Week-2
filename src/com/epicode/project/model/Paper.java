package com.epicode.project.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity

public class Paper extends Readable {
	private Periodicity periodicity;

	public Paper(String ISBN, String Title, int Year, int Pages, Periodicity periodicity) {
		super(ISBN, Title, Year, Pages);
		
		this.periodicity = periodicity;

	}

	@Enumerated(EnumType.STRING)
	public Periodicity getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(Periodicity periodicity) {
		this.periodicity = periodicity;
	}

	public static String toString(Paper ele) {
		return null;
	}

}
