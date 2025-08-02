package model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Ingredient {
	
	protected String name;
	protected int qty;

	public Ingredient() {
	
	}
	
	public Ingredient(String name, int qty) {
		this.name = name;
		this.qty = qty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
