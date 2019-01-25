package com.iiht.spring;

 
import static java.lang.System.exit;

import java.sql.Date;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;

import com.iiht.spring.model.Book;
import com.iiht.spring.model.Subject;
import com.iiht.spring.service.BookService;

@SpringBootApplication
//@Profile("!IgonreBootAppCLRunnerClass")
//@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class BootApp implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger("BootApp");

	@Autowired
	private BookService service;

	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);
	}

	@Override
	public void run(String... strings) {

		
		LOG.info("****************************Books***************************************************");
		LOG.info("No of books in DB: {}", service.countBooks());

		Book book = service.insertBook(new Book("Mahi",11.33,44, Date.valueOf(LocalDate.now())));
		LOG.info("Book inserted in DB : {}", book);

		LOG.info("No of books in DB: {}", service.countBooks());

		book.setTitle("Struts");
		Book editedbook = service.updateBook(book);
		LOG.info("Book edited in DB  : {}", editedbook);

		 
		
		LOG.info("****************************Subjects***************************************************");
		LOG.info("No of subjects in DB: {}", service.countSubjects());

		Subject subject = service.insertSubject(new Subject("AOP",55, book));
		LOG.info("Subject inserted in DB : {}", subject);

		LOG.info("No of subjects in DB: {}", service.countSubjects());

		subject.setSubTitle("StrutsAOP");
		Subject editedSubject = service.updateSubject(subject);
		LOG.info("Subject edited in DB  : {}", editedSubject);

		subject.setSubjectId(1);
		service.deleteSubject(subject.getSubjectId());
		LOG.info("After deleting the subject, count: {}", service.countSubjects());
		
		book.setBookId(1);
		service.deleteBook(book.getBookId());
		LOG.info("After deleting the book, count: {}", service.countBooks());
		
		exit(0);
	}
}
