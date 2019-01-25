package com.iiht.spring.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.spring.model.Book;
import com.iiht.spring.model.Subject;
import com.iiht.spring.repository.BookRepository;
import com.iiht.spring.repository.SubjectRepository;
 

@Service
public class BookServiceImpl implements BookService {
	
	@Resource
	private BookRepository bookRepository;
	
	@Resource
	private SubjectRepository subjectRepository;

	@Override
	@Transactional
	public Book insertBook(Book book) {
		// TODO Auto-generated method stub
		//this.bookDAO.insertBook(book);
		return bookRepository.save(book);
		
	}

	@Override
	@Transactional
	public void deleteSubject(long Id) {
		// TODO Auto-generated method stub
		//this.bookDAO.deleteSubject(Id);
		subjectRepository.delete(Id);
		
	}

	@Override
	@Transactional
	public void deleteBook(long Id) {
		// TODO Auto-generated method stub
		//this.bookDAO.deleteBook(Id);
		bookRepository.delete(Id);
	}

	@Override
	@Transactional
	public Book getBook(long bookId) {
		// TODO Auto-generated method stub
		//return this.bookDAO.getBook(bookId);
		//Optional<Book> book =  bookRepository.findById(bookId);
		return bookRepository.getOne(bookId);
	}

	@Override
	@Transactional
	public Subject getSubject(long subjectId) {
		// TODO Auto-generated method stub
		//return this.bookDAO.getSubject(subjectId);
		return subjectRepository.getOne(subjectId);
		
	}

	@Override
	@Transactional
	public Iterable<Book> listBooks() {
		// TODO Auto-generated method stub
		//return this.bookDAO.listBooks();
		//return bookRepository.findAll(new Sort(Sort.Direction.ASC, "title"));
		return bookRepository.findAll();
	}

	@Override
	@Transactional
	public List<Subject> listSubjects() {
		// TODO Auto-generated method stub
		//return this.bookDAO.listSubjects();
		return subjectRepository.findAll(new Sort(Sort.Direction.ASC, "durationInHours"));
	}


	@Override
	@Transactional
	public Subject insertSubject(Subject subject) {
		//this.bookDAO.insertSubject(subject);
		return subjectRepository.save(subject);
	}
	
	@Override
    @Transactional
    public Book updateBook(Book book){
        Book updateBook = bookRepository.getOne(book.getBookId());
        updateBook.setTitle(book.getTitle());
        return updateBook;
    }
	
	@Override
    @Transactional
    public Subject updateSubject(Subject subject){
        Subject updateSubject = subjectRepository.getOne(subject.getSubjectId());
        updateSubject.setSubTitle(subject.getSubTitle());
        return updateSubject;
    }
	
	 @Override
     public long countBooks() {
        return bookRepository.count();
     }
	 
	 @Override
     public long countSubjects() {
        return subjectRepository.count();
     }

}
