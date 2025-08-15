package view;

import model.ProductManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.Action;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import java.awt.Graphics;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.SwingUtilities;
import javax.swing.text.DateFormatter;

import com.toedter.calendar.JDateChooser;

public class MainWindow {

	private JFrame main_frame;
	private JTextField p_search;
	public static String last_comp_hovered = null;

	public void initialize() {
		main_frame = new JFrame();
		main_frame.setResizable(false);
		main_frame.setTitle("PantryManager");
		main_frame.setBounds(100, 100, 1300, 730);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		//main_frame.setLayout(new MigLayout("", "[300.00px][1000.00px]"));
		
		JPanel[][] panels = new JPanel[3][3];
		JLabel[][] labels = new JLabel[3][3];
		JButton[][] buttons = new JButton[3][3];
		Action add_action = new AddAction();
		RemoveButtonAction rmv_action = new RemoveButtonAction();
		
		
		JPanel profile_pl = new JPanel();
		main_frame.getContentPane().add(profile_pl);
		profile_pl.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow]"));
		
		JLabel profile_title = new JLabel("PROFILI");
		profile_pl.add(profile_title, "cell 1 0,alignx center,aligny top");
		
		// Product Form to add 
		
		JPanel form_pl = new JPanel();
		form_pl.setBackground(new Color(200, 200, 255));
		form_pl.setLayout(new MigLayout("", "[210px][][210px]", "[grow][grow][grow][grow][grow]"));
		
		profile_pl.add(form_pl, "cell 1 1, grow, alignx center, aligny center");
		
		JLabel form_title = new JLabel("AGGIUNGI PRODOTTO"); 
		form_pl.add(form_title, "cell 1 0, alignx center, aligny top");
		
		JLabel form_name = new JLabel("Nome"); 
		form_pl.add(form_name, "cell  0 1, alignx right, aligny center");
		
		JTextField form_name_tf = new JTextField(15);
		form_name_tf.setToolTipText("Inserisci il nome del prodotto che vuoi salvare.");
		form_pl.add(form_name_tf, "cell 1 1, alignx left, aligny center");
		
		JLabel form_qty = new JLabel("Quantità"); 
		form_pl.add(form_qty, "cell  0 2, alignx right, aligny center");

		JTextField form_qty_tf = new JTextField(15);
		form_name_tf.setToolTipText("Inserisci quante unità vuoi salvare.");
		form_pl.add(form_qty_tf, "cell 1 2, alignx left, aligny center");
				
		JLabel form_exp_date = new JLabel("Data di Scadenza"); 
		form_pl.add(form_exp_date, "cell  0 3, alignx right, aligny center");
		
	    /*
		JTextField form_exp_date_tf = new JTextField(20);
		form_exp_date_tf.setToolTipText("Inserisci data");
		*/
		
		JDateChooser chooser = new JDateChooser(new Date(), "dd-mm-yyyy"); 
		form_pl.add(chooser, "cell 1 3");

		
		JPanel product_pl = new JPanel();
		main_frame.getContentPane().add(product_pl);
		product_pl.setLayout(new MigLayout("", "[100px:n:100px,grow][100px:n:100px,grow][195px:n:195px,grow][100px:n:100px,grow][100px:n:100px,grow]", "[grow][][grow][grow][grow]"));
		
		JLabel product_title = new JLabel("PRODOTTI");
		product_title.setBackground(new Color(128, 128, 255));
		product_pl.add(product_title, "cell 2 0,alignx center,aligny top");
		
		JButton p_search_left = new JButton("<--");
		product_pl.add(p_search_left, "cell 0 0 1 2,grow");
		
		p_search = new JTextField();
		p_search.setText("Cerca prodotto");
		product_pl.add(p_search, "cell 1 1 2 1,grow");
		p_search.setColumns(10);
		
		JButton p_search_send = new JButton("Invia");
		product_pl.add(p_search_send, "cell 3 1,growx,aligny center");
		
		JButton p_search_right = new JButton("-->");
		product_pl.add(p_search_right, "cell 4 0 1 2,grow");
		
		// Products Matrix Panel
		
		JPanel products_pl = new JPanel();
		products_pl.setBackground(new Color(192, 192, 192));
		product_pl.add(products_pl, "cell 0 2 5 3,grow");
		products_pl.setLayout(new MigLayout("", "[170px][170px][170px]", "[170px][170px][170px]"));
		
		//productsMatrixInit(products_pl, panels, labels);
		
		
		
		//System.out.println(add.getRootPane());
		
		
		
		int color = 255;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JPanel p = new JPanel();
				JLabel name = new JLabel("Nome Prodotto " + i + j);
				JLabel qty = new JLabel("2");
				JButton add = new JButton(add_action);
				JButton remove = new JButton(rmv_action);
				
				
				p.setName("p" + i + j);
				p.setLayout(new MigLayout("", "[65px:n:65px][40px:n:40px][65px:n:65px]", "[100px:n:100px][70px:n:70px]"));
				p.setBackground(new Color(color, color, 255));
				p.setOpaque(true);
				
				/*
				add.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						System.out.println(e.getComponent().getParent().getName());
					}
				});
				*/
				
				p.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						//System.out.println(e.getComponent().getName());
						last_comp_hovered = e.getComponent().getName();
					}
				});
				
				panels[i][j] = p;
				labels[i][j] = name;
				buttons[i][j] = add;


				p.add(name, "cell 0 0 3 1, alignx center, aligny center");
				p.add(add, "cell 2 1, grow, alignx center, aligny center");
				p.add(qty, "cell 1 1, alignx center, aligny center");
				p.add(remove, "cell 0 1, grow, alignx center, aligny center");
				products_pl.add(p, "cell " + j + " " + i +  ", grow");
				
				color -= 30;
			}
		}
	}
	
	
	/*
	private void productsMatrixInit(JPanel products_pl, JPanel[][] panels, JLabel[][] labels) {
		//JPanel[][] panels = new JPanel[3][3];
		//JLabel[][] labels = new JLabel[3][3];
		// i colonne
		// j righe
		int color = 255;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JPanel p = new JPanel();
				JLabel name = new JLabel("Nome Prodotto " + color);
				JLabel qty = new JLabel("2");
				JButton add = new JButton(add_Action);
				JButton remove = new JButton("-");
				
				p.setLayout(new MigLayout("", "[65px:n:65px][40px:n:40px][65px:n:65px]", "[100px:n:100px][70px:n:70px]"));
				p.setBackground(new Color(color, color, 255));
				p.setOpaque(true);
				
				panels[i][j] = p;
				labels[i][j] = name;

				p.add(name, "cell 0 0 3 1, alignx center, aligny center");
				p.add(add, "cell 0 1, grow, alignx center, aligny center");
				p.add(qty, "cell 1 1, alignx center, aligny center");
				p.add(remove, "cell 2 1, grow, alignx center, aligny center");
				products_pl.add(p, "cell " + j + " " + i +  ", grow");
				
				color -= 30;
			}
		}
	}
	
	 */
    public void show() {
    	this.main_frame.setVisible(true);
    	
    }
    
    public static String getLastCompHovered() {
    	return last_comp_hovered;
    }
}
/*

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import javax.swing.*;

public class MainWindow{
		
		private JFrame frame;
		private JPanel left_panel;
		private JPanel title_panel;
		
		private JLabel product_title;
		private JTextField textField;
		private JButton save_b;
		private JButton delete_b;
		
		private static final int WIDTH = 1200;
		private static final int HEIGHT = 700;
	
	    public void main(String[] args)
	    {
	    	initialize();
	    }
	    
	    public MainWindow() {
	    	initialize();
	    }
	    
	    public void initialize() {
	    	frame = new JFrame();
	    	frame.setTitle("PantryManager");
	    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	frame.setSize(WIDTH, HEIGHT);
	    	frame.setLayout(new BorderLayout(10,10));
	    	frame.setLocationRelativeTo(null);

	    	//frame.setBounds(0, 0, WIDTH, HEIGHT);
	    	//frame.setResizable(false);
	    	
	    	//PANEL SETUP
	    	left_panel = new JPanel(new BorderLayout());
	    	left_panel.setBackground(new Color(215,216,230));
	    	title_panel = new JPanel(new BorderLayout());
	    	title_panel.setBackground(new Color(200,200,200));
	    	
	    	
	    	//BUTTON SETUP
	    	save_b = new JButton("SAVE");
	    	save_b.setPreferredSize(new Dimension(100, 100));
	    	delete_b = new JButton("DELETE");
	    	delete_b.setPreferredSize(new Dimension(100, 100));
	    	
	    	//LABEL SETUP
	    	product_title = new JLabel("PRODOTTI");
	    	
	    	
	    	//panel = new JPanel();
	    	//panel.setBackground(new Color(173,216,230));
	    	//label = new JLabel("Nome prodotto");
	    	//p1 = new JLabel(ProductManager.getProducts().getFirst().getName().toUpperCase());
	    	textField = new JTextField(20);
	    	//button.setBounds(150, 200, 220, 50);
	    	
	    	//button.addActionListener(al);
	    	
	    	//panel.add(label);
	    	//panel.add(textField);
	    	//panel.add(button);
	    	//panel.add(button2);
	    	
	    	title_panel.add(product_title, BorderLayout.NORTH);
	    	left_panel.add(save_b, BorderLayout.EAST);
	    	left_panel.add(delete_b, BorderLayout.WEST);
	    	frame.add(left_panel, BorderLayout.LINE_START);
	    	
	    }
	    
	    public void show() {
	    	this.frame.setVisible(true);
	    	
	    }
	    
	    ActionListener al = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	    System.out.println(textField.getText());
	        }
	    };

	    ActionListener al2 = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	    System.out.println(textField.getText().toUpperCase());
	        }
	    };
}
*/