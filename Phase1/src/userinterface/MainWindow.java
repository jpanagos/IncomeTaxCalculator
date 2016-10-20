package userinterface;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import datamanager.DatabaseFactory;
import datamanager.Taxpayer;
import datamanager.TaxpayerDatabase;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSeparator;

public class MainWindow extends WindowAdapter{

	private JFrame incomeTaxCalculator;
	private TaxpayersWindow taxpayerWindow;
	private InputWindow inputWindow;
	private TaxpayerDatabase taxpayerDatabase;
	private JLabel feedbackLabel = new JLabel();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.incomeTaxCalculator.setVisible(true);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		taxpayerDatabase = DatabaseFactory.createDatabase();
		System.setProperty("line.separator", "\r\n");
		initialize();

	}

	private void initialize() {
		incomeTaxCalculator = new JFrame();
		incomeTaxCalculator.setResizable(false);
		incomeTaxCalculator.setTitle("Income Tax Calculator");
		incomeTaxCalculator.setBounds(100, 100, 360, 325);
		incomeTaxCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		incomeTaxCalculator.getContentPane().setLayout(null);
		
		JSeparator feedbackSeparator = new JSeparator();
		feedbackSeparator.setBounds(0, 280, 354, 1);
		incomeTaxCalculator.getContentPane().add(feedbackSeparator);
		
		JPanel feedbackPanel = new JPanel();
		feedbackPanel.setBounds(0, 281, 354, 21);
		incomeTaxCalculator.getContentPane().add(feedbackPanel);
		feedbackPanel.setLayout(null);
		
		setFeedbackDefaultText();
		feedbackLabel.setForeground(new Color(128, 128, 128));
		feedbackLabel.setBackground(SystemColor.controlHighlight);
		feedbackLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		feedbackLabel.setOpaque(true);
		feedbackLabel.setBounds(0, 0, 354, 19);
		feedbackPanel.add(feedbackLabel);
		
		
		JPanel taxpayerButtonPanel = new JPanel();
		taxpayerButtonPanel.setBounds(0, 0, 354, 90);
		incomeTaxCalculator.getContentPane().add(taxpayerButtonPanel);
		taxpayerButtonPanel.setLayout(null);
		

		
		JButton insertTaxpayerButton = new JButton("Εισαγωγή φορολογουμένου");
		insertTaxpayerButton.setBounds(10, 11, 334, 68);
		taxpayerButtonPanel.add(insertTaxpayerButton);
		insertTaxpayerButton.setToolTipText("Επιλογή αρχείων και εισαγωγή στη λίστα");
		insertTaxpayerButton.setFont(new Font("Verdana", Font.PLAIN, 18));
		insertTaxpayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputWindow = new InputWindow();
				inputWindow.setLocationRelativeTo(null);
				inputWindow.setVisible(true);
				Taxpayer t = inputWindow.getTaxpayer();
				String tPath = inputWindow.getInputFilePath();
				if (t != null){
					taxpayerDatabase.addTaxpayer(t);
					taxpayerDatabase.addFilePath(tPath);
					inputWindow.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e){
							setFeedbackLabelAddSuccess();
						}
					});
				}

			}
		});
		
		JPanel taxpayerListButtonPanel = new JPanel();
		taxpayerListButtonPanel.setBounds(0, 90, 354, 90);
		incomeTaxCalculator.getContentPane().add(taxpayerListButtonPanel);
		taxpayerListButtonPanel.setLayout(null);
		
		JButton taxpayersListButton = new JButton("Λίστα φορολογουμένων");
		taxpayersListButton.setBounds(10, 11, 334, 68);
		taxpayerListButtonPanel.add(taxpayersListButton);
		taxpayersListButton.setToolTipText("Προβολή λίστας φορολογουμένων");
		taxpayersListButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				taxpayerWindow = new TaxpayersWindow(taxpayerDatabase);
				taxpayerWindow.setLocationRelativeTo(null);
				taxpayerWindow.setVisible(true);
				taxpayerWindow.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e){
						taxpayerDatabase = taxpayerWindow.returnDatabase();
						setFeedbackDefaultText();
					}
				});
			}
		});
		taxpayersListButton.setFont(new Font("Verdana", Font.PLAIN, 18));
		
		JPanel exitButtonPanel = new JPanel();
		exitButtonPanel.setBounds(0, 180, 354, 90);
		incomeTaxCalculator.getContentPane().add(exitButtonPanel);
		exitButtonPanel.setLayout(null);
		
		JButton exitButton = new JButton("Έξοδος");
		exitButton.setBounds(10, 11, 334, 68);
		exitButtonPanel.add(exitButton);
		exitButton.setToolTipText("Κλείσιμο εφαρμογής");
		exitButton.setFont(new Font("Verdana", Font.PLAIN, 18));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExitWindow exitWindow = new ExitWindow();
				exitWindow.setLocationRelativeTo(null);
				exitWindow.setVisible(true);
			}
		});
		
		incomeTaxCalculator.setLocationRelativeTo(null);

	}
	
	private void setFeedbackLabelAddSuccess(){
		feedbackLabel.setForeground(new Color(0, 128, 0));
		feedbackLabel.setText(" Εισαγωγή φορολογουμένου επιτυχής!");
	}
	
	private void setFeedbackDefaultText(){
		feedbackLabel.setForeground(new Color(128, 128, 128));
		feedbackLabel.setText(" Καλωσήλθατε! Επιλέξτε μια λειτουργία.");
	}
	
}
