package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import datamanager.Receipt;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReceiptListWindow extends JDialog {

	private NewReceiptWindow newReceiptWindow;
	private ArrayList<Receipt> receipts;
	private JLabel feedbackLabel = new JLabel("");
	private ReceiptListPanel receiptListPanel;
	private JPanel buttonPanel = new JPanel();
	private JButton addReceiptButton = new JButton("Προσθήκη...");
	private JButton deleteReceiptButton = new JButton("Διαγραφή...");
	private JButton returnButton = new JButton("Επιστροφή");
	private JSeparator separator = new JSeparator();

	public ReceiptListWindow(ArrayList<Receipt> receipts) {
		this.receipts = receipts;
		setTitle("Λίστα αποδείξεων");
		setWindowProperties();
		initializePanels();
		initializeFeedbackElements();
		addAddReceiptActionListener();
		addDeleteReceiptActionListener();
		addReturnActionListener();
	}

	private void setWindowProperties() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ReceiptListWindow.class.getResource(
				"/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setBounds(100, 100, 380, 491);
		getContentPane().setLayout(null);
	}

	private void initializePanels() {
		receiptListPanel = new ReceiptListPanel(this.receipts);
		receiptListPanel.setBounds(0, 0, 374, 264);
		getContentPane().add(receiptListPanel);
		buttonPanel.setBounds(0, 275, 374, 191);
		buttonPanel.setLayout(null);
		getContentPane().add(buttonPanel);
		initializeButtonPanelElements();
	}

	private void initializeButtonPanelElements() {
		addReceiptButton.setBounds(10, 0, 354, 50);
		addReceiptButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		deleteReceiptButton.setBounds(10, 61, 354, 50);
		deleteReceiptButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		returnButton.setBounds(122, 122, 130, 33);
		returnButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		addElementsToPanel();
	}

	private void addElementsToPanel() {
		buttonPanel.add(addReceiptButton);
		buttonPanel.add(deleteReceiptButton);
		buttonPanel.add(returnButton);
	}

	private void initializeFeedbackElements() {
		separator.setBounds(0, 168, 374, 1);
		buttonPanel.add(separator);
		feedbackLabel.setBounds(0, 169, 374, 22);
		feedbackLabel.setOpaque(true);
		feedbackLabel.setBackground(SystemColor.controlHighlight);
		feedbackLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		buttonPanel.add(feedbackLabel);
	}

	private void addAddReceiptActionListener() {
		addReceiptButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				newReceiptWindow = new NewReceiptWindow();
				newReceiptWindow.setLocationRelativeTo(null);
				newReceiptWindow.setVisible(true);
				setNewReceiptWindowEventListener();
			}
		});
	}

	private void setNewReceiptWindowEventListener() {
		newReceiptWindow.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent event) {
				Receipt newReceipt = newReceiptWindow.getReceipt();
				if (newReceipt != null) {
					addNewReceipt(newReceipt);
				}
			}
		});
	}

	private void addNewReceipt(Receipt receiptToAdd) {
		receipts.add(receiptToAdd);
		setFeedbackMessageAddSuccess();
		receiptListPanel.refresh(getUpdatedReceiptList());
	}

	private void setFeedbackMessageAddSuccess() {
		feedbackLabel.setForeground(new Color(0, 128, 0));
		feedbackLabel.setText(" Εισαγωγή επιταγής στη τη λίστα επιτυχής!");
	}

	public ArrayList<Receipt> getUpdatedReceiptList() {
		return receipts;
	}

	private void addDeleteReceiptActionListener() {
		deleteReceiptButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				String selected = receiptListPanel.getSelectedValue();
				checkSelectedValue(selected);
			}
		});
	}

	private void checkSelectedValue(String selected) {
		if (nothingSelected(selected)) {
			setFeedbackNoneSelected();
		} else {
			int receiptID = filterID(selected);
			deleteReceipt(receiptID);
		}
	}

	private boolean nothingSelected(String selectedString) {
		return (selectedString == null);
	}

	private void setFeedbackNoneSelected() {
		getToolkit().beep();
		feedbackLabel.setForeground(new Color(128, 0, 0));
		feedbackLabel.setText(
				" Δεν επιλέχθηκε καμία απόδειξη! Επιλέξτε κάποια.");
	}

	private int filterID(String stringToFilter) {
		String[] splitString = stringToFilter.split(" ");
		return Integer.parseInt(splitString[1]);
	}

	private void deleteReceipt(int receiptIDToDelete) {
		int index = findReceiptIndex(receiptIDToDelete);
		receipts.remove(index);
		setFeedbackMessageDeleteSuccess();
		receiptListPanel.refresh(getUpdatedReceiptList());
	}

	private int findReceiptIndex(int receiptID) {
		for (int index = 0; index < receipts.size(); index++) {
			if (receipts.get(index).getReceiptID() == receiptID) {
				return index;
			}
		}
		return -1;
	}

	private void setFeedbackMessageDeleteSuccess() {
		feedbackLabel.setForeground(new Color(0, 128, 0));
		feedbackLabel.setText(" Διαγραφή απόδειξης από τη λίστα επιτυχής!");
	}

	private void addReturnActionListener() {
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				setVisible(false);
				dispose();
			}
		});
	}

	public void setReceiptList(ArrayList<Receipt> newReceiptList) {
		this.receipts = newReceiptList;
	}

}
