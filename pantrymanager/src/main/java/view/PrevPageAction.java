package view;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class PrevPageAction extends AbstractAction implements Action {

	public PrevPageAction() {
		super("<<<<");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MatrixRenderer.setCurrentPage(MatrixRenderer.getCurrentPage() - 1);
		MainWindow.renderMatrix(MatrixRenderer.fillMtx(MainWindow.getProductSearchText()));
		System.out.println(MatrixRenderer.getCurrentPage());
	}

}
