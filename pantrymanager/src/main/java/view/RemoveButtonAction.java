package view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class RemoveButtonAction extends AbstractAction{


	public RemoveButtonAction() {
		super("-");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Rimuovo! " + MainWindow.getLastCompHovered());
	}
}