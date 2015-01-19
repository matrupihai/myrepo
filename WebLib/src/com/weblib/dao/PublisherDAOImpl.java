package com.weblib.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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
	
    public Publisher findByName(String name) {
        Criteria crit = getSession().createCriteria(Publisher.class);
        crit.add(Restrictions.eq("publisherName", name));
        
        return (Publisher) crit.uniqueResult();
    }
    
}
