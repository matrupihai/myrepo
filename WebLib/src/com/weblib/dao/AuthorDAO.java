package com.weblib.dao;

import java.util.List;

import com.weblib.hbm.model.Author;

public class AuthorDAO {
	private GenericDAO dao = new GenericDAO();
	
	public Author insertAuthor(Author author) {
		dao.insert(author);
		return author;
	}
	
	public List<Author> insertAuthors(List authors) {
		dao.insertAll(authors);
		return authors;
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> getAllAuthors() {
		return (List<Author>) dao.findAll(Author.class);
	}
	
	
}
