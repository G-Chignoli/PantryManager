package view;

import java.awt.event.ActionEvent;
import java.time.LocalDate;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.ProductManager;

@SuppressWarnings("serial")
public class SaveProductAction extends AbstractAction {
	public static final Logger logger = LogManager.getLogger(SaveProductAction.class);

	SaveProductAction(){
		super("Salva Prodotto");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = MainWindow.getFormName();

		if (name.equals("")) {
			errorMessage("Inserisci un nome valido.");
		} else {
			if (ProductManager.saveProduct(
					name, 
					toFloat(MainWindow.getFormWeight()), 
					toInt(MainWindow.getFormQty()),
					toInt(MainWindow.getFormCal()),
					(LocalDate) MainWindow.getExpDate()) == 0) {
				JOptionPane.showMessageDialog(
						MainWindow.getMainFrame(), 
						"Prodotto Aggiunto.");			
				
				MainWindow.matrixInit();
			} else errorMessage("Non è stato possibile salvare il prodotto (prodotto già inserito).");
			
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
	
	private void errorMessage(String s) {
		JOptionPane.showMessageDialog(
				MainWindow.getMainFrame(), 
				s, 
				"Qualcosa è andato storto...",
			    JOptionPane.ERROR_MESSAGE);
	}
}
