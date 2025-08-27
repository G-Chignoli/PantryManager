package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

import view.MainWindow;

@SuppressWarnings("serial")
public class SearchMtxAction extends AbstractAction implements Action {
	
	
	public SearchMtxAction() {
		super("Invia");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//MainWindow.matrixInit(MatrixRenderer.getProductsToShow(MainWindow.getProductSearchText()));
		MainWindow.getInstance().matrixInit();
	}

}
