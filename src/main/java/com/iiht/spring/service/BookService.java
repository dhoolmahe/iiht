package com.iiht.spring.service;

import java.util.List;

import com.iiht.spring.model.Book;
import com.iiht.spring.model.Subject;

public interface BookService {

	public Book insertBook(Book book);
	public void deleteSubject(long Id);
	public void deleteBook(long Id);
	Book getBook(long bookId);
	Subject getSubject(long subjectId);
	public Iterable<Book> listBooks();
	public List<Subject> listSubjects();
	public Subject insertSubject(Subject subject);
	public Book updateBook(Book book);
	public Subject updateSubject(Subject subject);
	public long countBooks();
	public long countSubjects(); 
}
