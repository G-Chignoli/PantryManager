package view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ErrorWindow {
	
	private static JFrame main_frame = new JFrame();
	private static JLabel err_lb = new JLabel("Qualcosa è andato storto...");
	private static JButton ok_bt = new JButton("Ok");
	private static JPanel main_panel = new JPanel();
	private static JLabel displayed_msg = new JLabel();
	private static boolean mouse_out = false;
	
	
	public void ErrorWindow() {
		
	}
	
	public ErrorWindow(String msg) {
		ErrorWindow.displayed_msg.setText(msg);
		initialize();
	}
	
	public static void initialize() {
		
		main_frame.setResizable(false);
		main_frame.setTitle("Errore");
		main_frame.setBounds(100, 100, 250, 150);
		main_frame.setAutoRequestFocus(true);
		main_frame.setUndecorated(false);
		main_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main_frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		main_frame.setLocationRelativeTo((Component) MainWindow.getMainFrame());
		
		main_panel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		
		main_frame.add(main_panel, "cell 0 0");
		
		main_panel.add(err_lb, "cell 0 0, alignx center, aligny center");
		main_panel.add(displayed_msg, "cell 0 1, alignx center, aligny center");
		main_panel.add(ok_bt, "cell 0 2, alignx center, aligny center");
		
		MainWindow.deactivate();
		ok_bt.addActionListener(
				new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainWindow.activate();
					closeWindow();
				}
			});
		
		main_frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	MainWindow.activate();
		    }
		});
		
		show();
		
	}
	
    public static void show() {
    	ErrorWindow.main_frame.setVisible(true);
    }
    
    private static void closeWindow() {
    	main_frame.dispose();
    }
    

}
