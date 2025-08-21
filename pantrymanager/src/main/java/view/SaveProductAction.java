package view;

import java.awt.event.ActionEvent;
import java.time.LocalDate;

import javax.swing.AbstractAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.ProductManager;

@SuppressWarnings("serial")
public class SaveProductAction extends AbstractAction {
	public static final Logger logger = LogManager.getLogger(ProductManager.class);
	float qty = 0;

	SaveProductAction(){
		super("Salva Prodotto");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = MainWindow.getFormName();

		if (name.equals("")) {
			logger.error("Operazione interrotta, inserisci un nome valido!");
		} else {
			ProductManager.saveProduct(
					name, 
					toFloat(MainWindow.getFormWeight()), 
					toInt(MainWindow.getFormQty()),
					toInt(MainWindow.getFormCal()),
					(LocalDate) MainWindow.getExpDate());
			
			logger.info("Prodotto Salvato!");			
		}
	}
	
	private float toFloat(String s){
		float t;
		try {
			t = Float.parseFloat(s);
		}
		catch (NumberFormatException e) {
			logger.warn("Inserisci un valore valido nei campi!");
			t=0;
		}
		return t;
	}
	private int toInt(String s){
		int t;
		try {
			t = Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			logger.warn("Inserisci un valore valido nei campi!");
			t=0;
		}
		return t;
	}
}
