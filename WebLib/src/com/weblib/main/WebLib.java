package com.weblib.main;

import com.weblib.dao.PublisherDAOImpl;
import com.weblib.hbm.model.Book;
import com.weblib.hbm.model.Publisher;

public class WebLib {
	
	public static void main(String[] args) {
		System.out.println("ID: " + new PublisherDAOImpl().findByName("Penguin").getPublisherId());
	}
	
//	private static void insertAuthor(String name) {
//		Author author = new Author();
//		author.setAuthorName(name);
//		new AuthorDAOImpl().insertAuthor(author);
//	}
//	
//	private static void insertAuthors() {
//		List<Author> list = new ArrayList<Author>();
//		for (Author a : new AuthorDAOImpl().findAllAuthors()) {
//			a.setAuthorName(a.getAuthorName() + " TEST");
//			list.add(a);
//		}
//		
//		new AuthorDAOImpl().insertAuthors(list);
//	}
	
	private static void insertOneToMany() {
		Publisher p = new Publisher();
		p.setPublisherName("Penguin");
		
		Book b = new Book();
		b.setNoOfPages(354);
		b.setTitle("The Stranger");
		b.setYearPublished(1949);
		
		b.setPublisher(p);
		p.getBooks().add(b);
		
		new PublisherDAOImpl().insertPublisher(p);
	}
	
}
