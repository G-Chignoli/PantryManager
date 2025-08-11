package view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class AddAction extends AbstractAction{


	public AddAction() {
		super("+");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Aggiungo! " + MainWindow.getLastCompHovered());
	}
}