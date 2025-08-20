package model;


import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
			
			//Transaction
			logger.warn(getDishesByName("cipolle"));
			
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
	
	public static List<Dish> getDishesByName(String name) {
		CriteriaQuery<Dish> criteria = builder.createQuery(Dish.class);
		Root<Dish> root = criteria.from(Dish.class);
		
		criteria.where(builder.like(root.get("name"), name.toLowerCase().concat("%")));
		
		return entity_manager.createQuery(criteria).getResultList();
	}

	public static boolean checkForIngredient(String name) {
		return !ProductManager.getProductsByName(name).isEmpty();
	}
	
	

}
