package model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
	
@Entity
@Table(name = "PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	
	@Column(name = "name", nullable = false)
	protected String name;
	
	@Column(name = "weight")
	protected float weight;

	@Column(name = "qty")
	protected int qty;

	@Column(name = "expiration_date")
	protected LocalDate expiration_date;  

	@Column(name = "calories")
	protected float calories;
	
	public Product() {
		
	}
	
	public Product(String name, float weight, int qty, float calories, LocalDate expiration_date) {
		this.name = name;
		this.weight = weight;
		this.qty = qty;
		this.calories = calories;
		this.expiration_date = expiration_date; 
	}
	
	
	public long getId() {
		return id; 
	}

	public String getName() {
		return name; 
	}
	
	public float getWeight() {
		return weight; 
	}
	
	public int getQty() {
		return qty; 
	}
	
	public float getCalories() {
		return calories; 
	}
	
	public LocalDate getExpirationDate() {
		return expiration_date; 
	}
	
	public void setId(int id) {
		this.id = id; 
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
	public void setExpirationDate(LocalDate expiration_date) {
		this.expiration_date = expiration_date; 
	}
}
