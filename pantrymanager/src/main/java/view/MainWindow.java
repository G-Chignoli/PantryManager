package view;

import org.apache.commons.lang3.StringUtils;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Action;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Font;

import model.OperationMode;

public class MainWindow {

	private static JFrame main_frame;
	protected static JTextField p_search;
	public static String last_comp_hovered = null;
	private static Action add_action = new AddAction();
	private static Action rmv_action = new RemoveButtonAction();
	private static Action search_action = new SearchMtxAction();
	private static Action next_page = new NextPageAction();
	private static Action prev_page = new PrevPageAction();
	private static JPanel[][] panels = new JPanel[3][3];
	private static JLabel[][] labels = new JLabel[3][3];
	private static JButton[][] buttons = new JButton[3][3];
	private static JPanel p_matrix_pl = new JPanel();
	
	public void initialize() {
		
		main_frame = new JFrame();
		main_frame.setResizable(false);
		main_frame.setTitle("PantryManager");
		main_frame.setBounds(100, 100, 1300, 730);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		
		// LEFT PANEL //
		
		JPanel left_pl = new JPanel();
		left_pl.setLayout(new MigLayout("", "[grow]", "[][]"));
		main_frame.getContentPane().add(left_pl);
		
		JPanel profile_pl = new JPanel();
		JPanel form_pl = new JPanel();
		
		left_pl.add(profile_pl, "cell 0 0");
		left_pl.add(form_pl, "cell 0 1");
		
		
		// PROFILE PANEL SETUP //
		JLabel profile_title = new JLabel("< PROFILI >");

		profile_pl.setLayout(new MigLayout("fillx, filly", "[650px]", "[360px]"));
		profile_pl.setBackground(Color.LIGHT_GRAY);
		profile_pl.add(profile_title, "alignx center, aligny center");
		
		
		// PRODUCT FORM PANEL // 
		JLabel form_title = new JLabel("< GESTISCI PRODOTTO >");
		JLabel form_name = new JLabel("Nome"); 
		JLabel form_qty = new JLabel("Quantità"); 
		JLabel form_weight = new JLabel("Peso Unità"); 
		JLabel form_exp_date = new JLabel("Data di Scadenza"); 
		JTextField form_name_tf = new JTextField(15);
		JTextField form_qty_tf = new JTextField(15);
		JTextField form_weight_tf = new JTextField(15);
		JDateChooser exp_date_chooser = new JDateChooser(new Date(), "dd-mm-yyyy"); 
		JButton save_product = new JButton("Salva Prodotto");
		JButton delete_product = new JButton("Cancella Prodotto");
		JButton modify_product = new JButton("Modifica Prodotto");
		
		form_pl.setBackground(new Color(200, 200, 255));
		form_pl.setLayout(new MigLayout("", "[200px][200px][200px]", "[][grow][grow][grow][grow][grow]"));
		//profile_pl.add(form_pl, "cell 1 3, alignx center, aligny center");

		//form_title.setForeground(Color.BLUE);
		
		form_name_tf.setToolTipText("Inserisci il nome del prodotto che vuoi salvare.");
		form_qty_tf.setToolTipText("Inserisci quante unità vuoi salvare.");
		form_weight_tf.setToolTipText("Inserisci il peso unitario del prodotto.");
		//profile_pl.add(form_title, "cell 1 2, alignx center, aligny center");
		
		
		form_pl.add(form_title, "cell 1 0, alignx center, aligny center");
		form_pl.add(form_name, "cell  0 1, alignx right, aligny center");
		form_pl.add(form_name_tf, "cell 1 1, alignx left, aligny center");
		form_pl.add(form_qty, "cell  0 2, alignx right, aligny center");
		form_pl.add(form_qty_tf, "cell 1 2, alignx left, aligny center");
		form_pl.add(form_weight, "cell  0 3, alignx right, aligny center");
		form_pl.add(form_weight_tf, "cell 1 3, alignx left, aligny center");
		form_pl.add(form_exp_date, "cell  0 4, alignx right, aligny center");
		form_pl.add(exp_date_chooser, "cell 1 4");
		form_pl.add(save_product, "cell 0 5, grow, grow");	
		form_pl.add(delete_product, "cell 1 5, grow, grow");	
		form_pl.add(modify_product, "cell 2 5, grow, grow");	
		
		

		// MATRICE PRODOTTI // 
		JPanel product_pl = new JPanel();
		main_frame.getContentPane().add(product_pl);
		product_pl.setLayout(new MigLayout("", "[100px:n:100px,grow][100px:n:100px,grow][195px:n:195px,grow][100px:n:100px,grow][100px:n:100px,grow]", "[grow][][grow][grow][grow]"));
		
		JLabel product_title = new JLabel("PRODOTTI");
		product_title.setBackground(new Color(128, 128, 255));
		product_pl.add(product_title, "cell 2 0,alignx center,aligny top");
		
		JButton p_search_left = new JButton(prev_page);
		product_pl.add(p_search_left, "cell 0 0 1 2,grow");
		
		JButton p_search_right = new JButton(next_page);
		product_pl.add(p_search_right, "cell 4 0 1 2,grow");

		p_search = new JTextField();
		p_search.setToolTipText("Inserisci il nome del prodotto che vuoi cercare.");
		p_search.addActionListener(search_action);
		product_pl.add(p_search, "cell 1 1 2 1,grow");
		p_search.setColumns(10);
		
		JButton p_search_send = new JButton(search_action);
		p_search_send.setText("Invia");
		product_pl.add(p_search_send, "cell 3 1,growx,aligny center");
		
		// Products Matrix Panel
		
		//matrixInit(MatrixRenderer.getProductsToShow());
		matrixInit();
		product_pl.add(p_matrix_pl, "cell 0 2 5 3,grow");
		
	}
	
	public static void matrixInit() {
		//public static void matrixInit(String[] p_names) {
		//MatrixRenderer.getProductsToShow(MainWindow.getProductSearchText();
		String[] p_names = MatrixRenderer.getProductsToShow(MainWindow.getProductSearchText());
		int color = 255;
		int k = 0;
		
		p_matrix_pl.setBackground(new Color(192, 192, 192));
		p_matrix_pl.setLayout(new MigLayout("", "[170px][170px][170px]", "[170px][170px][170px]"));
		p_matrix_pl.removeAll();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JPanel p = new JPanel();
				JLabel name = new JLabel(StringUtils.capitalize(p_names[k]));
				JLabel qty = new JLabel("");
				JButton add = new JButton(add_action);
				JButton remove = new JButton(rmv_action);
				
				p.setName("p" + i + j);
				p.setLayout(new MigLayout("", "[65px:n:65px][40px:n:40px][65px:n:65px]", "[100px:n:100px][70px:n:70px]"));
				p.setBackground(new Color(color, color, 255));
				p.setOpaque(true);
				
				p.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
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
				p_matrix_pl.add(p, "cell " + j + " " + i +  ", grow");
				
				color -= 10;
				k++;
			}
		}
		
		main_frame.revalidate();
	}
	
	public static String getProductSearchText() {
		return p_search.getText();
	}
	
    public void show() {
    	this.main_frame.setVisible(true);
    }
    
    public static String getLastCompHovered() {
    	return last_comp_hovered;
    }
    
}
