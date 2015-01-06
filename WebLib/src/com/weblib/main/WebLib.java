package com.weblib.main;

import java.util.ArrayList;
import java.util.List;

import com.weblib.dao.AuthorDAO;
import com.weblib.hbm.model.Author;

public class WebLib {
	
	public static void main(String[] args) {
		insertAuthors();
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
	
}
