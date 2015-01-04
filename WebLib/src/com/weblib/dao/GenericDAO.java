package com.weblib.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.weblib.hbm.util.HibernateUtil;

public class GenericDAO {
	private static Session currentSession = HibernateUtil.getSessionFactory().openSession();
	
	public static Session getSession() {
		if (!currentSession.isOpen()) {
			currentSession = HibernateUtil.getSessionFactory().openSession();
		}
		
		return currentSession;
	}
	
	public void insert(Object entity) {
		getSession().beginTransaction();
		getSession().save(entity);
		getSession().getTransaction().commit();
		getSession().flush();
		getSession().close();
	}
	
	public void update(Object entity) {
		
	}
	
	public void delete(Object entity) {
		
	}
	
	public List findAll(Class entityClass) {
		getSession().beginTransaction();
		Query query = getSession().createQuery("from " + entityClass.getSimpleName());
		List list = query.list(); 
		getSession().flush();
		getSession().close();
		
		return list;
	}
	
}
