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
	private static int current_page = 0;
	

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
		int pages = list_size / MTX_SIZE;
		current_page = Math.clamp(current_page, 0, pages);
		int j = current_page * MTX_SIZE;
		boolean last_page =  pages == current_page;
		
		if (list_size >= MTX_SIZE && last_page) list_size = list_size % MTX_SIZE;
		
		for(int i = 0; i < MTX_SIZE; i++){
			if (i < list_size) {
				to_show[i] = p_list.get(j).getName();
				j++;
			} else {
				to_show[i] = "-";
			}
		}
		return to_show;
	}
	
	public static int getCurrentPage() {
		return current_page;
	}
	
	public static void setCurrentPage(int current_page) {
		MatrixRenderer.current_page = current_page;
	}
	
}
