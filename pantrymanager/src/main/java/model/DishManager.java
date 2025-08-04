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
	private static CriteriaBuilder builder = entity_manager.getCriteriaBuilder();

	public static void main(String[] args) {


		try {
			entity_manager.getTransaction().begin();

			/*
			String[] ing = {"carote", "sale", "pepe"};
			entity_manager.persist(new Dish("Carote al forno".toLowerCase(), ing));
			String[] ing2 = {"cipolle", "zucchero"};
			entity_manager.persist(new Dish("ciPollE Caramellate".toLowerCase(), ing2));
			String[] ing3 = {"cipolle"};
			entity_manager.persist(new Dish("ciPoLLE".toLowerCase(), ing3));
			 */
			
			String[] ing = getDishByName("").getIngredients();
			for(int i = 0; i < ing.length ; i++) {
				logger.warn(ing[i]);
			}
			
			entity_manager.getTransaction().commit();
			entity_manager_factory.close();

		} catch (Exception e) {
			logger.error("Impossibile connettersi al Database.");
		} 
	}
	
	public static List<Dish> getDishes() {
		CriteriaQuery<Dish> criteria = builder.createQuery(Dish.class);
		Root<Dish> root = criteria.from(Dish.class);

		criteria.select(root);

		return entity_manager.createQuery(criteria).getResultList();
	}
	
	public static Dish getDishByName(String name) {
		CriteriaQuery<Dish> criteria = builder.createQuery(Dish.class);
		Root<Dish> root = criteria.from(Dish.class);
		
		criteria.where(builder.like(root.get("name"), name.toLowerCase().concat("%")));
		
		return entity_manager.createQuery(criteria).getSingleResultOrNull();
	}

	public static boolean checkForIngredient(String name) {
		return !ProductManager.findProductByName(name).isEmpty();
	}

}
