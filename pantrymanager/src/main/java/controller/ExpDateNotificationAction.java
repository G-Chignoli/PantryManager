package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JDialog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.MainWindow;

@SuppressWarnings("serial")
public class ExpDateNotificationAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dialog = new JDialog(MainWindow.getMainFrame(), "Prodotti in Scadenza");
	}
	
}
