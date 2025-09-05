package view;

import java.util.List;

import model.Product;
import model.ProductManager;

public class MatrixRenderer{

	private static final int MTX_SIZE = 9;
	private static int current_page = 0;
	
	public static Product[] getProductsToShow() {
		List<Product> p_list = ProductManager.getProducts();
		return fillProductArrayObjects(p_list);
	}
	
	public static Product[] getProductsToShow(String name) {
		List<Product> p_list = ProductManager.getProductsByName(name);
		return fillProductArrayObjects(p_list);
	}
	
	private static Product[] fillProductArrayObjects(List<Product> p_list) {
		Product[] to_show = new Product[9];
		int list_size = p_list.size();
		int total_pages = list_size / MTX_SIZE;
		current_page = Math.clamp(current_page, 0, total_pages);
		int j = current_page * MTX_SIZE;
		
		boolean last_page = (current_page == total_pages);
		if (list_size >= MTX_SIZE && last_page) list_size = list_size % MTX_SIZE;
		
		for(int i = 0; i < MTX_SIZE; i++){
			if (i < list_size) {
				to_show[i] = p_list.get(j);
				j++;
			} else {
				to_show[i] =  new Product("", 0f, 0, 0, null);
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

    public static void browsePage(SwipePage operation) {
		switch(operation) {
		  case NEXT:
			  current_page ++;
		    break;
		  case PREV:				  
			    current_page --;
		    break;
		  default:
			  
		} 
    }
	
	
}
