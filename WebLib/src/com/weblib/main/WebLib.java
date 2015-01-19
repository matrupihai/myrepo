package com.weblib.main;

import com.weblib.dao.PublisherDAO;

public class WebLib {
	
	public static void main(String[] args) {
		System.out.println(new PublisherDAO().findAllPublishers());
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
	
//	private static void insertOneToMany() {
//		Publisher p = new Publisher();
//		p.setPublisherName("Humanitas");
//		
//		Book b = new Book();
//		b.setNoOfPages(329);
//		b.setTitle("1984");
//		b.setYearPublished(1949);
//		
//		b.setPublisher(p);
//		p.getBooks().add(b);
//		
//		new PublisherDAO().insertPublisher(p);
//	}
	
}
