package com.hl.is.dms.rest.model.util;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionfctory = buildSessionFactory(); 
	@SuppressWarnings("unused")
	private static SessionFactory buildSessionFactory(){
		try{
			return new Configuration().configure().buildSessionFactory();
		} catch(Exception x){
			System.out.println(x);
			
		}
		return null;
	}
	
	public static SessionFactory getSessionfactory(){
		return sessionfctory;
	}
	
	public static void shutdown(){
		getSessionfactory().close();
	}
}
