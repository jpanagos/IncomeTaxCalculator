package userinterface;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
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

	private final JPanel listPanel = new JPanel();
	private NewReceiptWindow newReceiptWindow;
	private ArrayList<Receipt> receipts;
	private JLabel feedbackLabel;

	public ReceiptListWindow(ArrayList<Receipt> receipts) {
		this.receipts = receipts;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReceiptListWindow.class.getResource(
				"/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Λίστα αποδείξεων");
		setBounds(100, 100, 380, 491);
		getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 443, 374, 1);
		getContentPane().add(separator);
		
		JPanel feedbackPanel = new JPanel();
		feedbackPanel.setBounds(0, 444, 374, 22);
		getContentPane().add(feedbackPanel);
		feedbackPanel.setLayout(null);
		
		feedbackLabel = new JLabel("");
		feedbackLabel.setOpaque(true);
		feedbackLabel.setBackground(SystemColor.controlHighlight);
		feedbackLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		feedbackLabel.setBounds(0, 0, 374, 22);
		feedbackPanel.add(feedbackLabel);
		listPanel.setBounds(0, 0, 374, 264);
		listPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(listPanel);
		listPanel.setLayout(null);
		
		JScrollPane receiptListContainer = new JScrollPane();
		receiptListContainer.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
		receiptListContainer.setBounds(10, 11, 354, 240);
		listPanel.add(receiptListContainer);
			JList receiptList = new JList();
			receiptList.setValueIsAdjusting(true);
			receiptList.setModel(new AbstractListModel() {
				String[] values = getReceiptsStringList();
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			receiptList.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
			receiptList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			receiptList.setFont(new Font("Verdana", Font.PLAIN, 14));
			receiptListContainer.setViewportView(receiptList);
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBounds(10, 275, 354, 168);
			getContentPane().add(buttonPanel);
			{
				JButton returnButton = new JButton("Επιστροφή");
				returnButton.setToolTipText("Επιστροφή στο προηγούμενο παράθυρο");
				returnButton.setBounds(113, 122, 130, 33);
				returnButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				buttonPanel.setLayout(null);
				returnButton.setActionCommand("Cancel");
				buttonPanel.add(returnButton);
			}
			
			JButton addReceiptButton = new JButton("Προσθήκη...");
			addReceiptButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					newReceiptWindow = new NewReceiptWindow();
					newReceiptWindow.setLocationRelativeTo(null);
					newReceiptWindow.setVisible(true);
					newReceiptWindow.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e){
								Receipt newReceipt = newReceiptWindow.getReceipt();
								if (newReceipt != null) {
									addNewReceipt(newReceipt);
									setFeedbackMessageAddSuccess();
									Object[] array = getReceiptsStringList();
									receiptList.setListData(array);
									receiptListContainer.revalidate();
									receiptListContainer.repaint();
								}else {
									setFeedbackMessageAddAborted();
								}
							}
						});

				}
			});
			addReceiptButton.setToolTipText("Δημιουργία νέας απόδειξης και προσθήκη στη λίστα");
			addReceiptButton.setBounds(0, 0, 354, 50);
			buttonPanel.add(addReceiptButton);
			addReceiptButton.setFont(new Font("Verdana", Font.PLAIN, 16));
			
			JButton deleteReceiptButton = new JButton("Διαγραφή...");
			deleteReceiptButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			deleteReceiptButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String selected = (String) receiptList.getSelectedValue();
					if (nothingSelected(selected)){
						setFeedbackNoneSelected();
					}else{
						int receiptID = filterID(selected);
						deleteReceipt(receiptID);
						setFeedbackMessageDeleteSuccess();
						Object[] array = getReceiptsStringList();
						receiptList.setListData(array);
						receiptListContainer.revalidate();
						receiptListContainer.repaint();
					}
				}
			});
			deleteReceiptButton.setToolTipText("Διαγραφή επιλεγμένης απόδειξης από τη λίστα");
			deleteReceiptButton.setBounds(0, 61, 354, 50);
			buttonPanel.add(deleteReceiptButton);
			deleteReceiptButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		}
	}
	
	private int filterID(String stringToFilter) {
		String [] splitString = stringToFilter.split(" ");
		return Integer.parseInt(splitString[1]);
	}
	
	private String [] getReceiptsStringList(){
		return createReceiptsStringList();
	}
	
	private String [] createReceiptsStringList(){
		String [] receiptsStringList = new String [receipts.size()];
		for (int index = 0; index < receiptsStringList.length; index++) {
			receiptsStringList[index] = "";
			receiptsStringList[index] += "ID: " + receipts.get(index).getReceiptID()
					+ " | Κατηγορία: " + receipts.get(index).getReceiptCategory()
					+ " | Ποσό: " + receipts.get(index).getReceiptAmount();
		}
		return receiptsStringList;
	}
	private boolean nothingSelected (String selectedString){
		return (selectedString == null);
	}
	
	private void setFeedbackNoneSelected(){
		getToolkit().beep();
		feedbackLabel.setForeground(new Color(128, 0, 0));
		feedbackLabel.setText(
				" Δεν επιλέχθηκε καμία επιταγή! Επιλέξτε κάποια.");
	}
	
	private void setFeedbackMessageAddSuccess() {
		feedbackLabel.setForeground(new Color(0, 128, 0));
		feedbackLabel.setText(" Εισαγωγή επιταγής στη τη λίστα επιτυχής!");
	}
	
	private void setFeedbackMessageAddAborted() {
		feedbackLabel.setForeground(new Color(128, 0, 0));
		feedbackLabel.setText(
				" Η δημιουργία νέας επιταγής ακυρώθηκε από το χρήστη.");
	}
	
	private void setFeedbackMessageDeleteSuccess(){
		feedbackLabel.setForeground(new Color(0, 128, 0));
		feedbackLabel.setText(" Διαγραφή επιταγής από τη λίστα επιτυχής!");
	}
	
	public void setReceiptList(ArrayList<Receipt> newReceiptList){
		this.receipts = newReceiptList;
	}
	
	public ArrayList<Receipt> getUpdatedReceiptList(){
		return receipts;
	}
	
	private void addNewReceipt(Receipt receiptToAdd){
		receipts.add(receiptToAdd);
	}
	
	private void deleteReceipt(int receiptIDToDelete) {
		int index = findReceiptIndex(receiptIDToDelete);
		receipts.remove(index);
	}
	
	private int findReceiptIndex(int receiptID) {
		for (int index = 0; index < receipts.size(); index++) {
			if (receipts.get(index).getReceiptID() == receiptID) {
				return index;
			}
		}
		return -1;
	}
	
}
