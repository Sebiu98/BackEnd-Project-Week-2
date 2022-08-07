package com.epicode.project.model;

import javax.persistence.Entity;


@Entity
public class Book extends Readable {

	private String Author;

	private String Type;

	public Book(String ISBN, String Title, int Year, int Pages, String Author, String Type) {
		super(ISBN, Title, Year, Pages);

		this.Author = Author;

		this.Type = Type;

	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public static String toString(Book ele) {
		return null;
	}


}
