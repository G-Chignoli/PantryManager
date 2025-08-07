package view;

import model.ProductManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import javax.swing.*;

public class MainWindow{
		
		private JFrame frame;
		private JPanel panel;
		private JLabel label;
		private JLabel p1;
		private JLabel p2;
		private JTextField textField;
		private JButton button;
		private JButton button2;
		
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
	    	//frame.setBounds(0, 0, WIDTH, HEIGHT);
	    	frame.setSize(WIDTH, HEIGHT);
	    	frame.setLayout(new BorderLayout(10,10));
	    	//frame.setResizable(false);
	    	frame.setLocationRelativeTo(null);
	    	
	    	panel = new JPanel();
	    	panel.setBackground(new Color(173,216,230));
	    	//label = new JLabel("Nome prodotto");
	    	p1 = new JLabel(ProductManager.getProducts().getFirst().getName().toUpperCase());
	    	p2 = new JLabel();
	    	textField = new JTextField(20);
	    	button = new JButton("SALVA");
	    	button.setBounds(150, 200, 220, 50);
	    	
	    	button.addActionListener(al);
	    	
	    	button2 = new JButton("CANCELLA");
	    	button2.setBounds(150, 200, 220, 50);
	    	
	    	button2.addActionListener(al2);
	    	
	    	//panel.add(label);
	    	panel.add(p1);
	    	panel.add(textField);
	    	panel.add(button);
	    	panel.add(button2);
	    	
	    	frame.add(panel, BorderLayout.CENTER);
	    	
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