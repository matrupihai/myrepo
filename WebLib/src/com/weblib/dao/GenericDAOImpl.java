package com.weblib.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.weblib.hbm.util.HibernateUtil;

public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {
	private Logger log = Logger.getLogger(GenericDAOImpl.class);
	private Class<T> objectType;
	
	public GenericDAOImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		objectType = (Class) pt.getActualTypeArguments()[0];
	}
	
	public void insert(T entity) {
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
		}
	}
	
	@Override
	public List<T> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<T> list = new ArrayList();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from " + objectType.getSimpleName());
			list.addAll(query.list()); 
		} catch (Exception e) {
			log.error(e);
		} 
		
		return list;
	}
}
