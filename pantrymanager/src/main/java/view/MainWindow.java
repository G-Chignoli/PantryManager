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
import javax.swing.WindowConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

import controller.AddProductAction;
import controller.DeleteProductAction;
import controller.ModifyProductAction;
import controller.NextPageAction;
import controller.PrevPageAction;
import controller.RemoveButtonAction;
import controller.SaveProductAction;
import controller.SearchMtxAction;
import controller.ShowExpirationDateAction;
import model.Product;

public class MainWindow {

	private static MainWindow instance;

	private MainWindow() {

	}

	public static MainWindow getInstance() {
		if(instance == null) {
			instance = new MainWindow();
		}

		return instance;
	}

	private JFrame main_frame = new JFrame();
	
	private Action add_action = new AddProductAction();
	private Action rmv_action = new RemoveButtonAction();
	private Action search_action = new SearchMtxAction();
	private Action next_page = new NextPageAction();
	private Action prev_page = new PrevPageAction();
	private Action show_exp_date_action = new ShowExpirationDateAction();

	private JPanel p_matrix_pl = new JPanel();
	private JTextField p_search_tf = new JTextField();
	private JTextField form_name_tf = new JTextField(15);
	private JTextField form_qty_tf = new JTextField(15);
	private JTextField form_weight_tf = new JTextField(15);
	private JTextField form_cal_tf = new JTextField(15);
	private JDateChooser exp_date_chooser = new JDateChooser(new Date(), " dd/MM/yyyy"); 
	private JButton show_exp_date = new JButton(show_exp_date_action);

	private String last_comp_hovered = null;
	private LocalDate exp_date = null;

	public void initialize() {

		main_frame.setResizable(false);
		main_frame.setTitle("PantryManager");
		main_frame.setBounds(100, 100, 1300, 730);
		main_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		main_frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));


		// LEFT PANEL //
		JPanel left_pl = new JPanel();
		JPanel profile_pl = new JPanel();
		JPanel form_pl = new JPanel();
		
		left_pl.setLayout(new MigLayout("", "[grow]", "[][]"));
		left_pl.add(profile_pl, "cell 0 0");
		left_pl.add(form_pl, "cell 0 1");
		main_frame.getContentPane().add(left_pl);


		// PROFILE PANEL SETUP //
		JLabel profile_title = new JLabel("< PROFILI >");

		profile_pl.setLayout(new MigLayout("fillx, filly", "[650px]", "[360px]"));
		profile_pl.setBackground(Color.LIGHT_GRAY);
		profile_pl.add(profile_title, "alignx center, aligny center");


		// PRODUCT FORM PANEL // 
		Action save_product_action = new SaveProductAction();
		Action delete_product_action = new DeleteProductAction();
		Action modify_product_action = new ModifyProductAction();

		JLabel form_title = new JLabel("< GESTISCI PRODOTTO >");
		JLabel form_name = new JLabel("Nome"); 
		JLabel form_qty = new JLabel("Quantità"); 
		JLabel form_weight = new JLabel("Peso Unità"); 
		JLabel form_cal = new JLabel("Calorie per unità");
		JLabel form_exp_date = new JLabel("Data di Scadenza"); 
		JButton save_product = new JButton(save_product_action);
		JButton delete_product = new JButton(delete_product_action);
		JButton modify_product = new JButton(modify_product_action);

		form_pl.setBackground(new Color(200, 200, 255));
		form_pl.setLayout(new MigLayout("", "[200px][200px][200px]", "[][grow][grow][grow][grow][grow][grow][grow][200px]"));

		form_name_tf.setToolTipText("Inserisci il nome del prodotto che vuoi salvare.");
		form_qty_tf.setToolTipText("Inserisci quante unità vuoi salvare.");
		form_weight_tf.setToolTipText("Inserisci il peso unitario del prodotto.");
		form_cal_tf.setToolTipText("Inserisci le calorie del prodotto per unità");

		exp_date_chooser.addPropertyChangeListener("date",
				new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				LocalDate date = toLocalDate();
				if( date.isAfter(LocalDate.now()) ) {
					setExpDate(date);
				} else { setExpDate(null); }
			}
		});


		form_pl.add(form_title, "cell 1 0, alignx center, aligny center");
		form_pl.add(form_name, "cell  0 1, alignx right, aligny center");
		form_pl.add(form_name_tf, "cell 1 1, alignx left, aligny center");
		form_pl.add(form_weight, "cell  0 2, alignx right, aligny center");
		form_pl.add(form_weight_tf, "cell 1 2, alignx left, aligny center");
		form_pl.add(form_qty, "cell  0 3, alignx right, aligny center");
		form_pl.add(form_qty_tf, "cell 1 3, alignx left, aligny center");
		form_pl.add(form_cal, "cell  0 4, alignx right, aligny center");
		form_pl.add(form_cal_tf, "cell 1 4, alignx left, aligny center");
		form_pl.add(form_exp_date, "cell  0 5, alignx right, aligny center");
		form_pl.add(exp_date_chooser, "cell 1 5");
		form_pl.add(save_product, "cell 1 6, grow, grow");	
		form_pl.add(delete_product, "cell 0 6, grow, grow");	
		form_pl.add(modify_product, "cell 2 6, grow, grow");	
		form_pl.add(show_exp_date, "cell 1 7, grow, grow");	


		// PRODUCT MATRIX // 
		JPanel product_pl = new JPanel();
		JLabel product_title = new JLabel("< PRODOTTI >");
		JButton p_search_left = new JButton(prev_page);
		JButton p_search_right = new JButton(next_page);
		JButton p_search_send = new JButton(search_action);

		main_frame.getContentPane().add(product_pl);

		p_search_tf.addActionListener(search_action);
		p_search_tf.setToolTipText("Inserisci il nome del prodotto che vuoi cercare.");
		p_search_tf.setColumns(10);

		product_pl.setLayout(new MigLayout("", "[100px:n:100px,grow][100px:n:100px,grow][195px:n:195px,grow][100px:n:100px,grow][100px:n:100px,grow]", "[grow][][grow][grow][grow]"));
		product_pl.add(product_title, "cell 2 0,alignx center,aligny top");
		product_pl.add(p_search_left, "cell 0 0 1 2,grow");
		product_pl.add(p_search_right, "cell 4 0 1 2,grow");
		product_pl.add(p_search_tf, "cell 1 1 2 1,grow");
		product_pl.add(p_search_send, "cell 3 1,growx,aligny center");
		product_pl.add(p_matrix_pl, "cell 0 2 5 3,grow");

		matrixInit();
	}

	public void matrixInit() {
		Product[] p = MatrixRenderer.getProductsToShow(getProductSearchText());
		int color = 255;
		int k = 0;

		p_matrix_pl.setBackground(new Color(192, 192, 192));
		p_matrix_pl.setLayout(new MigLayout("", "[170px][170px][170px]", "[170px][170px][170px]"));
		p_matrix_pl.removeAll();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				String name = p[k].getName();

				JPanel pl = new JPanel();
				JLabel name_lb = new JLabel(StringUtils.capitalize(name));
				JLabel qty = new JLabel(Integer.toString(p[k].getQty()));
				JButton add = new JButton(add_action);
				JButton remove = new JButton(rmv_action);

				pl.setName(p[k].getName());
				pl.setLayout(new MigLayout("", "[65px:n:65px][40px:n:40px][65px:n:65px]", "[100px:n:100px][70px:n:70px]"));
				pl.setBackground(new Color(color, color, 255));
				pl.setOpaque(true);

				pl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						instance.last_comp_hovered = e.getComponent().getName();
					}
				});

				pl.add(name_lb, "cell 0 0 3 1, alignx center, aligny center");
				pl.add(add, "cell 2 1, grow, alignx center, aligny center");
				pl.add(qty, "cell 1 1, alignx center, aligny center");
				pl.add(remove, "cell 0 1, grow, alignx center, aligny center");
				p_matrix_pl.add(pl, "cell " + j + " " + i +  ", grow");

				if(name.isBlank()) {
					add.setEnabled(false);
					remove.setEnabled(false);
				}

				color -= 10;
				k++;
			}
		}

		getInstance().getMainFrame().revalidate();
	}

	public String getProductSearchText() {
		return this.p_search_tf.getText();
	}

	public void show() {
		this.main_frame.setVisible(true);
	}

	public String getLastCompHovered() {
		return instance.last_comp_hovered;
	}

	public String getFormName() {
		return form_name_tf.getText();
	}

	public String getFormQty() {
		return form_qty_tf.getText();
	}

	public String getFormWeight() {
		return form_weight_tf.getText();
	}

	public String getFormCal() {
		return form_cal_tf.getText();
	}

	public LocalDate toLocalDate() {
		return exp_date_chooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private void setExpDate(LocalDate date) {
		exp_date = date;
	}

	public LocalDate getExpDate() {
		return exp_date;
	}

	public JFrame getMainFrame() {
		return this.main_frame;
	}

	public void showExpProducts() {
		this.show_exp_date.doClick();
	}

}
