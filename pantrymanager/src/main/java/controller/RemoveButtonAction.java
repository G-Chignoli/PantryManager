package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import model.Product;
import model.ProductManager;
import view.MainWindow;

@SuppressWarnings("serial")
public class RemoveButtonAction extends AbstractAction{


	public RemoveButtonAction() {
		super("-");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Product product = ProductManager.getProductsByName(MainWindow.getInstance().getLastCompHovered()).getFirst();
		int qty = product.getQty();
		
		if (qty > 0) {
			qty--;
			product.setQty(qty);
			
			ProductManager.mergeProduct(product);
		}
		
		MainWindow.getInstance().matrixInit();
	}
}