package com.weblib.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.weblib.hbm.util.HibernateUtil;

public class ProjectDAO {
	private Logger log = Logger.getLogger(ProjectDAO.class);
	
	public void insert(Object entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error(e);
		} finally {
			session.close();
		}
	}
	
	public void insertAll(List entities) {
		if (!entities.isEmpty()) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();
				for (Object entity : entities) {
					session.save(entity);
				}
				session.flush();
				session.getTransaction().commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				log.error(e);
			} finally {
				session.close();
			}
		} else {
			log.error("Empty list");
		}
	}
	
	public void update(Object entity) {
		
	}
	
	public void delete(Object entity) {
		
	}
	
	@SuppressWarnings ("unchecked")
	public List findAll(Class entityClass) {
		List list = new ArrayList();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from " + entityClass.getSimpleName());
			list.addAll(query.list()); 
		} catch (Exception e) {
			log.error(e);
		} finally {
			session.close();
		}
		
		return list;
	}
	
}
