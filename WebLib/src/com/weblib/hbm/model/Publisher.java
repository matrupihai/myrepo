package com.weblib.hbm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "DBA.\"101_PUBLISHERS\"")

public class Publisher implements Serializable {
	@Id
	@Column (name="publisher_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int publisherId;
	
	@Column (name="publisher_name")
	private String publisherName;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "publisher", cascade = CascadeType.ALL)
	private Set<Book> books = new HashSet<Book>();
	
	public Publisher() {
		
	}

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(HashSet<Book> books) {
		this.books = books;
	}
	
}
