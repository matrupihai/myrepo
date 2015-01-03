package com.weblib.hbm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name="100_AUTHORS")
public class Author {
	@Id
	@Column (name="author_id")
	private int authorId;
	@Column (name="author_name")
	private String authorName;
	
	public int getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
}
