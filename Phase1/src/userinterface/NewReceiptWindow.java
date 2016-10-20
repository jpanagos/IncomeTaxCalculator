package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import datamanager.Company;
import datamanager.CompanyFactory;
import datamanager.Receipt;
import datamanager.ReceiptFactory;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

public class NewReceiptWindow extends JDialog {

	private final JPanel receiptPromptPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField receiptDateTextField;
	private JTextField companyNameTextField;
	private JTextField companyCountryTextField;
	private JTextField companyCityTextField;
	private JTextField companyAddressTextField;
	private JPanel receiptIDPanel;
	private JPanel receiptDatePanel;
	private JPanel receiptCategoryPanel;
	private JPanel receiptAmountPanel;
	private JPanel companyNamePanel;
	private JPanel companyCountryPanel;
	private JPanel companyCityPanel;
	private JPanel companyAddressPanel;
	private JPanel companyAddressNumberPanel;
	private int receiptID = -1;
	private String receiptDate = null;
	private String receiptCategory = null;
	private int receiptAmount = -1;
	private String companyName = null;
	private String companyCountry= null;
	private String companyCity = null;
	private String companyAddress = null;
	private int companyAddressNumber = -1;
	private Receipt newReceipt = null;
	

	public NewReceiptWindow() {
		initializeInputPanels();
		setTitle("Νέα επιταγή");
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewReceiptWindow.class.getResource(
				"/com/sun/java/swing/plaf/windows/icons/File.gif")));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setBounds(100, 100, 390, 662);
		getContentPane().setLayout(null);
		
		JSeparator companyFieldTopSeparator = new JSeparator();
		companyFieldTopSeparator.setForeground(Color.BLACK);
		companyFieldTopSeparator.setBounds(0, 329, 384, 1);
		getContentPane().add(companyFieldTopSeparator);
		
		JSeparator receiptFieldTopSeparator = new JSeparator();
		receiptFieldTopSeparator.setForeground(Color.BLACK);
		receiptFieldTopSeparator.setBounds(0, 48, 384, 1);
		getContentPane().add(receiptFieldTopSeparator);
		receiptPromptPanel.setBounds(0, 0, 384, 46);
		receiptPromptPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(receiptPromptPanel);
		receiptPromptPanel.setLayout(null);
		{
			JLabel receiptPromptLabel = new JLabel("Συμπληρώστε τα στοιχεία της επιταγής:");
			receiptPromptLabel.setHorizontalAlignment(SwingConstants.CENTER);
			receiptPromptLabel.setToolTipText("Όλα τα πεδία είναι υποχρεωτικά");
			receiptPromptLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
			receiptPromptLabel.setBounds(10, 0, 364, 34);
			receiptPromptPanel.add(receiptPromptLabel);
		}
		
		JLabel label = new JLabel("Όλα τα πεδία πρέπει να συμπληρωθούν");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("Verdana", Font.ITALIC, 11));
		label.setBounds(10, 30, 364, 16);
		receiptPromptPanel.add(label);
		
		receiptIDPanel.setToolTipText("Πρέπει να είναι ακέραιος αριθμός");
		receiptIDPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		receiptIDPanel.setBounds(0, 48, 384, 46);
		getContentPane().add(receiptIDPanel);
		receiptIDPanel.setLayout(null);
		
		JLabel receiptIDLabel = new JLabel("Αριθμός επιταγής (ID):");
		receiptIDLabel.setToolTipText("");
		receiptIDLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		receiptIDLabel.setBounds(10, 11, 170, 24);
		receiptIDPanel.add(receiptIDLabel);
		
		JFormattedTextField receiptIDTextField = new JFormattedTextField();
		receiptIDTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyEvent) {
				if (!(keyIsAcceptable(keyEvent))){
					getToolkit().beep();
					keyEvent.consume();
				}
			}
		});
		receiptIDTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (receiptIDTextField.getText().length() != 0) {
					receiptIDPanel.setBackground(new Color(238, 238, 238));
					receiptID = Integer.parseInt(receiptIDTextField.getText());
				}else {
					receiptID = -1;
				}
			}
		});
		receiptIDTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		receiptIDTextField.setBounds(180, 11, 194, 24);
		receiptIDPanel.add(receiptIDTextField);
		
		receiptDatePanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		receiptDatePanel.setBounds(0, 93, 384, 46);
		getContentPane().add(receiptDatePanel);
		receiptDatePanel.setLayout(null);
		
		JLabel receiptDateLabel = new JLabel("Ημερομηνία έκδοσης:");
		receiptDateLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		receiptDateLabel.setBounds(10, 11, 170, 24);
		receiptDatePanel.add(receiptDateLabel);
		
		receiptDateTextField = new JTextField();
		receiptDateTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (receiptDateTextField.getText().length() != 0) {
					receiptDatePanel.setBackground(new Color(238, 238, 238));
					receiptDate = receiptDateTextField.getText();
				}else {
					receiptDate = null;
				}
			}
		});
		receiptDateTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		receiptDateTextField.setBounds(180, 11, 194, 24);
		receiptDatePanel.add(receiptDateTextField);
		receiptDateTextField.setColumns(10);
		
		receiptCategoryPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		receiptCategoryPanel.setToolTipText("Επιλογή ενός");
		receiptCategoryPanel.setBounds(0, 138, 384, 76);
		getContentPane().add(receiptCategoryPanel);
		receiptCategoryPanel.setLayout(null);
		
		JLabel receiptCategoryLabel = new JLabel("Κατηγορία επιταγής:");
		receiptCategoryLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		receiptCategoryLabel.setBounds(10, 11, 154, 30);
		receiptCategoryPanel.add(receiptCategoryLabel);
		
		JRadioButton healthRadioButton = new JRadioButton("Ιατρική περίθαλψη");
		healthRadioButton.setOpaque(false);
		healthRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				receiptCategory = "Health";
				setDefaultButtonPanelColor();
			}
		});
		healthRadioButton.setToolTipText("Health");
		healthRadioButton.setBounds(131, 46, 137, 23);
		receiptCategoryPanel.add(healthRadioButton);
		
		JRadioButton entertainmentRadioButton = new JRadioButton("Διασκέδαση");
		entertainmentRadioButton.setOpaque(false);
		entertainmentRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				receiptCategory = "Entertainment";
				setDefaultButtonPanelColor();
			}
		});
		entertainmentRadioButton.setToolTipText("Entertainment");
		entertainmentRadioButton.setBounds(162, 17, 109, 23);
		receiptCategoryPanel.add(entertainmentRadioButton);
		
		JRadioButton basicRadioButton = new JRadioButton("Βασικά Αγαθά");
		basicRadioButton.setOpaque(false);
		basicRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				receiptCategory = "Basic";
				setDefaultButtonPanelColor();
			}
		});
		basicRadioButton.setToolTipText("Basic");
		basicRadioButton.setBounds(269, 17, 109, 23);
		receiptCategoryPanel.add(basicRadioButton);
		
		JRadioButton travelRadioButton = new JRadioButton("Μετακινήσεις");
		travelRadioButton.setOpaque(false);
		travelRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				receiptCategory = "Travel";
				setDefaultButtonPanelColor();
			}
		});
		travelRadioButton.setToolTipText("Travel");
		travelRadioButton.setBounds(10, 46, 109, 23);
		receiptCategoryPanel.add(travelRadioButton);
		
		JRadioButton otherRadioButton = new JRadioButton("Άλλα έξοδα");
		otherRadioButton.setOpaque(false);
		otherRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				receiptCategory = "Other";
				setDefaultButtonPanelColor();
			}
		});
		otherRadioButton.setToolTipText("Other");
		otherRadioButton.setBounds(269, 46, 109, 23);
		receiptCategoryPanel.add(otherRadioButton);
		
		receiptAmountPanel.setToolTipText("Πρέπει να είναι ακέραιος αριθμός");
		receiptAmountPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		receiptAmountPanel.setBounds(0, 213, 384, 46);
		getContentPane().add(receiptAmountPanel);
		receiptAmountPanel.setLayout(null);
		
		JLabel receiptAmountLabel = new JLabel("Ποσό επιταγής:");
		receiptAmountLabel.setToolTipText("");
		receiptAmountLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		receiptAmountLabel.setBounds(10, 11, 170, 24);
		receiptAmountPanel.add(receiptAmountLabel);
		
		JFormattedTextField receiptAmountTextField = new JFormattedTextField();
		receiptAmountTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyEvent) {
				if (!(keyIsAcceptable(keyEvent))){
					getToolkit().beep();
					keyEvent.consume();
				}
			}
		});
		receiptAmountTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (receiptAmountTextField.getText().length() != 0) {
					receiptAmountPanel.setBackground(new Color(238, 238, 238));
					receiptAmount = Integer.parseInt(receiptAmountTextField.getText());
				}else {
					receiptAmount = -1;
				}
			}
		});
		receiptAmountTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		receiptAmountTextField.setBounds(180, 11, 194, 24);
		receiptAmountPanel.add(receiptAmountTextField);
		
		JPanel companyPromptPanel = new JPanel();
		companyPromptPanel.setLayout(null);
		companyPromptPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		companyPromptPanel.setBounds(0, 257, 384, 72);
		getContentPane().add(companyPromptPanel);
		
		JLabel companyPromptLabel = new JLabel("Συμπληρώστε τα στοιχεία της εταιρείας:");
		companyPromptLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyPromptLabel.setToolTipText("Όλα τα πεδία είναι υποχρεωτικά");
		companyPromptLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		companyPromptLabel.setBounds(10, 11, 364, 37);
		companyPromptPanel.add(companyPromptLabel);
		
		JSeparator companyPromptTopSeparator = new JSeparator();
		companyPromptTopSeparator.setBounds(0, 11, 384, 1);
		companyPromptPanel.add(companyPromptTopSeparator);
		
		JSeparator companyPromptBottomSeparator = new JSeparator();
		companyPromptBottomSeparator.setBounds(0, 60, 384, 1);
		companyPromptPanel.add(companyPromptBottomSeparator);
		
		JLabel label_1 = new JLabel("Όλα τα πεδία πρέπει να συμπληρωθούν");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("Verdana", Font.ITALIC, 11));
		label_1.setBounds(10, 42, 364, 16);
		companyPromptPanel.add(label_1);
		
		companyNamePanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		companyNamePanel.setBounds(0, 329, 384, 46);
		getContentPane().add(companyNamePanel);
		companyNamePanel.setLayout(null);
		
		JLabel companyNameLabel = new JLabel("Όνομα εταιρείας:");
		companyNameLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		companyNameLabel.setBounds(10, 11, 171, 24);
		companyNamePanel.add(companyNameLabel);
		
		companyNameTextField = new JTextField();
		companyNameTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (companyNameTextField.getText().length() != 0) {
					companyNamePanel.setBackground(new Color(238, 238, 238));
					companyName = companyNameTextField.getText();
				}else {
					companyName = null;
				}
			}
		});
		companyNameTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		companyNameTextField.setBounds(180, 11, 194, 24);
		companyNamePanel.add(companyNameTextField);
		companyNameTextField.setColumns(10);
		
		companyCountryPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		companyCountryPanel.setBounds(0, 374, 384, 46);
		getContentPane().add(companyCountryPanel);
		companyCountryPanel.setLayout(null);
		
		JLabel companyCountryLabel = new JLabel("Χώρα εταιρείας:");
		companyCountryLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		companyCountryLabel.setBounds(10, 11, 170, 24);
		companyCountryPanel.add(companyCountryLabel);
		
		companyCountryTextField = new JTextField();
		companyCountryTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (companyCountryTextField.getText().length() != 0) {
					companyCountryPanel.setBackground(new Color(238, 238, 238));
					companyCountry = companyCountryTextField.getText();
				}else {
					companyCountry = null;
				}
			}
		});
		companyCountryTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		companyCountryTextField.setBounds(180, 11, 194, 24);
		companyCountryPanel.add(companyCountryTextField);
		companyCountryTextField.setColumns(10);
		
		companyCityPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		companyCityPanel.setBounds(0, 419, 384, 46);
		getContentPane().add(companyCityPanel);
		companyCityPanel.setLayout(null);
		
		JLabel companyCityLabel = new JLabel("Πόλη εταιρείας:");
		companyCityLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		companyCityLabel.setBounds(10, 11, 170, 24);
		companyCityPanel.add(companyCityLabel);
		
		companyCityTextField = new JTextField();
		companyCityTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (companyCityTextField.getText().length() != 0) {
					companyCityPanel.setBackground(new Color(238, 238, 238));
					companyCity = companyCityTextField.getText();
				}else {
					companyCity = null;
				}
			}
		});
		companyCityTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		companyCityTextField.setBounds(180, 11, 194, 24);
		companyCityPanel.add(companyCityTextField);
		companyCityTextField.setColumns(10);
		
		companyAddressPanel.setLayout(null);
		companyAddressPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		companyAddressPanel.setBounds(0, 464, 384, 46);
		getContentPane().add(companyAddressPanel);
		
		JLabel companyAddressLabel = new JLabel("Διεύθυνση (Οδός):");
		companyAddressLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		companyAddressLabel.setBounds(10, 11, 170, 24);
		companyAddressPanel.add(companyAddressLabel);
		
		companyAddressTextField = new JTextField();
		companyAddressTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (companyAddressTextField.getText().length() != 0) {
					companyAddressPanel.setBackground(new Color(238, 238, 238));
					companyAddress = companyAddressTextField.getText();
				}else {
					companyAddress = null;
				}
			}
		});
		companyAddressTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		companyAddressTextField.setBounds(180, 11, 194, 24);
		companyAddressPanel.add(companyAddressTextField);
		companyAddressTextField.setColumns(10);
		
		companyAddressNumberPanel = new JPanel();
		companyAddressNumberPanel.setToolTipText("Πρέπει να είναι αριθμός");
		companyAddressNumberPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		companyAddressNumberPanel.setBounds(0, 509, 384, 46);
		getContentPane().add(companyAddressNumberPanel);
		companyAddressNumberPanel.setLayout(null);
		
		JLabel companyAddressNumberLabel = new JLabel("Αριθμός διεύθυνσης:");
		companyAddressNumberLabel.setToolTipText("");
		companyAddressNumberLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		companyAddressNumberLabel.setBounds(10, 11, 170, 24);
		companyAddressNumberPanel.add(companyAddressNumberLabel);
		
		JFormattedTextField companyAddressNumberTextField = new JFormattedTextField();
		companyAddressNumberTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyEvent) {
				if (!(keyIsAcceptable(keyEvent))){
					getToolkit().beep();
					keyEvent.consume();
				}
			}
		});
		companyAddressNumberTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (companyAddressNumberTextField.getText().length() != 0) {
					companyAddressNumberPanel.setBackground(new Color(238, 238, 238));
					companyAddressNumber = Integer.parseInt(companyAddressNumberTextField.getText());
				}else {
					companyAddressNumber = -1;
				}
			}
		});
		companyAddressNumberTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		companyAddressNumberTextField.setBounds(180, 11, 194, 24);
		companyAddressNumberPanel.add(companyAddressNumberTextField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 566, 384, 73);
		getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JButton confirmButton = new JButton("Επιβεβαίωση");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if  (receiptValuesAreOK() && companyValuesAreOK()){
					createReceipt();
					setVisible(false);
					dispose();
				}else {
					colorizeEmptyPanels();
					getToolkit().beep();
				}
			}
		});
		confirmButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		confirmButton.setToolTipText("Προσθήκη επιταγής στη λίστα");
		confirmButton.setBounds(10, 11, 169, 51);
		buttonPanel.add(confirmButton);
		
		JButton cancelButton = new JButton("Επιστροφή");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		cancelButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		cancelButton.setToolTipText("Ακύρωση ενέργειας");
		cancelButton.setBounds(205, 11, 169, 51);
		buttonPanel.add(cancelButton);
		
		buttonGroup.add(healthRadioButton);
		buttonGroup.add(otherRadioButton);
		buttonGroup.add(travelRadioButton);
		buttonGroup.add(entertainmentRadioButton);
		buttonGroup.add(basicRadioButton);
		
		JSeparator panelSeparator = new JSeparator();
		panelSeparator.setBounds(0, 564, 384, 1);
		getContentPane().add(panelSeparator);
	}
	
	private void initializeInputPanels() {
		receiptIDPanel = new JPanel();
		receiptDatePanel = new JPanel();
		receiptCategoryPanel = new JPanel();
		receiptAmountPanel = new JPanel();
		companyNamePanel = new JPanel();
		companyCountryPanel = new JPanel();
		companyCityPanel = new JPanel();
		companyAddressPanel = new JPanel();
		companyAddressNumberPanel = new JPanel();
	}
	
	
	private boolean keyIsAcceptable(KeyEvent keyEvent){
		char typed = keyEvent.getKeyChar();
		return (Character.isDigit(typed) ||
				(typed==keyEvent.VK_BACK_SPACE) || (typed==keyEvent.VK_DELETE));
	}
	
	private void setDefaultButtonPanelColor() {
		receiptCategoryPanel.setBackground(new Color(238, 238, 238));
	}
	
	private boolean receiptValuesAreOK() {
		return (receiptIDIsOK() && receiptDateIsOK() && receiptCategoryIsOK() && receiptAmountIsOK());
	}
	
	private boolean receiptIDIsOK() {
		return (receiptID != -1);
	}
	
	private boolean receiptDateIsOK() {
		return (receiptDate != null);
	}
	
	private boolean receiptCategoryIsOK() {
		return (receiptCategory != null);
	}
	
	private boolean receiptAmountIsOK() {
		return (receiptAmount != -1);
	}
	
	private boolean companyValuesAreOK() {
		return (companyNameIsOK() && companyCountryIsOK() && companyCityIsOK() &&
				companyAddressIsOK() && companyAddressNumberIsOK());
	}
	
	private boolean companyNameIsOK() {
		return (companyName != null);
	}
	
	private boolean companyCountryIsOK() {
		return (companyCountry != null);
	}
	
	private boolean companyCityIsOK() {
		return (companyCountry != null);
	}
	
	private boolean companyAddressIsOK() {
		return (companyAddress != null);
	}
	
	private boolean companyAddressNumberIsOK() {
		return (companyAddressNumber != -1);
	}
	
	private void colorizeEmptyPanels() {
		colorizeEmptyReceiptPanels();
		colorizeEmptyCompanyPanels();
	}
	
	private void colorizeEmptyReceiptPanels() {
		if (receiptID == -1) receiptIDPanel.setBackground(Color.PINK);
		if (receiptDate == null) receiptDatePanel.setBackground(Color.PINK);
		if (receiptCategory == null) receiptCategoryPanel.setBackground(Color.PINK);
		if (receiptAmount == -1) receiptAmountPanel.setBackground(Color.PINK);
	}
	
	private void colorizeEmptyCompanyPanels() {
		if (companyName == null) companyNamePanel.setBackground(Color.PINK);
		if (companyCountry == null) companyCountryPanel.setBackground(Color.PINK);
		if (companyCity == null) companyCityPanel.setBackground(Color.PINK);
		if (companyAddress == null) companyAddressPanel.setBackground(Color.PINK);
		if (companyAddressNumber == -1) companyAddressNumberPanel.setBackground(Color.PINK);
	}
	
	private void createReceipt() {
		Company newCompany = CompanyFactory.createCompany(
				companyName, companyCountry, companyCity, companyAddress, companyAddressNumber);
				
		newReceipt = ReceiptFactory.createReceipt(
				receiptID, receiptDate, receiptCategory, receiptAmount, newCompany);
	}
	
	public Receipt getReceipt() {
		return newReceipt;
	}
	
}

