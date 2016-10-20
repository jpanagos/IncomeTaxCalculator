package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import datamanager.Taxpayer;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.Toolkit;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class LogWindow extends JDialog {

	private Taxpayer taxpayer;
	private String result;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JSeparator separator = new JSeparator();
	private JLabel promptLabel = new JLabel("Επιλέξτε μορφή αποθήκευσης:");
	private JRadioButton plainTextRadioButton = new JRadioButton(
			"Μορφή απλού κειμένου (.txt)");
	private JRadioButton xMLRadioButton = new JRadioButton(
			"Μορφή τύπου XML (.xml)");
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Ακύρωση");

	public LogWindow() {
		setTitle("Αρχείο αναφοράς");
		setWindowProperties();
		setWindowElements();
		initializeRadioButtons();
		initializeActionButtons();
	}

	private void setWindowProperties() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				LogWindow.class.getResource(
				"/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 270, 225);
		getContentPane().setLayout(null);
	}

	private void setWindowElements() {
		promptLabel.setBounds(12, 12, 240, 39);
		promptLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		getContentPane().add(promptLabel);
		separator.setBounds(0, 132, 264, 1);
		getContentPane().add(separator);
	}

	private void initializeRadioButtons() {
		plainTextRadioButton.setBounds(12, 57, 244, 23);
		plainTextRadioButton.setActionCommand("txt");
		plainTextRadioButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		xMLRadioButton.setBounds(12, 89, 244, 23);
		xMLRadioButton.setActionCommand("xml");
		xMLRadioButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		addButtonsToWindowAndGroup();
	}

	private void addButtonsToWindowAndGroup() {
		getContentPane().add(plainTextRadioButton);
		getContentPane().add(xMLRadioButton);
		buttonGroup.add(plainTextRadioButton);
		buttonGroup.add(xMLRadioButton);
	}

	private void initializeActionButtons() {
		okButton.setBounds(12, 144, 107, 44);
		okButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		addOkActionListener();
		getContentPane().add(okButton);
		cancelButton.setBounds(145, 144, 107, 44);
		cancelButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		addCancelActionListener();
		getContentPane().add(cancelButton);
	}

	private void addOkActionListener() {
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (buttonGroup.getSelection() != null) {
					initializeTaxpayersLog();
				} else {
					getToolkit().beep();
				}
			} });
	}

	private void initializeTaxpayersLog() {
		initializeTaxpayersLogFactory();
		try {
			taxpayer.createLogWriter();
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		createLog();
	}

	private void initializeTaxpayersLogFactory() {
		taxpayer.createLogFactory(
				buttonGroup.getSelection().getActionCommand());
				setResult(buttonGroup.getSelection().getActionCommand());
	}

	private void setResult(String fileExtension) {
		result = "";
		result += (" Το αρχείο " + taxpayer.getAFM() + "_LOG."
				+ fileExtension + " δημιουργήθηκε επιτυχώς!");
	}

	private void createLog() {
		taxpayer.createLog();
		setVisible(false);
		dispose();
	}

	private void addCancelActionListener() {
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				setVisible(false);
				dispose();
			}
		});
	}

	public void setTaxpayer(Taxpayer taxpayer) {
		this.taxpayer = taxpayer;
	}

	public String getResult() {
		return result;
	}

}
