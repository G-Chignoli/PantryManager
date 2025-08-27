package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

import view.MainWindow;
import view.MatrixRenderer;
import view.SwipePage;

public class NextPageAction extends AbstractAction implements Action {

	public NextPageAction() {
		super(">>>>");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//MatrixRenderer.setCurrentPage(MatrixRenderer.getCurrentPage() + 1);
		MatrixRenderer.browsePage(SwipePage.NEXT);
		MainWindow.matrixInit();
		//MainWindow.matrixInit(MatrixRenderer.getProductsToShow(MainWindow.getProductSearchText()));
		System.out.println(MatrixRenderer.getCurrentPage());
	}

}
