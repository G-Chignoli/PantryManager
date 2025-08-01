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


public class ProductManager {
	public static Logger logger = LogManager.getLogger(ProductManager.class);
	
	public static void main(String[] args) {
		OperationMode operation = OperationMode.NULL;
		Product product = new Product("Carote", 2f, 0, 0, LocalDate.now());
		
		
        EntityManagerFactory entity_manager_factory;
		EntityManager entity_manager;
		try {
			entity_manager_factory = Persistence.createEntityManagerFactory("product");
			entity_manager = entity_manager_factory.createEntityManager();
			
			entity_manager.getTransaction().begin();
			
			switch(operation) {
			  case OperationMode.SAVE:
				  saveProduct(entity_manager, product);
			    break;
			  case OperationMode.DELETE:				  
				    deleteProduct(entity_manager, product);
			    break;
			  case OperationMode.MODIFY:
				  	modifyProduct(entity_manager, product);
				break;
			  default:
			}
			
			entity_manager.getTransaction().commit();
			entity_manager_factory.close();
			
		} catch (Exception e) {
			logger.error("Impossibile connettersi al Database.");
		} 
	}
	
	private static void modifyProduct(EntityManager em, Product p) {
		try {
			  Product to_change = findProductByName(em, p.getName()).get(0);
			  p.setId(to_change.getId());
			  em.merge(p);
		} catch (Exception e) {
			logger.error("Impossibile modificare il prodotto.");
		}		
	}

	private static void deleteProduct(EntityManager em, Product p) {
		try {
			em.remove(findProductByName(em, p.getName()).get(0));			  
		} catch (Exception e) {
			logger.error("Impossibile eliminare il prodotto.");
		}			
	}

	private static void saveProduct(EntityManager entity_manager, Product p) {
		try {
			entity_manager.persist(p);
		} catch (Exception e) {
			logger.error("Impossibile salvare il prodotto.");
		}
	}
	
	public static List<Product> findProductByName(EntityManager em, String name){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		criteria.select(root).where(builder.equal(root.get("name"), name ));
		
		return em.createQuery(criteria).getResultList();
	}
}
