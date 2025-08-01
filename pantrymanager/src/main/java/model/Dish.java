package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DISH")
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	
	@Column(name = "name", nullable = false)
	protected String name; 

	@Column(name = "ingredients")
	protected String[] ingredients;
	
	public Dish() {
		
	}
	
	public Dish(String name, String[] ingredients) {
		this.name = name;
		this.ingredients = ingredients;
	}
	
	public long getId() {
		return id;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String[] getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(String[] ingredients) {
		this.ingredients = ingredients;
	}
	
	public String getIngredient(int i) {
		return ingredients[i];
	}
}
