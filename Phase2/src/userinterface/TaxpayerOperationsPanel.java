package userinterface;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JButton;
import javax.swing.JPanel;
import datamanager.Taxpayer;
import outputmanager.BarChartWindow;
import outputmanager.PieChartWindow;

public class TaxpayerOperationsPanel extends JPanel {

	protected PropertyChangeSupport propertyChangeSupport;
	private Taxpayer taxpayer = null;
	private String action = null;
	private JButton showDetailsButton;
	private JButton showReceiptsButton;
	private JButton showBarChartButton;
	private JButton showPieChartButton;
	private JButton createLogButton = new JButton("Αποθήκευση στοιχείων");
	private JButton deleteTaxpayerButton =
			new JButton("Διαγραφή φορολογουμένου");

	public TaxpayerOperationsPanel() {
		setPanelProperties();
		initializeButtons();
	}

	private void setPanelProperties() {
		setBounds(0, 183, 604, 160);
		setLayout(null);
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	private void initializeButtons() {
		addShowDetailsButtons();
		addShowChartButtons();
		addUtilityButtons();
		setActionListeners();
	}

	private void addShowDetailsButtons() {
		showDetailsButton = new JButton("Εμφάνιση στοιχείων");
		showDetailsButton.setBounds(10, 11, 285, 39);
		showDetailsButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		showReceiptsButton = new JButton("Εμφάνιση λίστας αποδείξεων");
		showReceiptsButton.setBounds(309, 11, 285, 39);
		showReceiptsButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		add(showDetailsButton);
		add(showReceiptsButton);
	}

	private void addShowChartButtons() {
		showBarChartButton = new JButton("Ραβδόγραμμα φόρων");
		showBarChartButton.setBounds(10, 61, 285, 39);
		showBarChartButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		showPieChartButton = new JButton("Διάγραμμα πίτας αποδείξεων");
		showPieChartButton.setBounds(309, 61, 285, 39);
		showPieChartButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		add(showBarChartButton);
		add(showPieChartButton);
	}

	private void addUtilityButtons() {
		createLogButton.setBounds(10, 111, 285, 39);
		createLogButton.setToolTipText("Δημιουργία αρχείου αναφοράς");
		createLogButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		deleteTaxpayerButton.setBounds(309, 111, 285, 39);
		deleteTaxpayerButton.setToolTipText("Αφαίρεση από τη λίστα");
		deleteTaxpayerButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		add(createLogButton);
		add(deleteTaxpayerButton);
	}

	private void setActionListeners() {
		setShowDetailsButtonListener();
		setShowReceiptsButtonListener();
		setShowBarChartButtonListener();
		setShowPieChartButtonListener();
		setCreateLogButtonListener();
		setDeleteButtonListener();
	}

	private void setShowDetailsButtonListener() {
		showDetailsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
					if (taxpayerIsNull()) {
						setAction("None selected");
					} else {
						setAction("Show details");
					}
			} });
	}

	private boolean taxpayerIsNull() {
		return (taxpayer == null);
	}

	private void setAction(String action) {
		this.action = action;
		propertyChangeSupport.firePropertyChange(action, null, null);
	}

	private void setShowReceiptsButtonListener() {
		showReceiptsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				if (taxpayerIsNull()) {
					setAction("None selected");
				} else {
					setAction("Show receipts");
				}
			} });
	}

	private void setShowBarChartButtonListener() {
		showBarChartButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				if (taxpayerIsNull()) {
					setAction("None selected");
				} else {
					BarChartWindow barChart = new BarChartWindow(taxpayer);
				}
			} });
	}

	private void setShowPieChartButtonListener() {
		showPieChartButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				if (taxpayerIsNull()) {
					setAction("None selected");
				} else {
					PieChartWindow pieChart = new PieChartWindow(taxpayer);
				}
			} });
	}

	private void setCreateLogButtonListener() {
		createLogButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				if (taxpayerIsNull()) {
					setAction("None selected");
				} else {
					setAction("Create log");
				}
			} });
	}

	private void setDeleteButtonListener() {
		deleteTaxpayerButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				if (taxpayerIsNull()) {
					setAction("None selected");
				} else {
					setAction("Delete");
				}
			} });
	}

	public void setTaxpayer(Taxpayer taxpayer) {
		this.taxpayer = taxpayer;
	}

	public String getAction() {
		return action;
	}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

}
