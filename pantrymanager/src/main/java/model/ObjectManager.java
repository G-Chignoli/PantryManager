package model;

import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import controller.Product;
//import util.HibernateUtil;

public class ObjectManager {
	
	public static void main(String[] args) {
	
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		    try (Session session = factory.openSession()) {
		      session.beginTransaction();

		      Product product = new Product("zucchine", 2, 0, 1200, LocalDate.now());
		      session.persist(product);

		      session.getTransaction().commit();

		    }

		    factory.close();
		  }

}

