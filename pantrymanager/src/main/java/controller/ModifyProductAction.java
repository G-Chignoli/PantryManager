package controller;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
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
		String name = MainWindow.getInstance().getFormName();
		String qty  = MainWindow.getInstance().getFormQty();
		String weight   = MainWindow.getInstance().getFormWeight();
		String calories  = MainWindow.getInstance().getFormCal();
		LocalDate date = MainWindow.getInstance().getExpDate();
		Product p = new Product();
		JFrame main_frame = MainWindow.getInstance().getMainFrame();
		
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
			errorMessage("Inserisci un nome valido.", main_frame);
		} else {
			if (ProductManager.modifyProduct(p) == 0) {
				
				MainWindow.getInstance().matrixInit();
			
			JOptionPane.showMessageDialog(
					//MainWindow.getInstance().getMainFrame(), 
					main_frame, 
					"Prodotto modificato.");	
			
			} else errorMessage("Il prodotto non è stato modificato (prodotto non trovato)", main_frame);
			
		}
	}
	
	private void errorMessage(String s, JFrame main_frame) {
		JOptionPane.showMessageDialog(
				//MainWindow.getInstance().getMainFrame(), 
				main_frame, 
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
