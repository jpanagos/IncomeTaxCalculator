package userinterface;

import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFileChooser;
import datamanager.Taxpayer;
import inputparsing.*;
import java.awt.Toolkit;
import javax.swing.JSeparator;

public class InputWindow extends JDialog {

	private InputFactory inputFactory;
	private InputParser inputParser;
	private Taxpayer taxpayer;
	private String inputFilePath;
	private JSeparator separator = new JSeparator();
	private JLabel userPromptLabel =
			new JLabel("Επιλέξτε ένα αρχείο της μορφής <ΑΦΜ>_INFO:");
	private JLabel exampleLabel =
			new JLabel("π.χ. 12345_INFO.txt, 12345_INFO.xml");
	private JFileChooser fileChooser = new JFileChooser();
	private File workingDirectory = new File(System.getProperty("user.dir"));

	public InputWindow() {
		setTitle("Επιλογή αρχείου");
		setWindowProperties();
		initializeFileChooser();
		initializeLabels();
		initializeSeparator();
	}

	private void setWindowProperties() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InputWindow.class.
			getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		setLocationByPlatform(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 692, 560);
		getContentPane().setLayout(null);
		setResizable(false);
	}

	private void initializeFileChooser() {
		fileChooser.setApproveButtonToolTipText("");
		fileChooser.setCurrentDirectory(workingDirectory);
		fileChooser.setFont(new Font("Verdana", Font.PLAIN, 12));
		fileChooser.setFileHidingEnabled(true);
		fileChooser.setBounds(20, 61, 656, 459);
		getContentPane().add(fileChooser);
		setFileChooserActionListener();
	}

	private void setFileChooserActionListener() {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				initializeTaxpayer(actionEvent);
				setVisible(false);
				dispose();
			}
		};
		fileChooser.addActionListener(actionListener);
	}

	private void initializeTaxpayer(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals(JFileChooser.APPROVE_SELECTION)) {
        	checkSelectedFile(fileChooser.getSelectedFile());
        } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
        	taxpayer = null;
        }
	}

	private void checkSelectedFile(File selectedFile) {
		if (fileIsSupported(selectedFile)) {
			readTaxpayerFromFile(selectedFile);
		} else {
			createUnsupportedWindow();
		}
	}

	private boolean fileIsSupported(File file) {
		return (getFileExtension(file).equals("txt")
				|| getFileExtension(file).equals("xml"));
	}

	private String getFileExtension(File file) {
        String filePath = file.getAbsolutePath();
        return (filePath.substring(filePath.lastIndexOf(".") + 1));
	}

	private void readTaxpayerFromFile(File file) {
		try {
			readInput(file);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	private void readInput(File file) throws FileNotFoundException {
        inputFactory = InputFactory.createInputFactory(getFileExtension(file));
        inputParser = inputFactory.createInputParser(file.getAbsolutePath());
        inputParser.parseInput();
        taxpayer = inputParser.getTaxpayer();
        setInputFilePath(file.getAbsolutePath());
	}

	private void setInputFilePath(String filePath) {
		this.inputFilePath = filePath;
	}

	private void createUnsupportedWindow() {
    	  UnsupportedFileWindow unsupportedFileWindow =
      			  new UnsupportedFileWindow();
      	  unsupportedFileWindow.setLocationRelativeTo(null);
      	  unsupportedFileWindow.setVisible(true);
	}

	private void initializeLabels() {
		userPromptLabel.setBounds(10, 0, 424, 30);
		getContentPane().add(userPromptLabel);
		userPromptLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		exampleLabel.setBounds(10, 24, 424, 25);
		getContentPane().add(exampleLabel);
		exampleLabel.setForeground(Color.GRAY);
		exampleLabel.setFont(new Font("Verdana", Font.ITALIC, 10));
	}

	private void initializeSeparator() {
		separator.setBounds(0, 48, 686, 1);
		getContentPane().add(separator);
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public Taxpayer getTaxpayer() {
		return taxpayer;
	}

}
