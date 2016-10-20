package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import datamanager.Company;
import datamanager.Receipt;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewReceiptWindow extends JDialog {

	private JPanel buttonPanel = new JPanel();
	private JButton confirmButton = new JButton("Επιβεβαίωση");
	private JButton cancelButton = new JButton("Επιστροφή");
	private ReceiptInfoPanel receiptInfoPanel = new ReceiptInfoPanel();
	private CompanyInfoPanel companyInfoPanel = new CompanyInfoPanel();
	private String[] receiptProperties;
	private String[] companyProperties;
	private Receipt newReceipt = null;
	private Company newCompany = null;

	public NewReceiptWindow() {
		initializeProperties();
		JSeparator topSeparator = new JSeparator();
		topSeparator.setBounds(0, 269, 384, 1);
		getContentPane().add(topSeparator);
		addPanels();
		addEventListeners();
	}

	private void initializeProperties() {
		setTitle("Νέα απόδειξη");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				NewReceiptWindow.class.getResource(
				"/com/sun/java/swing/plaf/windows/icons/File.gif")));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setBounds(100, 100, 390, 640);
		getContentPane().setLayout(null);
	}

	private void addPanels() {
		receiptInfoPanel.setBounds(0, 0, 384, 270);
		getContentPane().add(receiptInfoPanel);
		buttonPanel.setBounds(0, 544, 384, 71);
		buttonPanel.setLayout(null);
		getContentPane().add(buttonPanel);
		companyInfoPanel.setBounds(0, 269, 384, 275);
		getContentPane().add(companyInfoPanel);
		addButtons();
	}

	private void addButtons() {
		confirmButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		confirmButton.setToolTipText("Προσθήκη απόδειξης στη λίστα");
		confirmButton.setBounds(10, 11, 169, 51);
		buttonPanel.add(confirmButton);
		cancelButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		cancelButton.setToolTipText("Ακύρωση ενέργειας");
		cancelButton.setBounds(205, 11, 169, 51);
		buttonPanel.add(cancelButton);
	}

	private void addEventListeners() {
		addConfirmButtonListener();
		addCancelButtonListener();
	}

	private void addConfirmButtonListener() {
		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				getInfoFromPanels();
			}
		});
	}

	private void getInfoFromPanels() {
		receiptProperties = receiptInfoPanel.requestReceipt();
		companyProperties = companyInfoPanel.requestCompany();
		if (check(companyProperties) && check(receiptProperties)) {
			createReceipt();
			close();
		} else {
			getToolkit().beep();
		}
	}

	private boolean check(String[] stringToCheck) {
		for (int i = 0; i < stringToCheck.length; i++) {
			if (stringToCheck[i].length() == 0) {
				return false;
			}
		}
		return true;
	}

	private void createReceipt() {
		initializeCompany();
		initializeReceipt();
	}

	private void initializeCompany() {
		newCompany = new Company(
				companyProperties[0],
				companyProperties[1],
				companyProperties[2],
				companyProperties[3],
				Integer.parseInt(companyProperties[4]));
	}

	private void initializeReceipt() {
		newReceipt = new Receipt(
				Integer.parseInt(receiptProperties[0]),
				receiptProperties[1],
				receiptProperties[2],
				Integer.parseInt(receiptProperties[3]),
				newCompany);
	}

	private void close() {
		setVisible(false);
		dispose();
	}

	private void addCancelButtonListener() {
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				close();
			}
		});
	}

	public Receipt getReceipt() {
		return newReceipt;
	}

}
