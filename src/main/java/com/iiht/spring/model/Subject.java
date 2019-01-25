package com.iiht.spring.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="SUBJECT")
//@Proxy(lazy=false)
public class Subject {
	
	@Id
	@GeneratedValue
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	//@SequenceGenerator(name = "id_Sequence", sequenceName = "SUBJECT_ID_SEQ",allocationSize=1)
	private long subjectId;
	
	private String subTitle;
	
	private int durationInHours;
	
	@ManyToOne 
    @JoinColumn(name="bookId")
	private Book book;
	
	public Subject(String subTitle, int durationInHours, Book book) {
		super();
		this.subTitle = subTitle;
		this.durationInHours = durationInHours;
		this.book = book;
	}
	
	
	
	public Subject(){
		//System.out.println("Subject Entity Invoked...");
	}
	
	public Subject(long subjectId) {
		super();
		this.subjectId = subjectId;
	}
	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public int getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subTitle=" + subTitle + ", durationInHours=" + durationInHours
				+ ", book=" + book + "]";
	}
	

}
