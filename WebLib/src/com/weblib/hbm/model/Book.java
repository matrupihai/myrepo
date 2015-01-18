package com.weblib.hbm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name="DBA.\"102_BOOKS\"")
public class Book {
	@Id
	@Column (name = "isbn")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int isbn;
	
	@Column (name = "title")
	private String title;
	
	@Column (name = "no_of_pages")
	private int noOfPages;
	
	@Column (name = "year_published")
	private int yearPublished;
	
	private Publisher publisher;
	
	public int getIsbn() {
		return isbn;
	}
	
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getNoOfPages() {
		return noOfPages;
	}
	
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	
	public int getYearPublished() {
		return yearPublished;
	}
	
	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "publisher_id", nullable = true)
	public Publisher getPublisher() {
		return publisher;
	}
	
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	
}
