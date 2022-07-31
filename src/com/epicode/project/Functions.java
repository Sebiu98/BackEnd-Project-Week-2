package com.epicode.project;

import java.io.IOException;
import java.util.List;

public interface Functions {

	void add(Readable obj);

	void removeIsbn(String isbn);

	void searchIsbn(String isbn);

	List<Readable> searchYear(Integer Year);
	
	List<Book> searchAuthor(String Author);

	void save() throws IOException;

	void load() throws IOException;

}
