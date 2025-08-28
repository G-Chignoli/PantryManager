package view;

import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow.getInstance().initialize();
				MainWindow.getInstance().show();
				MainWindow.getInstance().showExpProducts();
				
			}
		});
	}

}