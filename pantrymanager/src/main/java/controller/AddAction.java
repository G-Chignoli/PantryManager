package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.MainWindow;

public class AddAction extends AbstractAction{

	public AddAction() {
		super("+");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Aggiungo! " + MainWindow.getLastCompHovered());
	}
}