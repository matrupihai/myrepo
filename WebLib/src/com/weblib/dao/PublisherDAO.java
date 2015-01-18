package com.weblib.dao;

import com.weblib.hbm.model.Publisher;

public class PublisherDAO {
	private GenericDAO dao = new GenericDAO();
	
	public Publisher insertPublisher(Publisher publisher) {
		dao.insert(publisher);
		return publisher;
	}
}
