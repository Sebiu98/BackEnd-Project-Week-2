package Catalogue;


import org.apache.commons.lang3.time.DateUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.epicode.project.model.Book;
import com.epicode.project.model.Paper;
import com.epicode.project.model.Periodicity;
import com.epicode.project.model.Loan;
import com.epicode.project.model.Sex;
import com.epicode.project.model.User;
import com.epicode.project.model.dao.UserDAO;
import com.epicode.project.model.dao.ReadableDAO;
import com.epicode.project.model.dao.LoanDAO;


public class Catalogue {

	public static void main(String[] args) {

		fillDb();
		
		ReadableDAO elDao = new ReadableDAO();
		
		elDao.getElementsByIsbn("12345");
		elDao.deleteByIsbn("12345");
		
		
		LoanDAO prDao = new LoanDAO();
		
		prDao.getLoanByNumber("12345");
		
		prDao.getExpiredLoan();
		
		

	}

	private static void fillDb() {
		Book b1 = saveBook();
		Paper p1 = savePaper();
		User user = saveUser();

		Loan loanInProgress = saveLoanInProgress(user, p1);

		Loan loanExpired = saveLoanExpired(user, b1);
	}

	private static Loan saveLoanInProgress(User user, Paper paper) {
		Loan loan = new Loan();
		loan.setLoaned(paper);
		loan.setUser(user);
		Date startDate = DateUtils.addDays(new Date(), -15);
		loan.setStartLoan(startDate);
		loan.setScheduledEndLoan(DateUtils.addDays(startDate, 30));

		return loan;
	}
	
	private static Loan saveLoanExpired(User user, Book book) {
		Loan loan = new Loan();
		loan.setLoaned(book);
		loan.setUser(user);
		Date startDate = DateUtils.addDays(new Date(), -50);
		loan.setStartLoan(startDate);
		loan.setScheduledEndLoan(DateUtils.addDays(startDate, 30));

		return loan;
	}

	private static User saveUser() {
		User user = new User();
		user.setSurname("Rossi");
		user.setName("Mario");
		user.setEmail("m.rossi@nowhere.com");
		user.setNumber("12345");
		user.setSex(Sex.MASCHIO);

		UserDAO utDao = new UserDAO();
		utDao.save(user);

		return user;
	}

	private static Book saveBook() {
		Book book = new Book();
		book.setYear(2022);
		book.setAuthor("Epicode");
		book.setType("Scientific");
		book.setIsbn("420-777");
		book.setPages(98);
		book.setTitle("Corso Java");

		ReadableDAO elDao = new ReadableDAO();
		elDao.save(book);
		return book;
	}

	private static Paper savePaper() {
		Paper paper = new Paper();
		paper.setYear(2021);
		paper.setIsbn("420-69");
		paper.setPages(15);
		paper.setTitle("La Gazetta del Programmatore");
		paper.setPeriodicity(Periodicity.SETTIMANALE);

		ReadableDAO elDao = new ReadableDAO();
		elDao.save(paper);
		return paper;
	}

}
