package com.weblib.dao;

import java.util.List;

import com.weblib.hbm.model.Author;

public class AuthorDAOImpl extends GenericDAOImpl<Author, Integer> {
	
	public Author insertAuthor(Author author) {
		insert(author);
		return author;
	}
//	
//	public List<Author> insertAuthors(List authors) {
//		dao.insertAll(authors);
//		return authors;
//	}
	
	public List<Author> findAllAuthors() {
		return (List<Author>) findAll();
	}
	
	
}
