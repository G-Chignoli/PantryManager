package view;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class NextPageAction extends AbstractAction implements Action {

	public NextPageAction() {
		super(">>>>");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//MatrixRenderer.setCurrentPage(MatrixRenderer.getCurrentPage() + 1);
		MatrixRenderer.browsePage(PageOperation.NEXT);
		MainWindow.matrixInit();
		//MainWindow.matrixInit(MatrixRenderer.getProductsToShow(MainWindow.getProductSearchText()));
		System.out.println(MatrixRenderer.getCurrentPage());
	}

}
