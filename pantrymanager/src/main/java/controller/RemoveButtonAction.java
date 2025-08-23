package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.MainWindow;

public class RemoveButtonAction extends AbstractAction{


	public RemoveButtonAction() {
		super("-");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Rimuovo! " + MainWindow.getLastCompHovered());
	}
}