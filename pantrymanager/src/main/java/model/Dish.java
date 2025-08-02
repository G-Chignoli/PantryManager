package model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "DISH")
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	
	@Column(name = "name", nullable = false)
	protected String name; 
	
	@Embedded
	@ElementCollection
	@Column(name = "ingredients")
	protected Ingredient[] ingredients;
	
	public Dish() {
		
	}
	
	public Dish(String name, Ingredient[] ingredients) {
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
	
	public Ingredient[] getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(Ingredient[] ingredients) {
		this.ingredients = ingredients;
	}
	/*
	public String getIngredient(int i) {
		return ingredients[i];
	}
	*/
}
