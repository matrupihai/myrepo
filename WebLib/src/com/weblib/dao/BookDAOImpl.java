package com.weblib.dao;

import com.weblib.hbm.model.Book;

public class BookDAOImpl extends GenericDAOImpl<Book, Integer> {

	public Book insertBook(Book entity) {
		super.insert(entity);
		return entity;
	}	
	
	
	
}
