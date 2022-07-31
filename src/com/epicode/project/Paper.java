package com.epicode.project;

public class Paper extends Readable {
	private Periodicity periodicity;

	public Paper(String ISBN, String Title, int Year, int Pages, Periodicity periodicity) {
		super(ISBN, Title, Year, Pages);
		
		this.periodicity = periodicity;

	}

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
