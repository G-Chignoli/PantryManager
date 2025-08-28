package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import model.ProductManager;
import view.MainWindow;

@SuppressWarnings("serial")
public class DeleteProductAction extends AbstractAction {

	public DeleteProductAction(){
		super("Elimina Prodotto");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = MainWindow.getInstance().getFormName();

		if (name.equals("")) {
			errorMessage("Inserisci un nome valido.");
		} else {
			if (ProductManager.deleteProduct(name) == 0) {
				
			MainWindow.getInstance().matrixInit();
			
			JOptionPane.showMessageDialog(
					MainWindow.getInstance().getMainFrame(), 
					"Prodotto Eliminato.");	
			
			} else errorMessage("Il prodotto non è stato eliminato (prodotto non trovato)");
			
		}
	}
	
	private void errorMessage(String s) {
		JOptionPane.showMessageDialog(
				MainWindow.getInstance().getMainFrame(), 
				s, 
				"Qualcosa è andato storto...",
			    JOptionPane.ERROR_MESSAGE);
	}
	
}
