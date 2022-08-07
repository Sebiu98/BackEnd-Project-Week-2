package com.epicode.project;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Catalogue implements Functions {

	private static final Logger logger = LoggerFactory.getLogger(Catalogue.class);
	private Map<String, Readable> archive;

	@Override
	public void add(Readable obj) {
		archive.put(null, obj);
		logger.info("Elemento aggiunto");

	}

	@Override
	public void removeIsbn(String isbn) {

		Readable removed = archive.remove(isbn);
		if (removed != null)
			logger.info("Elemento rimosso");

	}

	@Override
	public void searchIsbn(String isbn) {

		logger.info("Elemento Trovato con ISBN:" + archive.get(isbn));

	}

	@Override
	public List<Readable> searchYear(Integer Year) {
		return archive.values().stream().filter(ele -> Year.equals(ele.getYear())).collect(Collectors.toList());
	}

	@Override
	public List<Book> searchAuthor(String Author) {
		return archive.values().stream().filter(ele -> ele instanceof Book).map(ele -> (Book) ele)
				.filter(ele -> Author.equals(ele.getAuthor())).collect(Collectors.toList());

	}

	@Override
	public void save() throws IOException {
		String text = "";

		for (Readable ele : archive.values()) {

			if (ele instanceof Book) {
				text += Book.toString((Book) ele);
			} else if (ele instanceof Paper) {
				text += Paper.toString((Paper) ele);
			}

			File newReadable = new File("/BackEnd-Project-Week-2/src/com/epicode/project/db.txt");

			FileUtils.writeStringToFile(newReadable, text, "UTF-8");

			logger.info("Elemento salvato su DB" + text);

		}

	}

	@Override
	public void load() throws IOException {
		File loadReadable = new File("/BackEnd-Project-Week-2/src/com/epicode/project/db.txt");
		String text = FileUtils.readFileToString(loadReadable, "UTF-8");

		logger.info("Elemento caricato da DB" + text);

	}

		public static void main(String[] args) {
			Catalogue newCatalogue = new Catalogue();
			
			Book B1 = new Book("420-777", "Corso Java", 2022, 98, "Epicode", "Scientific");
			
			Book B2 = new Book("420-777", "Corso Java", 2021, 98, "Sebi", "Drama");
			
			Book B3 = new Book("420-777", "Corso Java", 2019, 98, "Mario", "Action");
			
			Book B4 = new Book("420-777", "Corso Java", 2012, 98, "Epicode", "Sentimental");
			
			Book B5 = new Book("420-777", "Corso Java", 2021, 98, "Gennaro", "Thriller");
			
			Paper P1 = new Paper("420-69", "La Gazetta del Programmatore", 2021, 15, Periodicity.SETTIMANALE);
			
			newCatalogue.add(P1);
			
			newCatalogue.add(B1);
			
			newCatalogue.add(B2);
			
			newCatalogue.add(B3);
			
			newCatalogue.add(B4);
			
			newCatalogue.add(B5);
			
			try {
				newCatalogue.save();
				
				newCatalogue.load();
				
				List<Readable> searchYear = newCatalogue.searchYear(2021);
				
				searchYear.forEach(ele -> System.out.println("Title: " + ele.getTitle()));
				
				List<Book> searchAuthor = newCatalogue.searchAuthor("Epicode");
				
				searchAuthor.forEach(ele -> System.out.println("Title: " + ele.getTitle()));
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			

		}

}
