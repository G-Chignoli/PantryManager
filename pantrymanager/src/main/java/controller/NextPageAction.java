package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

import view.MainWindow;
import view.MatrixRenderer;
import view.SwipePage;

@SuppressWarnings("serial")
public class NextPageAction extends AbstractAction implements Action {

	public NextPageAction() {
		super(">>>>");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MatrixRenderer.browsePage(SwipePage.NEXT);
		MainWindow.getInstance().matrixInit();
	}

}
