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
	@GeneratedValue (strategy = GenerationType.IDENTITY)
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
