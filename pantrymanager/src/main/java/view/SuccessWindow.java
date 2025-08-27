package view;

import java.awt.Component;
import java.awt.Frame;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class SuccessWindow {
	
	private static JFrame main_frame = new JFrame();
	private static JLabel err_lb = new JLabel("Fatto!");
	private static JPanel main_panel = new JPanel();
	private static JLabel displayed_msg = new JLabel();
	
	public SuccessWindow(String msg) {
		JDialog dialog = new JDialog( MainWindow.getMainFrame(), "Fatto!");
		JButton ok_bt = new JButton("Ok");
		JLabel l = new JLabel(msg);
		JPanel main_pl = new JPanel(new MigLayout("", "[grow]", "[grow]"));
		
		
		dialog.setLayout(new MigLayout("", "[grow]", "[grow]"));
		dialog.setLocationRelativeTo(MainWindow.getMainFrame());
		//dialog.add(l);
		dialog.setSize(200, 150);
		//dialog.setResizable(false);
		dialog.setVisible(true);
		dialog.add(main_pl, "cell 0 0");
		main_pl.add(ok_bt, "cell 0 0");
		
		ok_bt.addActionListener(
				new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//MainWindow.activate();
					dialog.dispose();
					//closeWindow();
				}
			});
		
		
	}
	

	/*
	public SuccessWindow(String msg) {
		SuccessWindow.displayed_msg.setText(msg);
		initialize();
	}
	
	public static void initialize() {
		
		main_frame.setResizable(false);
		main_frame.setBounds(100, 100, 250, 150);
		main_frame.setAutoRequestFocus(true);
		main_frame.setUndecorated(true);
		main_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main_frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		main_frame.setLocationRelativeTo((Component) MainWindow.getMainFrame());
		
		main_panel.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		main_frame.add(main_panel, "cell 0 0");
		
		main_panel.add(displayed_msg, "cell 0 1, alignx center, aligny center");
		main_panel.add(ok_bt, "cell 0 2, alignx center, aligny center");
		
		//MainWindow.deactivate();
		ok_bt.addActionListener(
				new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//MainWindow.activate();
					closeWindow();
				}
			});
		
		main_frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent windowEvent) {
				System.out.println("APERTA");
			}
		});
		
		/*
		main_panel.addMouseListener(
				new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(mouse_out) closeWindow();	
					System.out.println("clicco");
				}

				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					mouse_out = false;		
					System.out.println(mouse_out);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					mouse_out = true;	
					System.out.println(mouse_out);
				}
			});
		
		
		show();
		
		
		
	}
	 */
	
    public static void show() {
    	SuccessWindow.main_frame.setVisible(true);
    }
    
    private static void closeWindow() {
    	main_frame.dispose();
    }
    

}
