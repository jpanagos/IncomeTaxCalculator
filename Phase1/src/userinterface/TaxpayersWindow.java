package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import java.util.ArrayList;
import datamanager.Receipt;
import datamanager.Taxpayer;
import datamanager.TaxpayerDatabase;
import outputmanager.BarChartWindow;
import outputmanager.OutputFactory;
import outputmanager.OutputWriter;
import outputmanager.PieChartWindow;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class TaxpayersWindow extends JDialog {
	
	private TaxpayerDatabase taxpayerDatabase;
	private TaxpayerDetailsWindow taxpayerDetailsWindow;
	private ReceiptListWindow receiptListWindow;
	private LogWindow logWindow;
	private OutputFactory outputFactory;
	private OutputWriter outputWriter;
	private JLabel feedbackLabel;

	public TaxpayersWindow(TaxpayerDatabase database) {
		taxpayerDatabase = database;
		setIconImage(Toolkit.getDefaultToolkit().getImage(TaxpayersWindow.class.getResource(
				"/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		getContentPane().setFont(new Font("Verdana", Font.PLAIN, 13));
		setTitle("Λίστα φορολογουμένων");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 610, 435);
		getContentPane().setLayout(null);
		
		JSeparator feedbackSeparator = new JSeparator();
		feedbackSeparator.setBounds(0, 388, 604, 1);
		getContentPane().add(feedbackSeparator);
		JPanel feedbackPanel = new JPanel();
		feedbackPanel.setBounds(0, 389, 604, 20);
		getContentPane().add(feedbackPanel);
		feedbackPanel.setLayout(null);
		
		feedbackLabel = new JLabel("");
		feedbackLabel.setBackground(SystemColor.controlHighlight);
		feedbackLabel.setOpaque(true);
		feedbackLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		feedbackLabel.setBounds(0, 0, 604, 20);
		feedbackPanel.add(feedbackLabel);
		
		JPanel listPanel = new JPanel();
		listPanel.setBounds(0, 0, 604, 183);
		getContentPane().add(listPanel);
		listPanel.setLayout(null);
		
		JLabel userPromptLabel = new JLabel("Επιλέξτε έναν φορολογούμενο από τη λίστα:");
		userPromptLabel.setBounds(10, 11, 578, 29);
		listPanel.add(userPromptLabel);
		userPromptLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPromptLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		JScrollPane taxpayerListContainer = new JScrollPane();
		taxpayerListContainer.setBounds(10, 39, 584, 144);
		listPanel.add(taxpayerListContainer);
		taxpayerListContainer.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
		
		JList taxpayerList = new JList();
		taxpayerList.setValueIsAdjusting(true);
		taxpayerList.setModel(new AbstractListModel() {
			String[] values = getTaxpayersStringList();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		taxpayerList.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
		taxpayerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taxpayerList.setFont(new Font("Verdana", Font.PLAIN, 15));
		taxpayerListContainer.setViewportView(taxpayerList);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 183, 604, 206);
		getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
			
		JButton showTaxpayerDetailsButton = new JButton("Εμφάνιση στοιχείων");
		showTaxpayerDetailsButton.setBounds(10, 11, 285, 39);
		buttonPanel.add(showTaxpayerDetailsButton);
		showTaxpayerDetailsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selected = (String) taxpayerList.getSelectedValue();
				if (nothingSelected(selected)){
					setFeedbackNoneSelected();
				}else{
					String taxpayerName = filterName(selected);
					Taxpayer taxpayer = taxpayerDatabase.getTaxpayer(taxpayerName);
					String taxpayerDetails = taxpayer.getTaxpayerDetails();
					taxpayerDetailsWindow = new TaxpayerDetailsWindow();
					taxpayerDetailsWindow.setLocationRelativeTo(null);
					taxpayerDetailsWindow.setDetails(taxpayerDetails);
					taxpayerDetailsWindow.setVisible(true);
					setDefaultFeedback();
				}
			}
		});
		showTaxpayerDetailsButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JButton showTaxpayerReceiptsButton = new JButton("Εμφάνιση λίστας αποδείξεων");
		showTaxpayerReceiptsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selected = (String) taxpayerList.getSelectedValue();
				if (nothingSelected(selected)){
					setFeedbackNoneSelected();
				}else{
					String taxpayerName = filterName(selected);
					Taxpayer taxpayer = taxpayerDatabase.getTaxpayer(taxpayerName);
					ArrayList <Receipt> taxpayerReceipts = taxpayer.getTaxpayerReceipts();
					receiptListWindow = new ReceiptListWindow(taxpayerReceipts);
					receiptListWindow.setLocationRelativeTo(null);
					receiptListWindow.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent event){
							ArrayList <Receipt> updatedTaxpayerReceipts = 
									receiptListWindow.getUpdatedReceiptList();
							taxpayerDatabase.updateTaxpayer(taxpayerName, updatedTaxpayerReceipts);
							Taxpayer updatedTaxpayer = taxpayerDatabase.getTaxpayer(taxpayerName);
							String filePath = taxpayerDatabase.getTaxpayerFilePath(taxpayerName);
							String fileExtension = getFileExtension(filePath);
							outputFactory = OutputFactory.createOutputFactory(fileExtension);
							try {
								outputWriter = outputFactory.createOutputWriter(updatedTaxpayer, filePath);
							} catch (FileNotFoundException exception) {
								exception.printStackTrace();
							}
							outputWriter.printFile();
							setFeedbackUpdatedFileText();
							}
						});
					receiptListWindow.setVisible(true);
				}
			}
		});
		
		showTaxpayerReceiptsButton.setBounds(309, 11, 285, 39);
		buttonPanel.add(showTaxpayerReceiptsButton);
		showTaxpayerReceiptsButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JButton showBarChartButton = new JButton("Ραβδόγραμμα φόρων");
		showBarChartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selected = (String) taxpayerList.getSelectedValue();
				if (nothingSelected(selected)){
					setFeedbackNoneSelected();
				}else{
					String taxpayerName = filterName(selected);
					Taxpayer taxpayer = taxpayerDatabase.getTaxpayer(taxpayerName);					
					BarChartWindow barChart = new BarChartWindow(taxpayer);
				}
			}
		});
		showBarChartButton.setBounds(10, 61, 285, 39);
		buttonPanel.add(showBarChartButton);
		showBarChartButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JButton showPieChartButton = new JButton("Διάγραμμα πίτας επιταγών");
		showPieChartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selected = (String) taxpayerList.getSelectedValue();
				if (nothingSelected(selected)){
					setFeedbackNoneSelected();
				}else{
					String taxpayerName = filterName(selected);
					Taxpayer taxpayer = taxpayerDatabase.getTaxpayer(taxpayerName);					
					PieChartWindow pieChart = new PieChartWindow(taxpayer);
				}
			}
		});
		showPieChartButton.setBounds(309, 61, 285, 39);
		buttonPanel.add(showPieChartButton);
		showPieChartButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JButton createTaxpayerLogButton = new JButton("Αποθήκευση στοιχείων");
		createTaxpayerLogButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selected = (String) taxpayerList.getSelectedValue();
				if (nothingSelected(selected)){
					setFeedbackNoneSelected();
				}else{
					String taxpayerName = filterName(selected);
					Taxpayer taxpayer = taxpayerDatabase.getTaxpayer(taxpayerName);
					logWindow = new LogWindow();
					logWindow.setLocationRelativeTo(null);
					logWindow.setTaxpayer(taxpayer);
					logWindow.setVisible(true);
					logWindow.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e){
								String action = logWindow.getAction();
								String result = logWindow.getResult();
								colorizeFeedbackLabel(action);
								updateFeedbackLabel(result);
							}
						});
				}
			}
		});
		createTaxpayerLogButton.setBounds(10, 111, 285, 39);
		buttonPanel.add(createTaxpayerLogButton);
		createTaxpayerLogButton.setToolTipText("Δημιουργία αρχείου αναφοράς");
		createTaxpayerLogButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JButton deleteTaxpayerButton = new JButton("Διαγραφή φορολογουμένου");
		deleteTaxpayerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selected = (String) taxpayerList.getSelectedValue();
				if (nothingSelected(selected)){
					setFeedbackNoneSelected();
				}else{
					String taxpayerName = filterName(selected);
					taxpayerDatabase.removeTaxpayer(taxpayerName);
					setFeedbackMessageDeleteSuccess();
					Object[] array = getTaxpayersStringList();
					taxpayerList.setListData(array);
					taxpayerListContainer.revalidate();
					taxpayerListContainer.repaint();
				}
			}
		});
		
		deleteTaxpayerButton.setBounds(309, 111, 285, 39);
		buttonPanel.add(deleteTaxpayerButton);
		deleteTaxpayerButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		deleteTaxpayerButton.setToolTipText("Αφαίρεση από τη λίστα");
		
		JButton returnButton = new JButton("Επιστροφή");
		returnButton.setBounds(247, 161, 110, 33);
		buttonPanel.add(returnButton);
		returnButton.setToolTipText("Επιστροφή στο αρχικό μενού");
		returnButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});	
	}

		
	public TaxpayerDatabase returnDatabase(){
		return this.taxpayerDatabase;
	}
	
	private String [] getTaxpayersStringList(){
		ArrayList<Taxpayer> taxpayerList = taxpayerDatabase.getTaxpayerDatabase();
		return createTaxpayersStringList(taxpayerList);
	}
	
	private String [] createTaxpayersStringList(ArrayList<Taxpayer> taxpayerList){
		String [] taxpayerStringList = new String [taxpayerList.size()];
		for (int index = 0; index < taxpayerStringList.length; index++){
			Taxpayer taxpayer = taxpayerList.get(index);
			taxpayerStringList[index] = "";
			taxpayerStringList[index] += (taxpayer.getFullName() + " | " + taxpayer.getAFM());
		}
		return taxpayerStringList;
	}
	
	private boolean nothingSelected (String selectedString){
		return (selectedString == null);
	}
	
	private void setDefaultFeedback() {
		feedbackLabel.setText("");
	}
	
	private void setFeedbackNoneSelected(){
		getToolkit().beep();
		feedbackLabel.setForeground(new Color(128, 0, 0));
		feedbackLabel.setText(
				" Δεν επιλέχθηκε κανένας φορολογούμενος! Επιλέξτε έναν και ξαναπροσπαθήστε.");
	}
	
	private void setFeedbackUpdatedFileText() {
		feedbackLabel.setForeground(new Color(0, 0, 128));
		feedbackLabel.setText(" Το αρχείο εισαγωγής φορολογουμένου ενημερώθηκε επιτυχώς!");
		
	}
	
	private String filterName(String stringToFilter){
		String [] splitString = stringToFilter.split(" [|] ");
		return splitString[0];
	}
	
	private void setFeedbackMessageDeleteSuccess(){
		feedbackLabel.setForeground(new Color(0, 128, 0));
		feedbackLabel.setText(" Διαγραφή φορολογουμένου από τη λίστα επιτυχής!");
	}
	
	private void colorizeFeedbackLabel(String action){
		if (actionIsConfirm(action)){
			feedbackLabel.setForeground(new Color(0, 128, 0));
		}else{
			feedbackLabel.setForeground(new Color(128, 0, 0));
		}
	}
	
	private void updateFeedbackLabel(String result){
			feedbackLabel.setText(result);
	}
	
	private boolean actionIsConfirm(String action){
		return (action.equalsIgnoreCase("OK"));
	}
	
	private String getFileExtension(String filePath) {
		return filePath.substring(filePath.lastIndexOf(".")+1);
	}
	
}

