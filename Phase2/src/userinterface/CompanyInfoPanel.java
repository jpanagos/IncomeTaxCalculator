package userinterface;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class CompanyInfoPanel extends JPanel {

	private JPanel namePanel = new JPanel();
	private JPanel countryPanel = new JPanel();
	private JPanel cityPanel = new JPanel();
	private JPanel addressPanel = new JPanel();
	private JPanel numberPanel = new JPanel();
	private JTextField nameTextField = new JTextField();
	private JTextField countryTextField = new JTextField();
	private JTextField cityTextField = new JTextField();
	private JTextField addressTextField = new JTextField();
	private JFormattedTextField numberTextField = new JFormattedTextField();
	private JLabel promptLabel =
			new JLabel("Συμπληρώστε τα στοιχεία της εταιρείας:");
	private JLabel infoLabel =
			new JLabel("Όλα τα πεδία πρέπει να συμπληρωθούν");
	private JLabel nameLabel = new JLabel("Όνομα εταιρείας:");
	private JLabel countryLabel = new JLabel("Χώρα εταιρείας:");
	private JLabel cityLabel = new JLabel("Πόλη εταιρείας:");
	private JLabel addressLabel = new JLabel("Διεύθυνση (Οδός):");
	private JLabel numberLabel = new JLabel("Αριθμός διεύθυνσης:");
	private String[] companyProperties = {"", "", "", "", "" };

	public CompanyInfoPanel() {
		setLayout(null);
		initializeLabels();
		add(promptLabel);
		add(infoLabel);
		JSeparator topSeparator = new JSeparator();
		topSeparator.setBounds(0, 48, 384, 1);
		add(topSeparator);
		initializePanels();
	}

	private void initializeLabels() {
		promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
		promptLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		promptLabel.setBounds(0, 0, 384, 34);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setForeground(Color.GRAY);
		infoLabel.setFont(new Font("Verdana", Font.ITALIC, 11));
		infoLabel.setBounds(0, 31, 384, 16);
	}

	private void initializePanels() {
		initializeNamePanel();
		initializeCountryPanel();
		initializeCityPanel();
		initializeAddressPanel();
		initializeNumberPanel();
		addNumberPanel();
	}

	private void initializeNamePanel() {
		namePanel.setBounds(0, 48, 384, 46);
		namePanel.setLayout(null);
		namePanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		nameLabel.setBounds(10, 11, 171, 24);
		nameTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		nameTextField.setBounds(180, 11, 194, 24);
		addNamePanel();
	}

	private void addNamePanel() {
		namePanel.add(nameLabel);
		addFocusListener(nameTextField, 0);
		namePanel.add(nameTextField);
		add(namePanel);
	}

	private void addFocusListener(JTextField field, int index) {
		field.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent event) {
				companyProperties[index] = field.getText();
			}
		});
	}

	private void initializeCountryPanel() {
		countryPanel.setBounds(0, 93, 384, 46);
		countryPanel.setLayout(null);
		countryPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		countryLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		countryLabel.setBounds(10, 11, 170, 24);
		countryTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		countryTextField.setBounds(180, 11, 194, 24);
		addCountryPanel();
	}

	private void addCountryPanel() {
		countryPanel.add(countryLabel);
		addFocusListener(countryTextField, 1);
		countryPanel.add(countryTextField);
		add(countryPanel);
	}

	private void initializeCityPanel() {
		cityPanel.setBounds(0, 138, 384, 46);
		cityPanel.setLayout(null);
		cityPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		cityLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		cityLabel.setBounds(10, 11, 170, 24);
		cityTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		cityTextField.setBounds(180, 11, 194, 24);
		addCityPanel();
	}

	private void addCityPanel() {
		cityPanel.add(cityLabel);
		addFocusListener(cityTextField, 2);
		cityPanel.add(cityTextField);
		add(cityPanel);
	}

	private void initializeAddressPanel() {
		addressPanel.setLayout(null);
		addressPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		addressPanel.setBounds(0, 183, 384, 46);
		addressLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		addressLabel.setBounds(10, 11, 170, 24);
		addressTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		addressTextField.setBounds(180, 11, 194, 24);
		addAddressPanel();
	}

	private void addAddressPanel() {
		addressPanel.add(addressLabel);
		addFocusListener(addressTextField, 3);
		addressPanel.add(addressTextField);
		add(addressPanel);
	}

	private void initializeNumberPanel() {
		numberPanel.setBounds(0, 228, 384, 46);
		numberPanel.setToolTipText("Πρέπει να είναι αριθμός");
		numberPanel.setLayout(null);
		numberPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		numberLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		numberLabel.setBounds(10, 11, 170, 24);
		numberTextField.setFont(new Font("Verdana", Font.PLAIN, 14));
		numberTextField.setBounds(180, 11, 194, 24);
	}

	private void addNumberPanel() {
		numberPanel.add(numberLabel);
		numberTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent keyEvent) {
				checkKey(keyEvent);
			} });
		addFocusListener(numberTextField, 4);
		numberPanel.add(numberTextField);
		add(numberPanel);
	}

	private void checkKey(KeyEvent keyEvent) {
		final char typed = keyEvent.getKeyChar();
		if (!((Character.isDigit(typed))
			|| (typed == keyEvent.VK_BACK_SPACE)
			|| (typed == keyEvent.VK_DELETE))) {
			getToolkit().beep();
			keyEvent.consume();
		}
	}

	public String[] requestCompany() {
		if (companyProperties[0].length() == 0) { colorizePanel(namePanel); }
		if (companyProperties[1].length() == 0) { colorizePanel(countryPanel); }
		if (companyProperties[2].length() == 0) { colorizePanel(cityPanel); }
		if (companyProperties[3].length() == 0) { colorizePanel(addressPanel); }
		if (companyProperties[4].length() == 0) { colorizePanel(numberPanel); }
		return companyProperties;
	}

	private void colorizePanel(JPanel panel) {
		panel.setBackground(Color.PINK);
	}

}
