package controller;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Product;
import model.ProductManager;
import view.MainWindow;

@SuppressWarnings("serial")
public class ModifyProductAction extends AbstractAction {
	
	private static Logger logger = LogManager.getLogger(ModifyProductAction.class);
	
	public ModifyProductAction(){
		super("Modifica Prodotto");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = MainWindow.getFormName();
		String qty  = MainWindow.getFormQty();
		String weight   = MainWindow.getFormWeight();
		String calories  = MainWindow.getFormCal();
		LocalDate date = MainWindow.getExpDate();
		Product p = new Product();
		
		try {
			p = ProductManager.getProductsByName(name).getFirst();
		} catch (NoSuchElementException ex) {
			logger.error("Il prodotto non è stato trovato"); 
		}

		
		if(!qty.isBlank()) p.setQty(toInt(qty));
		if(!weight.isBlank()) p.setWeight(toFloat(weight));
		if(!calories.isBlank()) p.setCalories(toInt(calories));
		if(date!=null) p.setExpirationDate(date); 
			
		if (name.equals("")) {
			errorMessage("Inserisci un nome valido.");
		} else {
			if (ProductManager.modifyProduct(p) == 0) {
				
				MainWindow.matrixInit();
			
			JOptionPane.showMessageDialog(
					MainWindow.getMainFrame(), 
					"Prodotto modificato.");	
			
			} else errorMessage("Il prodotto non è stato modificato (prodotto non trovato)");
			
		}
	}
	
	private void errorMessage(String s) {
		JOptionPane.showMessageDialog(
				MainWindow.getMainFrame(), 
				s, 
				"Qualcosa è andato storto...",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private float toFloat(String s){
		float t;
		try {
			t = Float.parseFloat(s);
		}
		catch (NumberFormatException e) {
			t=0;
		}
		return Math.abs(t);
	}
	private int toInt(String s){
		int t;
		try {
			t = Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			t=0;
		}
		return Math.abs(t);
	}
	
	
}
