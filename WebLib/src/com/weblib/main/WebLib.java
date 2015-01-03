package com.weblib.main;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.weblib.hbm.util.HibernateUtil;



public class WebLib {
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Author");
		List list = query.list(); 
		session.close();
		
		System.out.println(list);
	}
	
}
