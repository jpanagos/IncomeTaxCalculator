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
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class InputWindow extends JDialog {

	private InputFactory inputFactory;
	private InputParser inputParser;
	private Taxpayer taxpayer;
	private String inputFilePath;

		public InputWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InputWindow.class.getResource(
				"/com/sun/java/swing/plaf/windows/icons/File.gif")));
		setLocationByPlatform(true);
		setTitle("Επιλογή αρχείου");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 692, 560);
		getContentPane().setLayout(null);
		
		JSeparator panelSeparator = new JSeparator();
		panelSeparator.setBounds(0, 48, 686, 1);
		getContentPane().add(panelSeparator);
		
		JPanel userAssistPanel = new JPanel();
		userAssistPanel.setBounds(0, 0, 686, 50);
		getContentPane().add(userAssistPanel);
		userAssistPanel.setLayout(null);
		
		JLabel userPromptLabel = new JLabel("Επιλέξτε ένα αρχείο της μορφής <ΑΦΜ>_INFO:");
		userPromptLabel.setBounds(10, 0, 424, 30);
		userAssistPanel.add(userPromptLabel);
		userPromptLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JLabel exampleLabel = new JLabel("π.χ. 12345_INFO.txt, 12345_INFO.xml");
		exampleLabel.setBounds(20, 25, 424, 25);
		userAssistPanel.add(exampleLabel);
		exampleLabel.setForeground(Color.GRAY);
		exampleLabel.setFont(new Font("Verdana", Font.ITALIC, 10));
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setApproveButtonToolTipText("");
		File workingDirectory = new File(System.getProperty("user.dir"));
		fileChooser.setCurrentDirectory(workingDirectory);
		fileChooser.setFont(new Font("Verdana", Font.PLAIN, 12));
		fileChooser.setFileHidingEnabled(true);
		fileChooser.setBounds(20, 61, 656, 459);
		getContentPane().add(fileChooser);
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        String command = actionEvent.getActionCommand();
		        if (command.equals(JFileChooser.APPROVE_SELECTION)) {
		          File selectedFile = fileChooser.getSelectedFile();
		          if (fileIsSupported(selectedFile)){
			          try {
							readInput(selectedFile);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
		          }else{
		        	  UnsupportedFileWindow unsupportedFileWindow = new UnsupportedFileWindow();
		        	  unsupportedFileWindow.setLocationRelativeTo(null);
		        	  unsupportedFileWindow.setVisible(true);
		          }
		          setVisible(false);
		          dispose();
		        }else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
		        	taxpayer = null;
		        	setVisible(false);
		        	dispose();
		        }
		      }
		    };
		    fileChooser.addActionListener(actionListener);
	}

	
	private void readInput(File file) throws FileNotFoundException{
        inputFactory = InputFactory.createInputFactory(getFileExtension(file));
        inputParser = inputFactory.createInputParser(file.getAbsolutePath());
        inputParser.parseInput();
        getTaxpayerFromParser();
        setInputFilePath(file.getAbsolutePath());
	}
	
	private void getTaxpayerFromParser(){
		taxpayer = inputParser.getTaxpayer();
	}
	
	private void  setInputFilePath(String filePath){
		this.inputFilePath = filePath;
	}
	
	public Taxpayer getTaxpayer(){
		return taxpayer;
	}
	
	private boolean fileIsSupported(File file){
		return (getFileExtension(file).equals("txt") || getFileExtension(file).equals("xml"));
	}
	
	private String getFileExtension(File file){
        String filePath = file.getAbsolutePath();
        String fileExtension = filePath.substring(filePath.lastIndexOf(".")+1);
        return fileExtension;
	}
	
	public String getInputFilePath(){
		return inputFilePath;
	}
	
}
