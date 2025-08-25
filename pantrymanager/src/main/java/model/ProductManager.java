package model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ProductManager {
	public static Logger logger = LogManager.getLogger(ProductManager.class);
	private static EntityManagerFactory entity_manager_factory	= Persistence.createEntityManagerFactory("pantry");
	private static EntityManager entity_manager = entity_manager_factory.createEntityManager();
	private static CriteriaBuilder builder = entity_manager.getCriteriaBuilder();
	
	public static int saveProduct(Product to_save) {
		return run(OperationMode.SAVE, to_save);		
	}
	
	public static int saveProduct(String name, float weight, int qty, int calories, LocalDate exp_date) {
		Product to_save = new Product(name.toLowerCase(), weight, qty, calories, exp_date);
		return run(OperationMode.SAVE, to_save);
	}
	
	public static int deleteProduct(String name) {
		Product to_delete = new Product(name.toLowerCase(), 0f, 0, 0, null);
		return run(OperationMode.DELETE, to_delete);
	}
	
	public static int modifyProduct(Product p) {
		return run(OperationMode.MODIFY, p);
	}
	
	public static int modifyProduct(String name, float weight, int qty, float calories, LocalDate exp_date) {
		Product to_change = new Product(name.toLowerCase(), weight, qty, calories, exp_date);
		return run(OperationMode.MODIFY, to_change);
	}
	/*
	*/
	
	private static int run(OperationMode operation, Product product) {
		int state = 0;
		try {
			entity_manager.getTransaction().begin();
			
			switch(operation) {
			  case OperationMode.SAVE:
				   	state = persistProduct(product);
			    break;
			  case OperationMode.DELETE:				  
				    state = removeProduct(product);
			    break;
			  case OperationMode.MODIFY:
				  	state = mergeProduct(product);
				break;
			  case OperationMode.GET:
				  getProducts();
				  break;
			  default:
			}
			
			entity_manager.getTransaction().commit();
			entity_manager.clear();
			//entity_manager_factory.close();
		} catch (Exception e) {
			logger.error("Impossibile connettersi al Database.");
		} 
		return state;
	}
	
	public static int mergeProduct(Product p) {
		try {
			  Product to_change = getProductsByName(p.getName()).get(0);
			  p.setId(to_change.getId());
			  entity_manager.merge(p);
		} catch (Exception e) {
			logger.error("Impossibile modificare il prodotto.");
			return 1;
		}		
		return 0;
	}

	private static int removeProduct(Product p) {
		try {
			entity_manager.remove(getProductsByName(p.getName()).get(0));			  
		} catch (Exception e) {
			logger.error("Impossibile eliminare il prodotto.");
			return 1;
		}	
		return 0;
	}

	private static int persistProduct(Product p) {
		try {
			entity_manager.persist(p);
		} catch (Exception e) {
			logger.error("Impossibile salvare il prodotto.");
			return 1;
		}
		return 0;
	}
	
	public static List<Product> getProducts() {
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);

		criteria.select(root);
		return entity_manager.createQuery(criteria).getResultList();
	}
	
	public static List<Product> getProductsByName(String name){
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		//criteria.select(root).where(builder.equal(root.get("name"), name ));
		criteria.where(builder.like(root.get("name"), name.toLowerCase().concat("%")));			
		entity_manager.createQuery(criteria).getResultList();
		return entity_manager.createQuery(criteria).getResultList();
	}

}
