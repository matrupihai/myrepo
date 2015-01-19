package com.weblib.dao;

import java.util.List;

import com.weblib.hbm.model.Author;

public class AuthorDAOImpl extends GenericDAOImpl<Author, Integer> {
	private ProjectDAO dao = new ProjectDAO();
//	
//	public Author insertAuthor(Author author) {
//		dao.insert(author);
//		return author;
//	}
//	
//	public List<Author> insertAuthors(List authors) {
//		dao.insertAll(authors);
//		return authors;
//	}
	
	@SuppressWarnings("unchecked")
	public List<Author> findAllAuthors() {
		return (List<Author>) findAll();
	}
	
	
}
