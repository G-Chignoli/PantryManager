package model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class ProductManager {
	public static Logger logger = LogManager.getLogger(ProductManager.class);
	private static EntityManagerFactory entity_manager_factory	= Persistence.createEntityManagerFactory("product");
	private static EntityManager entity_manager = entity_manager_factory.createEntityManager();
	public static void main(String[] args) {
		OperationMode operation = OperationMode.NULL;
		Product product = new Product("cipolle", 2f, 0, 0, LocalDate.now());
		
		try {
			entity_manager.getTransaction().begin();
			
			switch(operation) {
			  case OperationMode.SAVE:
				  saveProduct(product);
			    break;
			  case OperationMode.DELETE:				  
				    deleteProduct(product);
			    break;
			  case OperationMode.MODIFY:
				  	modifyProduct(product);
				break;
			  default:
			}
			
			entity_manager.getTransaction().commit();
			entity_manager_factory.close();
			
		} catch (Exception e) {
			logger.error("Impossibile connettersi al Database.");
		} 
	}
	
	private static void modifyProduct(Product p) {
		try {
			  Product to_change = findProductByName(p.getName()).get(0);
			  p.setId(to_change.getId());
			  entity_manager.merge(p);
		} catch (Exception e) {
			logger.error("Impossibile modificare il prodotto.");
		}		
	}

	private static void deleteProduct(Product p) {
		try {
			entity_manager.remove(findProductByName(p.getName()).get(0));			  
		} catch (Exception e) {
			logger.error("Impossibile eliminare il prodotto.");
		}			
	}

	private static void saveProduct(Product p) {
		try {
			entity_manager.persist(p);
		} catch (Exception e) {
			logger.error("Impossibile salvare il prodotto.");
		}
	}
	
	public static List<Product> findProductByName(String name){
		CriteriaBuilder builder = entity_manager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		criteria.select(root).where(builder.equal(root.get("name"), name ));
		
		return entity_manager.createQuery(criteria).getResultList();
	}
}
