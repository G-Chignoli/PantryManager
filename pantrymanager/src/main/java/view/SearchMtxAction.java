package view;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class SearchMtxAction extends AbstractAction implements Action {
	
	public SearchMtxAction() {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//MatrixRenderer.fillMtx(MainWindow.getProductSearchText());
		MainWindow.renderMatrix(MatrixRenderer.fillMtx(MainWindow.getProductSearchText()));
	}

}
