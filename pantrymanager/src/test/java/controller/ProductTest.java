package controller;

import java.time.LocalDate;
import org.junit.Test;

import model.Product;

import static org.junit.Assert.*;

public class ProductTest {
	String[] names = {"", "    ", "Zucchine", "Patate", "12345"};
	int[] weights = {0, 1, 1000, -1, -1000};

	public ProductTest() {
		
	}
	
	@Test
	public void ProductTest() {
		for(int i = 0; i < names.length; i++) {
			Product p = new Product(names[i], weights[i], 10, 220, LocalDate.of(2025, 8, 10));

			assertEquals(names[i], p.getName());
			assertEquals(weights[i], p.getWeight(), 0);
			assertEquals(10, p.getQty());
			assertEquals(220, p.getCalories(), 0);
			assertTrue(p.getExpirationDate().isEqual(LocalDate.of(2025, 8, 10)));
			
		}
	}
}
