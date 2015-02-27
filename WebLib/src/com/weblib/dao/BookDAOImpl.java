package com.weblib.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.weblib.hbm.model.Book;
import com.weblib.hbm.model.BookCopy;

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

	public Book findBookByIsbn(Integer isbn) {
		if (isbn == null) {
			throw new IllegalArgumentException("A book isbn cannot be null");
		}

		return findById(isbn);
	}

	public Set<BookCopy> findBookCopies(Integer isbn) {
		if (isbn == null) {
			throw new IllegalArgumentException("A book isbn cannot be null");
		}

		Book book = findBookByIsbn(isbn);
		if (book != null) {
			return book.getCopies();
		}

		return new HashSet<BookCopy>();
	}

}
