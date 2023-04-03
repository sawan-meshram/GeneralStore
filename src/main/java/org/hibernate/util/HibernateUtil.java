package org.hibernate.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory(List<Class> annotationClassList) {
		if (sessionFactory == null) {
		    try {
		        Configuration configuration = new Configuration()
		        		.configure();
		        for (Class cls : annotationClassList) {
		            configuration.addAnnotatedClass(cls); //.getClass()
		        }
		        sessionFactory = configuration.buildSessionFactory();
		    } catch (Throwable ex) {
		    	ex.printStackTrace();
		        System.out.println("Failed to create sessionFactory object." + ex);
		        throw new ExceptionInInitializerError(ex);
		    }
		}
	    return sessionFactory;
	}
	
}
