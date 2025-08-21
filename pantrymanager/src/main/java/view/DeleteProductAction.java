package view;

import java.awt.event.ActionEvent;
import java.time.LocalDate;

import javax.swing.AbstractAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.ProductManager;

@SuppressWarnings("serial")
public class DeleteProductAction extends AbstractAction {
	public static final Logger logger = LogManager.getLogger(ProductManager.class);

	DeleteProductAction(){
		super("Rimuovi Prodotto");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = MainWindow.getFormName();

		if (name.equals("")) {
			logger.error("Operazione interrotta, inserisci un nome valido!");
		} else {
			ProductManager.deleteProduct(name); 
			MainWindow.matrixInit();
			logger.info("Prodotto Eliminato!");			
		}
	}
	
}
