package com.billing.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.util.HibernateUtil;

public class BillingDatabase {
	private static SessionFactory factory;
	
	/**
	 * This method is used to create session
	 * @param annotationClassList
	 */
	public static void createSessionFactory(List<Class> annotationClassList) {
		try {
			factory = HibernateUtil.getSessionFactory(annotationClassList);
			System.out.println("Session factory object created.......");
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	
	public static Session createSession() {
		return factory.openSession();
	}
	
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	
	public static void closeSessionFactory() {
		if(factory != null && factory.isOpen()) {
			factory.close();
			System.out.println("Session factory is closed successfully.");
		}
	}
	
	public static void closeSession(Session session) {
		if(session != null && session.isOpen()) {
//			session.clear();
			session.close();
			System.out.println("Session is closed successfully.");
		}
	}
	
}
