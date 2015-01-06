package com.weblib.hbm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "DBA.\"101_PUBLISHERS\"")

public class Publisher implements Serializable {
	@Id
	@Column (name="publisher_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int publisherId;
	
	@Column (name="publisher_name")
	private int publisherName;
	
	public Publisher() {
		
	}

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public int getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(int publisherName) {
		this.publisherName = publisherName;
	}
	
}
