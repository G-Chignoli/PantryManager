package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

import view.MainWindow;

public class SearchMtxAction extends AbstractAction implements Action {
	
	public SearchMtxAction() {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//MainWindow.matrixInit(MatrixRenderer.getProductsToShow(MainWindow.getProductSearchText()));
		MainWindow.matrixInit();
	}

}
