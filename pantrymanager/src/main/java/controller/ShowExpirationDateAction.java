package controller;

import java.awt.event.ActionEvent;
import model.ExpirationDateManager;
import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ShowExpirationDateAction extends AbstractAction {

	public ShowExpirationDateAction(){
		super("Prodotti in scadenza");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		new ExpirationDateManager();
	}

}
