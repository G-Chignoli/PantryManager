package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import model.Product;
import model.ProductManager;

public class MatrixRenderer{

	static String[] to_show = new String[9];
	final static int MTX_SIZE = 9;
	
	public MatrixRenderer() {
	}
	
	public static String[] fillMtx() {
		List<Product> p_list = ProductManager.getProducts();
		return populate(p_list);
	}

	public static String[] fillMtx(String name) {
		List<Product> p_list = ProductManager.getProductsByName(name);
		return populate(p_list);
	}
	
	private static String[] populate(List<Product> p_list) {
		int list_size = p_list.size();
		
		if (list_size >= MTX_SIZE) list_size = MTX_SIZE;
		
		for(int i = 0; i < MTX_SIZE; i++){
			if (i < list_size) {
				to_show[i] = p_list.get(i).getName(); 				
			} else {
				to_show[i] = "-";
			}
			System.out.println(to_show[i]);
		}
		return to_show;
	}
	
	
	public static String[] populate(String name) {
		List<Product> p_list = ProductManager.getProductsByName(name);
		int list_size = p_list.size();
		
		for(int i = 0; i < MTX_SIZE; i++){
				to_show[i] = "-";
			}
		System.out.println(to_show[0]);
		to_show[0] = p_list.getFirst().getName();
		return to_show;
	}
	
}
