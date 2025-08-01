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

	public static void main(String[] args) {

		EntityManagerFactory entity_manager_factory;
		EntityManager entity_manager;
		
		/*
		String[] ingredients2 = {"cipolle", "melanzane", "carote", "cipolle", "melanzane", "carote", "cipolle", "melanzane", "carote"};
		
		Dish dish = new Dish(ingredients);
		Dish dish2 = new Dish(ingredients2);
		 */
		String[] ingredients = {"cipolle", "melanzane", "carote"};
		Dish dish = new Dish("Verdure", ingredients);
		
		try {
			entity_manager_factory = Persistence.createEntityManagerFactory("dish");
			entity_manager = entity_manager_factory.createEntityManager();

			entity_manager.getTransaction().begin();
			
			entity_manager.persist(dish);
			
			/*
			entity_manager.persist(dish2);

			CriteriaBuilder builder = entity_manager.getCriteriaBuilder();
			CriteriaQuery<Dish> criteria = builder.createQuery(Dish.class);
			Root<Dish> root = criteria.from(Dish.class);
			
			criteria.select(root);
			
			List<Dish> dishes = entity_manager.createQuery(criteria).getResultList();
			
			logger.warn(dishes.get(0).getIngredient(2));
			 */
			
			entity_manager.getTransaction().commit();
			entity_manager_factory.close();

		} catch (Exception e) {
			logger.error("Impossibile connettersi al Database.");
		} 
	}

}
