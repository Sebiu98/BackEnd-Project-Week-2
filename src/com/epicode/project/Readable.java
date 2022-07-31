package com.epicode.project;

public abstract class Readable {

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