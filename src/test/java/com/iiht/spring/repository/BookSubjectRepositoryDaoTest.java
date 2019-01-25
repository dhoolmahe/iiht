package com.iiht.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iiht.spring.BootApp;
import com.iiht.spring.model.Book;
import com.iiht.spring.model.Subject;


/*@RunWith(SpringRunner.class)
@DataJpaTest*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BootApp.class, 
                      initializers = ConfigFileApplicationContextInitializer.class)
@AutoConfigureTestEntityManager
@Transactional
public class BookSubjectRepositoryDaoTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BookRepository bookRepositoryDao;
	
	@Autowired
	private SubjectRepository subjectRepositoryDao;
	
	@Test
	public void testSaveBook(){
		Book book = getBook();
		Book savedInDb = entityManager.merge(book);
		Book getFromDb = bookRepositoryDao.findOne(savedInDb.getBookId());
		
		assertThat(getFromDb).isEqualTo(savedInDb);
	}
	
	private Book getBook() {
		Book book = new Book();
		book.setBookId(1);
		book.setTitle("Struts");
		book.setPrice(112.00);
		book.setVolume(100);
		book.setPublishDate(Date.valueOf(LocalDate.now()));
		return book;
	}
	
	@Test
	public void testGetBookById(){
		//Save book in DB
		Book bookSavedInDb = entityManager.merge(getBook());
		
		//Get Book from DB
		Book bookFromInDb = bookRepositoryDao.findOne(bookSavedInDb.getBookId());
		assertThat(bookSavedInDb).isEqualTo(bookFromInDb);
	}
	
	
	@Ignore
	public void testSaveSubject(){
		//Subject subject = getSubject();
		
		Book book = new Book();
		book.setBookId(1);
		book.setTitle("Struts");
		book.setPrice(112.00);
		book.setVolume(100);
		book.setPublishDate(Date.valueOf(LocalDate.now()));
		
		Subject subject = new Subject();
		subject.setSubjectId(1);
		subject.setSubTitle("SpringAOP");
		subject.setDurationInHours(11);
		subject.setBook(book);
		Subject savedInDb = entityManager.merge(subject);
		Subject getFromDb = subjectRepositoryDao.findOne(savedInDb.getSubjectId());
		
		assertThat(getFromDb).isEqualTo(savedInDb);
	}
	
	private Subject getSubject() {
		Subject subject = new Subject();
		subject.setSubjectId(1);
		subject.setSubTitle("SpringAOP");
		subject.setDurationInHours(11);
		subject.setBook(getBook());
		return subject;
	}
	
	@Ignore
	public void testGetSubjectById(){
		
		Book book = new Book();
		book.setBookId(1);
		book.setTitle("Struts");
		book.setPrice(112.00);
		book.setVolume(100);
		book.setPublishDate(Date.valueOf(LocalDate.now()));
		
		Subject subject = new Subject();
		subject.setSubjectId(1);
		subject.setSubTitle("SpringAOP");
		subject.setDurationInHours(11);
		subject.setBook(book);
		
		Subject subSavedInDb = entityManager.merge(subject);
		//Get subject from DB
		Subject subFromInDb = subjectRepositoryDao.findOne(subSavedInDb.getSubjectId());
		assertThat(subSavedInDb).isEqualTo(subFromInDb);
	}
	
	@Test
	public void testDeleteBookById(){
		
		Book book1 = new Book();
		book1.setBookId(3);
		book1.setTitle("Struts");
		book1.setPrice(112.00);
		book1.setVolume(100);
		book1.setPublishDate(Date.valueOf(LocalDate.now()));
		
		Book book2 = new Book();
		book2.setBookId(4);
		book2.setTitle("Struts");
		book2.setPrice(112.00);
		book2.setVolume(100);
		book2.setPublishDate(Date.valueOf(LocalDate.now()));
		
		//Save both books in DB
		Book persist = entityManager.merge(book1);
		entityManager.merge(book2);
		
		//delete one book DB
		entityManager.remove(persist);
		
		Iterable<Book> allBooksFromDb = bookRepositoryDao.findAll();
		List<Book> bookList = new ArrayList<>();
		
		for (Book book : allBooksFromDb) {
			bookList.add(book);
		}
		assertThat(bookList.size()).isEqualTo(1);
	}
	
	
	@Ignore
	public void testDeleteSubjectById(){
		
		Subject subject1 = new Subject();
		subject1.setSubjectId(3);
		subject1.setSubTitle("SpringBatch1");
		subject1.setDurationInHours(151);
		subject1.setBook(getBook());
		
		Subject subject2 = new Subject();
		subject2.setSubjectId(4);
		subject2.setSubTitle("SpringBatch2");
		subject2.setDurationInHours(152);
		subject2.setBook(getBook());
		
		//Save both subjects in DB
		Subject persist = entityManager.merge(subject1);
		entityManager.merge(subject2);
		
		//delete one subject DB
		entityManager.remove(persist);
		
		Iterable<Subject> allSubsFromDb = subjectRepositoryDao.findAll();
		List<Subject> subList = new ArrayList<>();
		
		for (Subject book : allSubsFromDb) {
			subList.add(book);
		}
		assertThat(subList.size()).isEqualTo(1);
	}
	
	
	 
}
