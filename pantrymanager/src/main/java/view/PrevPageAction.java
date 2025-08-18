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
		MatrixRenderer.browsePage(SwipePage.PREV);
		MainWindow.matrixInit();
		System.out.println(MatrixRenderer.getCurrentPage());
	}

}
