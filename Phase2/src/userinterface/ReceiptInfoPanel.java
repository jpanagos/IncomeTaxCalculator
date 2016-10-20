package userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReceiptInfoPanel extends JPanel {

	private final ButtonGroup buttonGroup = new ButtonGroup();
	JLabel promptLabel = new JLabel("Συμπληρώστε τα στοιχεία της απόδειξης:");
	JLabel infoLabel = new JLabel("Όλα τα πεδία πρέπει να συμπληρωθούν");
	private JSeparator topSeparator = new JSeparator();
	private JPanel datePanel = new JPanel();
	private JPanel idPanel = new JPanel();
	private JPanel categoryPanel = new JPanel();
	private JPanel amountPanel = new JPanel();
	private JTextField dateTextField = new JFormattedTextField();
	private JFormattedTextField idTextField = new JFormattedTextField();
	private JFormattedTextField amountTextField = new JFormattedTextField();
	private JLabel idLabel = new JLabel("Αριθμός απόδειξης (ID):");
	private JLabel dateLabel = new JLabel("Ημερομηνία έκδοσης:");
	private JLabel categoryLabel = new JLabel("Κατηγορία απόδειξης:");
	private JLabel amountLabel = new JLabel("Ποσό απόδειξης:");
	JRadioButton healthRadioButton = new JRadioButton("Ιατρική περίθαλψη");
	JRadioButton entertainmentRadioButton = new JRadioButton("Διασκέδαση");
	JRadioButton travelRadioButton = new JRadioButton("Μετακινήσεις");
	JRadioButton basicRadioButton = new JRadioButton("Βασικά Αγαθά");
	JRadioButton otherRadioButton = new JRadioButton("Άλλα έξοδα");
	private String[] receiptProperties = {"", "", "", "" };

	public ReceiptInfoPanel() {
		setLayout(null);
		initializePanelLabels();
		add(promptLabel);
		add(infoLabel);
		topSeparator.setBounds(0, 48, 384, 1);
		add(topSeparator);
		initializePanels();
		initializeLabels();
	}

	private void initializePanelLabels() {
		promptLabel.setToolTipText("");
		promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
		promptLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		promptLabel.setBounds(0, 0, 384, 34);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setForeground(Color.GRAY);
		infoLabel.setFont(new Font("Verdana", Font.ITALIC, 11));
		infoLabel.setBounds(0, 31, 384, 16);
	}

	private void initializePanels() {
		initializeIdPanel();
		initializeDatePanel();
		initializeCategoryPanel();
		initializeAmountPanel();
	}

	private void initializeIdPanel() {
		initializeIdPanelComponents();
		idPanel.setLayout(null);
		idPanel.setToolTipText("Πρέπει να είναι ακέραιος αριθμός");
		idPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		idPanel.setBounds(0, 48, 384, 46);
		add(idPanel);
	}

	private void initializeIdPanelComponents() {
		idTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		idTextField.setBounds(180, 11, 194, 24);
		addTextFieldEventListeners(idTextField, 0);
		idPanel.add(idLabel);
		idPanel.add(idTextField);
	}

	private void addTextFieldEventListeners(JTextField field, int index) {
		field.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent keyEvent) {
				checkKey(keyEvent);
			} });
		field.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent focusEvent) {
				receiptProperties[index] = field.getText();
			} });
	}

	private void checkKey(KeyEvent keyEvent) {
		char typed = keyEvent.getKeyChar();
		if (!((Character.isDigit(typed)) ||
			(typed==keyEvent.VK_BACK_SPACE) || (typed==keyEvent.VK_DELETE))) {
			getToolkit().beep();
			keyEvent.consume();
		}
	}

	private void initializeDatePanel() {
		initializeDatePanelComponents();
		datePanel.setLayout(null);
		datePanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		datePanel.setBounds(0, 93, 384, 46);
		add(datePanel);
	}

	private void initializeDatePanelComponents() {
		dateTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		dateTextField.setBounds(180, 11, 194, 24);
		datePanel.add(dateLabel);
		datePanel.add(dateTextField);
		dateTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent focusEvent) {
				receiptProperties[1] = dateTextField.getText();
			} });
	}

	private void initializeCategoryPanel() {
		categoryPanel.setBounds(0, 138, 384, 76);
		categoryPanel.setLayout(null);
		categoryPanel.setToolTipText("Επιλογή ενός");
		categoryPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		initializeCategoryRadioButtons();
		categoryPanel.add(categoryLabel);
		add(categoryPanel);
	}

	private void initializeCategoryRadioButtons() {
		initializeRadioButton(healthRadioButton, "Health");
		initializeRadioButton(entertainmentRadioButton, "Entertainment");
		initializeRadioButton(basicRadioButton, "Basic");
		initializeRadioButton(travelRadioButton, "Travel");
		initializeRadioButton(otherRadioButton, "Other");
		setCategoryRadioButtonPositions();
	}

	private void initializeRadioButton(JRadioButton button, String text) {
		button.setToolTipText(text);
		button.setOpaque(false);
		addRadioButtonListener(button);
		categoryPanel.add(button);
		buttonGroup.add(button);
	}

	private void addRadioButtonListener(JRadioButton button) {
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				receiptProperties[2] = button.getToolTipText();
			}
		});
	}

	private void setCategoryRadioButtonPositions() {
		healthRadioButton.setBounds(131, 46, 137, 23);
		entertainmentRadioButton.setBounds(167, 17, 104, 23);
		basicRadioButton.setBounds(269, 17, 109, 23);
		travelRadioButton.setBounds(10, 46, 109, 23);
		otherRadioButton.setBounds(269, 46, 109, 23);
	}

	private void initializeAmountPanel() {
		initializeAmountPanelComponents();
		amountPanel.setLayout(null);
		amountPanel.setToolTipText("Πρέπει να είναι ακέραιος αριθμός");
		amountPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		amountPanel.setBounds(0, 213, 384, 46);
		add(amountPanel);
	}

	private void initializeAmountPanelComponents() {
		amountPanel.add(amountLabel);
		amountTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		amountTextField.setBounds(180, 11, 194, 24);
		addTextFieldEventListeners(amountTextField, 3);
		amountPanel.add(amountTextField);
	}

	private void initializeLabels() {
		idLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		idLabel.setBounds(10, 11, 170, 24);
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		dateLabel.setBounds(10, 11, 170, 24);
		categoryLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		categoryLabel.setBounds(10, 11, 154, 30);
		amountLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		amountLabel.setBounds(10, 11, 170, 24);
	}

	public String[] requestReceipt() {
		if (receiptProperties[0].length() == 0) { colorizePanel(idPanel); }
		if (receiptProperties[1].length() == 0) { colorizePanel(datePanel); }
		if (receiptProperties[2].length() == 0) { colorizePanel(categoryPanel);}
		if (receiptProperties[3].length() == 0) { colorizePanel(amountPanel); }
		return receiptProperties;
	}

	private void colorizePanel(JPanel panel) {
		panel.setBackground(Color.PINK);
	}

}
