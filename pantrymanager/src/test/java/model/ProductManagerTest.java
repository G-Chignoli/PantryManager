package model;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class ProductManagerTest {

    private static EntityManagerFactory entity_manager_factory;
    private static EntityManager entity_manager;

    @BeforeAll
    static void setup() {
        entity_manager_factory = Persistence.createEntityManagerFactory("pantry"); 
        entity_manager = entity_manager_factory.createEntityManager();
    }

    @BeforeEach
    void clearTable() {
        entity_manager.getTransaction().begin();
        entity_manager.createQuery("DELETE FROM Product").executeUpdate(); 
        entity_manager.getTransaction().commit();
    }
    
    @Test
    public void testGetProducts() {
        ProductManager.saveProduct("cipolle", 0f, 0, 0, null);
        ProductManager.saveProduct("zucchero", 0f, 0, 0, null);
        ProductManager.saveProduct("cavoletti", 0f, 0, 0, null);
        List<Product> products = ProductManager.getProducts();
        assertEquals(3, products.size());
    }
    
    @Test
    void testSaveProduct() {
        Product p_test = new Product("cipolle", 1.0f, 2, 150, LocalDate.now());
        assertEquals(0, ProductManager.saveProduct(p_test));

        Product p_saved = ProductManager.getProductsByName("cipolle").getFirst();
        assertEquals(p_test.getName(), p_saved.getName());
        assertEquals(p_test.getWeight(), p_saved.getWeight(), 0);
        assertEquals(p_test.getQty(), p_saved.getQty());
        assertEquals(p_test.getCalories(), p_saved.getCalories(), 0);
    }
    
    @Test
    void testSaveProductNull() {
        Product p_test = new Product(null, 0f, 2, 150, LocalDate.now());
        assertEquals(1, ProductManager.saveProduct(p_test));
    }
    
    @Test
    void testSaveProductEmpty() {
        Product p_test = new Product("", 0f, 2, 150, LocalDate.now());
        assertEquals(0, ProductManager.saveProduct(p_test));

    }
    
    @Test
    void testDeleteProduct() {
    	Product p_test = new Product("carote", 0.5f, 20, 300, LocalDate.now().plusDays(new Random().nextInt(365)));
        ProductManager.saveProduct(p_test);
        assertEquals(0, ProductManager.deleteProduct(p_test.getName()));

        List<Product> p_saved = ProductManager.getProductsByName(p_test.getName());
        assertEquals(0, p_saved.size());
    }
    
    @Test
    public void testModifyProduct() {
        Product p = new Product("spaghetti", 1.2f, 1, 200, LocalDate.now());
        ProductManager.saveProduct(p);
        Product p_mod = new Product("spaghetti", 2.0f, 5, 350, LocalDate.now().plusDays(23));
        ProductManager.modifyProduct(p_mod);

        Product p_saved = ProductManager.getProductsByName(p_mod.getName()).getFirst();
        assertEquals(p_mod.getWeight(), p_saved.getWeight(),0);
        assertEquals(p_mod.getQty(), p_saved.getQty());
        assertEquals(p_mod.getCalories(), p_saved.getCalories(),0);
        assertEquals(p_mod.getExpirationDate(), p_saved.getExpirationDate());
    }
    
	
}
