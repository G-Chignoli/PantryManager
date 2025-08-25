package view;

import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow main_window = new MainWindow();
				main_window.initialize();
				main_window.show();
				main_window.showExpDates();
				
			}
		});
	}

}