package com.iiht.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.iiht.spring.BootApp;
import com.iiht.spring.model.Book;
import com.iiht.spring.model.Subject;
import com.iiht.spring.repository.BookRepository;
import com.iiht.spring.repository.SubjectRepository;

/* 
@RunWith(SpringRunner.class)
@SpringBootTest*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BootApp.class, 
                      initializers = ConfigFileApplicationContextInitializer.class)
public class BookServiceImplTest {

	@Autowired
	private BookServiceImpl bookService;
	
	@MockBean
	private BookRepository bookRepositoryDao;
	
	@MockBean
	private SubjectRepository subjectRepositoryDao;
	
	@Test
	public void testCreateBook(){

		Book book = getBook();
		/*book.setBookId(1);
		book.setTitle("Struts");
		book.setPrice(112.00);
		book.setVolume(100);
		book.setPublishDate(Date.valueOf(LocalDate.now()));*/
		
	    Mockito.when(bookRepositoryDao.save(book)).thenReturn(book);
	    System.out.println("******Insert Book *********");
	    assertThat(bookService.insertBook(book)).isEqualTo(book);
	    System.out.println("******Insert Book *********");
	
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
		Book book = new Book();
		book.setBookId(2);
		book.setTitle("Spring");
		book.setPrice(114.00);
		book.setVolume(200);
		book.setPublishDate(Date.valueOf(LocalDate.now()));
		
	    Mockito.when(bookRepositoryDao.getOne(new Long(2))).thenReturn(book);
	    System.out.println("******Get Book *********");
	    assertThat(bookService.getBook(2)).isEqualTo(book);
	    System.out.println("******Get Book *********");
	}
	
	@Test
	public void testGetSubjectById(){
		Subject subject = new Subject();
		subject.setSubjectId(1);
		subject.setSubTitle("SpringAOP");
		subject.setDurationInHours(11);
		subject.setBook(getBook());
		
	    Mockito.when(subjectRepositoryDao.getOne(new Long(1))).thenReturn(subject);
	    System.out.println("******Get Subject *********");
	    assertThat(bookService.getSubject(1)).isEqualTo(subject);
	    System.out.println("******Get Subject *********");
	}
	
	@Test
	public void testGetAllBooks(){
		
		Book book1 = new Book();
		book1.setBookId(1);
		book1.setTitle("Struts");
		book1.setPrice(112.00);
		book1.setVolume(100);
		book1.setPublishDate(Date.valueOf(LocalDate.now()));
		
		Book book2 = new Book();
		book2.setBookId(2);
		book2.setTitle("Spring");
		book2.setPrice(114.00);
		book2.setVolume(200);
		book2.setPublishDate(Date.valueOf(LocalDate.now()));
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(book1);
		bookList.add(book2);
		
		Mockito.when(bookRepositoryDao.findAll()).thenReturn(bookList);
		assertThat(bookService.listBooks()).isEqualTo(bookList);
	}
	
	
	@Test
	public void testDeleteBook(){
		
		Book book2 = new Book();
		book2.setBookId(2);
		book2.setTitle("Spring");
		book2.setPrice(114.00);
		book2.setVolume(200);
		book2.setPublishDate(Date.valueOf(LocalDate.now()));
		
		
		Mockito.when(bookRepositoryDao.getOne(new Long(2))).thenReturn(book2);
	    Mockito.when(bookRepositoryDao.exists(book2.getBookId())).thenReturn(false);
	    assertFalse(bookRepositoryDao.exists(book2.getBookId()));
	}
	
	@Test
	public void testDeleteSubject(){
		
		Subject subject = new Subject();
		subject.setSubjectId(3);
		subject.setSubTitle("SpringBatch");
		subject.setDurationInHours(15);
		subject.setBook(getBook());
		
		
		Mockito.when(subjectRepositoryDao.getOne(new Long(3))).thenReturn(subject);
	    Mockito.when(subjectRepositoryDao.exists(subject.getSubjectId())).thenReturn(false);
	    assertFalse(bookRepositoryDao.exists(subject.getSubjectId()));
	}
	
	
 
}
