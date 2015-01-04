package com.weblib.main;

import com.weblib.dao.AuthorDAO;
import com.weblib.hbm.model.Author;

public class WebLib {
	
	public static void main(String[] args) {
		Author author = new Author();
		author.setAuthorName("george orwell");
		new AuthorDAO().insertAuthor(author);
		
		for (Author a : new AuthorDAO().getAllAuthors()) {
			System.out.println(a.getAuthorName());
		}
	}
	
}
