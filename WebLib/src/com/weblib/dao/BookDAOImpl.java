package com.weblib.dao;

import java.util.List;

import com.weblib.hbm.model.Book;

public class BookDAOImpl extends GenericDAOImpl<Book, Integer> {

	public Book insertBook(Book entity) {
		super.insert(entity);
		return entity;
	}	
	
	public List<Book> getAllBooks() {
		return findAll();
	}
	
	public Book findBookByTitle(String title) {
		return findByString("title", title);
	}
	
}
