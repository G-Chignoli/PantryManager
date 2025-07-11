package controller;
import java.util.Date; 
import jakarta.persistence.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
	
public class Product {
	private String name = "";
	private float weight = 0;
	private short qty = 0;
	private Date expirationDate = new Date();  
	private float calories = 0;
	
	private static Logger logger = LogManager.getLogger(Product.class);

	public static void main(String[] args)
	{
		
	}
	
	public Product(String name, float weight, short qty, float calories, Date expirationDate) {
		this.name = name;
		this.weight = weight;
		this.qty = qty;
		this.calories = calories;
		this.expirationDate = expirationDate; 
	}
	
	public String getName() {
		return name; 
	}
	
	public float getWeight() {
		return weight; 
	}
	
	public short getQty() {
		return qty; 
	}
	
	public float getCalories() {
		return calories; 
	}
	
	public Date getExpirationDate() {
		return expirationDate; 
	}
	
	public void setName(String name) {
		this.name = name; 
	}
	
	public void setWeight(float weight) {
		this.weight= weight ; 
	}
	public void setQty(short qty) {
		this.qty = qty; 
	}
	
	public void setCalories(float calories) {
		this.calories = calories; 
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate; 
	}
}
