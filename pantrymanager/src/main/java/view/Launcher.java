package view;

import javax.swing.SwingUtilities;

import database.DatabaseInit;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DatabaseInit.initialize();
				
				MainWindow.getInstance().initialize();
				MainWindow.getInstance().show();
				MainWindow.getInstance().showExpProducts();
				
				
			}
		});
	}

}