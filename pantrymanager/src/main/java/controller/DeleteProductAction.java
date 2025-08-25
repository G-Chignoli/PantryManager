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
		String name = MainWindow.getFormName();

		if (name.equals("")) {
			errorMessage("Inserisci un nome valido.");
		} else {
			if (ProductManager.deleteProduct(name) == 0) {
				
			MainWindow.matrixInit();
			
			JOptionPane.showMessageDialog(
					MainWindow.getMainFrame(), 
					"Prodotto Eliminato.");	
			
			} else errorMessage("Il prodotto non è stato eliminato (prodotto non trovato)");
			
		}
	}
	
	private void errorMessage(String s) {
		JOptionPane.showMessageDialog(
				MainWindow.getMainFrame(), 
				s, 
				"Qualcosa è andato storto...",
			    JOptionPane.ERROR_MESSAGE);
	}
	
}
