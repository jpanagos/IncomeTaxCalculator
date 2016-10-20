package userinterface;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import datamanager.Taxpayer;
import datamanager.TaxpayerDatabase;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private JSeparator separator = new JSeparator();
	private JButton insertTaxpayerButton =
			new JButton("Εισαγωγή φορολογουμένου");
	private JButton taxpayersListButton =
			new JButton("Λίστα φορολογουμένων");
	private JButton exitButton = new JButton("Έξοδος");	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.incomeTaxCalculator.setVisible(true);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			} });
	}

	public MainWindow() {
		taxpayerDatabase = new TaxpayerDatabase();
		initializeWindow();
	}

	private void initializeWindow() {
		setWindowProperties();
		initializeButtons();
		separator.setBounds(0, 280, 354, 1);
		incomeTaxCalculator.getContentPane().add(separator);
		addButonsToFrame();
	}

	private void setWindowProperties() {
		incomeTaxCalculator = new JFrame();
		incomeTaxCalculator.setResizable(false);
		incomeTaxCalculator.setTitle("Income Tax Calculator");
		incomeTaxCalculator.setBounds(100, 100, 360, 325);
		incomeTaxCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		incomeTaxCalculator.getContentPane().setLayout(null);
		incomeTaxCalculator.setLocationRelativeTo(null);
		initializeLabel();
	}

	private void initializeLabel() {
		feedbackLabel.setBounds(0, 281, 354, 19);
		feedbackLabel.setBackground(SystemColor.controlHighlight);
		feedbackLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		feedbackLabel.setOpaque(true);
		setFeedbackDefaultText();
		incomeTaxCalculator.getContentPane().add(feedbackLabel);
	}

	private void setFeedbackDefaultText() {
		feedbackLabel.setForeground(new Color(128, 128, 128));
		feedbackLabel.setText(" Καλωσήλθατε! Επιλέξτε μια λειτουργία.");
	}

	private void initializeButtons() {
		insertTaxpayerButton.setBounds(10, 11, 334, 68);
		insertTaxpayerButton.setFont(new Font("Verdana", Font.PLAIN, 18));
		taxpayersListButton.setBounds(10, 106, 334, 68);		
		taxpayersListButton.setFont(new Font("Verdana", Font.PLAIN, 18));
		exitButton.setBounds(10, 201, 334, 68);
		exitButton.setFont(new Font("Verdana", Font.PLAIN, 18));
		setButtonTooltips();
	}

	private void setButtonTooltips() {
		insertTaxpayerButton.setToolTipText(
				"Επιλογή αρχείων και εισαγωγή στη λίστα");
		taxpayersListButton.setToolTipText("Προβολή λίστας φορολογουμένων");
		exitButton.setToolTipText("Κλείσιμο εφαρμογής");
	}

	private void addButonsToFrame() {
		addInsertActionListener();
		addListActionListener();
		addExitActionListener();
		incomeTaxCalculator.getContentPane().add(insertTaxpayerButton);
		incomeTaxCalculator.getContentPane().add(taxpayersListButton);
		incomeTaxCalculator.getContentPane().add(exitButton);
	}

	private void addInsertActionListener() {
		insertTaxpayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				startInputWindow();
				getTaxpayerAndPath();
			}
		});
	}

	private void startInputWindow() {
		inputWindow = new InputWindow();
		inputWindow.setLocationRelativeTo(null);
		inputWindow.setVisible(true);	
	}

	private void getTaxpayerAndPath() {
		Taxpayer taxpayer = inputWindow.getTaxpayer();
		String taxpayerFilePath = inputWindow.getInputFilePath();
		if (taxpayer != null){
			taxpayerDatabase.addTaxpayer(taxpayer);
			taxpayerDatabase.addFilePath(taxpayerFilePath);
			addInputWindowListener();
		}		
	}

	private void addInputWindowListener() {
		inputWindow.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent event){
				setFeedbackLabelAddSuccess();
			}
		});		
	}

	private void setFeedbackLabelAddSuccess() {
		feedbackLabel.setForeground(new Color(0, 128, 0));
		feedbackLabel.setText(" Εισαγωγή φορολογουμένου επιτυχής!");
	}

	private void addListActionListener() {
		taxpayersListButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				startTaxpayerWindow();
				addTaxpayerWindowActionListener();
			}
		});
	}

	private void startTaxpayerWindow() {
		taxpayerWindow = new TaxpayersWindow(taxpayerDatabase);
		taxpayerWindow.setLocationRelativeTo(null);
		taxpayerWindow.setVisible(true);		
	}

	private void addTaxpayerWindowActionListener() {
		taxpayerWindow.addWindowListener(new WindowAdapter() {
		public void windowClosed(WindowEvent e){
				taxpayerDatabase = taxpayerWindow.returnDatabase();
				setFeedbackDefaultText();
			}
		});		
	}

	private void addExitActionListener() {
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmExitWindow exitWindow = new ConfirmExitWindow();
				exitWindow.setLocationRelativeTo(null);
				exitWindow.setVisible(true);
			}
		});
	}

}
