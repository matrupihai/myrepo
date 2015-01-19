package com.weblib.dao;

import java.util.List;

import com.weblib.hbm.model.Publisher;

public class PublisherDAO extends GenericDAOImpl<Publisher, Integer> {
//	private ProjectDAO dao = new ProjectDAO();
//	private GenericDAOImpl<Publisher, Integer> dao = new GenericDAOImpl<Publisher, Integer>();
	
//	public Publisher insertPublisher(Publisher publisher) {
//		dao.insert(publisher);
//		return publisher;
//	}
	
	@SuppressWarnings ("unchecked")
	public List<Publisher> findAllPublishers() {
		return findAll();
	}
}
