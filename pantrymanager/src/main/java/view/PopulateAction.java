package view;

import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.ProductManager;

public class PopulateAction {

	static String[] to_show = new String[9];
	final static int MTX_SIZE = 9;
	
	public void main(String[] args) {
		populate();
	}
	
	public PopulateAction() {
	}
	
	public static String[] populate() {
		List<Product> p_list = ProductManager.getProducts();
		int list_size = p_list.size();
		
		if (list_size >= MTX_SIZE) list_size = MTX_SIZE;
		
		for(int i = 0; i < MTX_SIZE; i++){
			if (i < list_size) {
				to_show[i] = p_list.get(i).getName(); 				
			} else {
				to_show[i] = "Nessun Prodotto";
			}
			System.out.println(to_show[i]);
		}
		return (String[]) to_show;
	}
	
	/*
	public List<Product> populateWithName(String name) {
		to_show = ProductManager.getProductsByName(name);
		return to_show;		
	}*/
	
	
}
