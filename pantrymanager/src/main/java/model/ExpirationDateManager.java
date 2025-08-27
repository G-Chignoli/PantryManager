package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import view.MainWindow;

public class ExpirationDateManager {
	
	public ExpirationDateManager() {
		initialize();
	}
	
	private void initialize() {
		final int MAX_DAYS = 3;
		List<Product> products = ProductManager.getProducts();
		String output = "";

		for (int i = 0; i < products.size(); i++) {
			LocalDate date = products.get(i).getExpirationDate();
			
			if (date != null) {
				int days_to_exp = (int) ChronoUnit.DAYS.between(LocalDate.now(), date);
				if( days_to_exp <= MAX_DAYS && days_to_exp >= 0) {
					output = output + StringUtils.capitalize(products.get(i).getName()) + " in scadenza il " + date + "\n";
				} 
			}
			
		}
		JOptionPane.showMessageDialog(MainWindow.getInstance().getMainFrame(), output);
	}
	
	
	
}
