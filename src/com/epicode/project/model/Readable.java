package com.epicode.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "elementByIsbn", query = "SELECT el FROM Readable el WHERE el.isbn = :isbn ")
@NamedQuery(name = "elementByYear", query = "SELECT el FROM Readable el WHERE el.Year = :Year ")
@NamedQuery(name = "elementByAuthor", query = "SELECT el FROM Readable el WHERE lower(el.Author) = lower(:Author)")
@NamedQuery(name = "elementByTitle", query = "SELECT el FROM Readable el WHERE lower(el.Title) LIKE CONCAT('%',:Title,'%')")

public abstract class Readable {

	@Id

	@SequenceGenerator(name = "elementoletterario_seq", sequenceName = "elementoletterario_seq", allocationSize = 1)

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elementoletterario_seq")

	private Long id;

	protected String isbn;

	protected String Title;

	protected int Year;

	protected int Pages;

	public Readable(String ISBN, String Title, int Year, int Pages) {

		this.isbn = ISBN;

		this.Title = Title;

		this.Year = Year;

		this.Pages = Pages;

	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public int getPages() {
		return Pages;
	}

	public void setPages(int pages) {
		Pages = pages;
	}

	public String toString() {
		return "ISBN: " + isbn + "" + "Title: " + Title + "" + "Year: " + Year + "" + "Pages: " + Pages;
	}

}