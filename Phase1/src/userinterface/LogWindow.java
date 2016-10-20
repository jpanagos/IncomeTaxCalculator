package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import datamanager.Taxpayer;
import outputmanager.LogFactory;
import outputmanager.LogWriter;
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

	private final JPanel outputSelectionPanel = new JPanel();
	private LogFactory outputFactory;
	private LogWriter outputWriter;
	private Taxpayer taxpayer;
	private String action;
	private String result;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public LogWindow() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogWindow.class.getResource(
				"/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		setTitle("Αρχείο αναφοράς");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 270, 225);
		getContentPane().setLayout(null);
		
		JSeparator panelSeparator = new JSeparator();
		panelSeparator.setBounds(0, 134, 264, 1);
		getContentPane().add(panelSeparator);
		outputSelectionPanel.setBounds(0, 0, 264, 134);
		outputSelectionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(outputSelectionPanel);
		outputSelectionPanel.setLayout(null);
		
		JLabel promptLabel = new JLabel("Επιλέξτε μορφή αποθήκευσης:");
		promptLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		promptLabel.setBounds(10, 11, 244, 39);
		outputSelectionPanel.add(promptLabel);
		
		JRadioButton plainTextRadioButton = new JRadioButton("Μορφή απλού κειμένου (.txt)");
		plainTextRadioButton.setActionCommand("txt");
		plainTextRadioButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		plainTextRadioButton.setBounds(10, 57, 244, 23);
		outputSelectionPanel.add(plainTextRadioButton);
		
		JRadioButton XMLRadioButton = new JRadioButton("Μορφή τύπου XML (.xml)");
		XMLRadioButton.setActionCommand("xml");
		XMLRadioButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		XMLRadioButton.setBounds(10, 93, 244, 23);
		outputSelectionPanel.add(XMLRadioButton);
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBounds(0, 134, 264, 66);
			getContentPane().add(buttonPanel);
			buttonPanel.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e){
						if  (buttonGroup.getSelection() != null){
						outputFactory = LogFactory.createLogFactory(
								buttonGroup.getSelection().getActionCommand());
								setResult(buttonGroup.getSelection().getActionCommand());
						try {
							outputWriter = outputFactory.createLogWriter(taxpayer);
						} catch (FileNotFoundException exception) {
							exception.printStackTrace();
						}
						outputWriter.createTaxpayerLog();
						setAction("OK");
						setVisible(false);
						dispose();
						}else{
							getToolkit().beep();
						}
					}
				});
				okButton.setFont(new Font("Verdana", Font.PLAIN, 16));
				okButton.setBounds(10, 11, 107, 44);
				buttonPanel.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Ακύρωση");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setCanceledResult();
						setAction("Cancel");
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setFont(new Font("Verdana", Font.PLAIN, 16));
				cancelButton.setBounds(147, 11, 107, 44);
				buttonPanel.add(cancelButton);
			}
		}
		buttonGroup.add(plainTextRadioButton);
		buttonGroup.add(XMLRadioButton);
		
	}
	
	public void setTaxpayer(Taxpayer taxpayer){
		this.taxpayer = taxpayer;
	}
	
	private void setAction(String action){
		this.action = action;
	}
	
	public String getAction(){
		return action;
	}
	
	private void setResult(String fileExtension){
		result = "";
		result += (
				" Το αρχείο " + taxpayer.getAFM() + "_LOG." + fileExtension + " δημιουργήθηκε επιτυχώς!");
	}
	
	public String getResult(){
		return result;
	}
	
	private void setCanceledResult(){
		result = "";
		result += (" Η δημιουργία αρχείου αναφοράς ακυρώθηκε από το χρήστη."	);
	}
	
}
