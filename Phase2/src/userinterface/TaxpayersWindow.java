package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Color;
import java.awt.Toolkit;
import datamanager.Taxpayer;
import datamanager.TaxpayerDatabase;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class TaxpayersWindow extends JDialog {

	private TaxpayerDatabase taxpayerDatabase;
	private TaxpayerDetailsWindow taxpayerDetailsWindow;
	private ReceiptListWindow receiptsWindow;
	private LogWindow logWindow;
	private TaxpayerListPanel taxpayerListPanel;
	private JButton returnButton = new JButton("Επιστροφή");
	private JSeparator separator = new JSeparator();
	private JLabel feedbackLabel = new JLabel("");
	private TaxpayerOperationsPanel taxpayerOperationsPanel;

	public TaxpayersWindow(TaxpayerDatabase database) {
		taxpayerDatabase = database;
		setTitle("Λίστα φορολογουμένων");
		initializeWindowElements();
		addLabels();
		addReturnButton();
		initializePanels();
		separator.setBounds(0, 388, 604, 1);
		getContentPane().add(separator);
	}

	private void initializeWindowElements() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TaxpayersWindow.class.
			getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 13));
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 610, 435);
		getContentPane().setLayout(null);
	}

	private void addLabels() {
		feedbackLabel.setBounds(0, 389, 604, 20);
		feedbackLabel.setOpaque(true);
		feedbackLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		feedbackLabel.setBackground(SystemColor.controlHighlight);
		getContentPane().add(feedbackLabel);
	}

	private void addReturnButton() {
		returnButton.setBounds(247, 356, 110, 32);
		getContentPane().add(returnButton);
		returnButton.setFont(new Font("Verdana", Font.PLAIN, 14));
		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				setVisible(false);
				dispose();
			} });
	}

	private void initializePanels() {
		taxpayerListPanel = new TaxpayerListPanel(taxpayerDatabase);
		taxpayerOperationsPanel = new TaxpayerOperationsPanel();
		taxpayerListPanel.setBounds(0, 0, 604, 184);
		getContentPane().add(taxpayerListPanel);
		taxpayerOperationsPanel.setBounds(0, 185, 604, 160);
		getContentPane().add(taxpayerOperationsPanel);
		addPanelEventListeners();
	}

	private void addPanelEventListeners() {
		taxpayerListPanel.addTaxpayerListEventListener();
		addTaxpayerListPropertyChangeListener();
		taxpayerOperationsPanel.addPropertyChangeListener(
				new PropertyChangeListener() {
		    public void propertyChange(PropertyChangeEvent event) {
		    	doAction(event.getPropertyName());
		    } });
	}

	private void addTaxpayerListPropertyChangeListener() {
		taxpayerListPanel.addPropertyChangeListener(
				new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				taxpayerOperationsPanel.setTaxpayer(
						taxpayerListPanel.getSelectedTaxpayer());
			} });
	}

	private void doAction(String action) {
		if (action.equals("None selected")) { setFeedbackNoneSelected(); }
		if (action.equals("Show details")) { createTaxpayerDetailsWindow(); }
		if (action.equals("Show receipts")) { createReceiptListWindow(); }
		if (action.equals("Create log")) { createLogWindow(); }
		if (action.equals("Delete")) { deleteTaxpayer(); }
	}

	private void setFeedbackNoneSelected() {
		getToolkit().beep();
		feedbackLabel.setForeground(new Color(128, 0, 0));
		feedbackLabel.setText(" Δεν επιλέχθηκε κανένας φορολογούμενος! "
			+ "Επιλέξτε έναν και ξαναπροσπαθήστε.");
	}

	private void createTaxpayerDetailsWindow() {
		Taxpayer taxpayer = taxpayerListPanel.getSelectedTaxpayer();
		taxpayerDetailsWindow = new TaxpayerDetailsWindow();
		taxpayerDetailsWindow.setLocationRelativeTo(null);
		taxpayerDetailsWindow.setDetails(taxpayer.getTaxpayerDetails());
		taxpayerDetailsWindow.setVisible(true);
		setDefaultFeedback();
	}

	private void setDefaultFeedback() {
		feedbackLabel.setText("");
	}

	private void createReceiptListWindow() {
		Taxpayer taxpayer = taxpayerListPanel.getSelectedTaxpayer();
		receiptsWindow = new ReceiptListWindow(taxpayer.getTaxpayerReceipts());
		receiptsWindow.setLocationRelativeTo(null);
		addReceiptsWindowListener();
		receiptsWindow.setVisible(true);
	}

	private void addReceiptsWindowListener() {
		receiptsWindow.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent event) {
				updateTaxpayer();
				setFeedbackUpdatedFileText();
				}
			});
	}

	private void updateTaxpayer() {
		Taxpayer taxpayer = taxpayerListPanel.getSelectedTaxpayer();
		taxpayerDatabase.updateTaxpayer(taxpayer,
				receiptsWindow.getUpdatedReceiptList());
		String filePath = taxpayerDatabase.getTaxpayerFilePath(taxpayer);
		taxpayer.createOutputFactory(filePath);
		taxpayer.printFile();
	}

	private void setFeedbackUpdatedFileText() {
		feedbackLabel.setForeground(new Color(0, 0, 128));
		feedbackLabel.setText(
				" Το αρχείο εισαγωγής φορολογουμένου ενημερώθηκε επιτυχώς!");
	}

	private void createLogWindow() {
		logWindow = new LogWindow();
		logWindow.setLocationRelativeTo(null);
		logWindow.setTaxpayer(taxpayerListPanel.getSelectedTaxpayer());
		logWindow.setVisible(true);
		logWindow.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent event) {
					setFeedbackOutputFileCreated(logWindow.getResult());
				} });
	}

	private void setFeedbackOutputFileCreated(String result) {
		feedbackLabel.setForeground(new Color(0, 128, 0));
		feedbackLabel.setText(result);
	}

	private void deleteTaxpayer() {
		taxpayerOperationsPanel.setTaxpayer(null);
		taxpayerListPanel.deleteSelectedTaxpayer();
		updateDatabase(taxpayerListPanel.getTaxpayerDatabase());
		setFeedbackMessageDeleteSuccess();
	}

	private void updateDatabase(TaxpayerDatabase newDatabase) {
		taxpayerDatabase = newDatabase;
	}

	private void setFeedbackMessageDeleteSuccess() {
		feedbackLabel.setForeground(new Color(0, 128, 0));
		feedbackLabel.setText(
				" Διαγραφή φορολογουμένου από τη λίστα επιτυχής!");
	}

	public TaxpayerDatabase returnDatabase() {
		return this.taxpayerDatabase;
	}

}
