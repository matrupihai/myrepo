package com.weblib.hbm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="DBA.\"100_AUTHORS\"")
public class Author {

	@Id
	@Column (name="author_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int authorId;

	@Column (name="author_name")
	private String authorName;
	
	public Author() {
		
	}
	
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
