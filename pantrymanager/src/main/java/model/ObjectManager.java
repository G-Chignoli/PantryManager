package model;

import org.hibernate.Session;

import util.HibernateUtil;

public class ObjectManager {	
	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    	
			session.beginTransaction();
			//session.persist();
			session.getTransaction().commit();

		} catch (Exception e){
		} 
	}
}

