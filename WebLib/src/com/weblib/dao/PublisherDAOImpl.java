package com.weblib.dao;

import java.util.List;

import com.weblib.hbm.model.Publisher;

public class PublisherDAOImpl extends GenericDAOImpl<Publisher, Integer> {
//	private ProjectDAO dao = new ProjectDAO();
//	private GenericDAOImpl<Publisher, Integer> dao = new GenericDAOImpl<Publisher, Integer>();
	
	public Publisher insertPublisher(Publisher publisher) {
		insert(publisher);
		return publisher;
	}
	
	public List<Publisher> findAllPublishers() {
		return findAll();
	}
}
