package model;

import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import database.Database;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DishManager {
	public static Logger logger = LogManager.getLogger(DishManager.class);
	private static EntityManagerFactory entity_manager_factory = Persistence.createEntityManagerFactory("dish");
	private static EntityManager entity_manager = entity_manager_factory.createEntityManager();

	public static void main(String[] args) {

		
		try {
			/*
			entity_manager_factory = Persistence.createEntityManagerFactory("dish");
			entity_manager = entity_manager_factory.createEntityManager();
			*/
			entity_manager.getTransaction().begin();
						
			entity_manager.getTransaction().commit();
			entity_manager_factory.close();

		} catch (Exception e) {
			logger.error("Impossibile connettersi al Database.");
		} 
	}
	
	public static List<Dish> getDishes(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Dish> criteria = builder.createQuery(Dish.class);
		Root<Dish> root = criteria.from(Dish.class);
		
		criteria.select(root);
		
		return em.createQuery(criteria).getResultList();
	}
	
	public static boolean checkForIngredient(String name) {
		return !ProductManager.findProductByName(name).isEmpty();
	}
	
}
