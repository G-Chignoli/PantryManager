package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.Product;
import model.ProductManager;
import view.MainWindow;

@SuppressWarnings("serial")
public class AddProductAction extends AbstractAction{

	public AddProductAction() {
		super("+");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Product product = ProductManager.getProductsByName(MainWindow.getLastCompHovered()).getFirst();
		int qty = product.getQty();
		
		qty++;
		product.setQty(qty);
		
		ProductManager.modifyProduct(product);
		
		MainWindow.matrixInit();
	}
}