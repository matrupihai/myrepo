package com.weblib.main;

import java.util.ArrayList;
import java.util.List;

import com.weblib.dao.AuthorDAO;
import com.weblib.dao.PublisherDAO;
import com.weblib.hbm.model.Author;
import com.weblib.hbm.model.Book;
import com.weblib.hbm.model.Publisher;

public class WebLib {
	
	public static void main(String[] args) {
		insertOneToMany();
	}
	
	private static void insertAuthor(String name) {
		Author author = new Author();
		author.setAuthorName(name);
		new AuthorDAO().insertAuthor(author);
	}
	
	private static void insertAuthors() {
		List<Author> list = new ArrayList<Author>();
		for (Author a : new AuthorDAO().getAllAuthors()) {
			a.setAuthorName(a.getAuthorName() + " TEST");
			list.add(a);
		}
		
		new AuthorDAO().insertAuthors(list);
	}
	
	private static void insertOneToMany() {
		Book b = new Book();
		b.setNoOfPages(329);
		b.setTitle("The Stranger");
		b.setYearPublished(1980);
		
		
		Publisher p = new Publisher();
		p.setPublisherName("Humanitas");
		p.getBooks().add(b);
		
		new PublisherDAO().insertPublisher(p);
		
	}
	
}
