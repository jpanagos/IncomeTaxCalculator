package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class UnsupportedFileWindow extends JDialog {

	private JLabel unsupportedFileLabel = new JLabel(
			"Ο επιλεγμένος τύπος αρχείου δεν υποστηρίζεται!");
	private JButton okButton = new JButton("OK");
	private JLabel inputFileInfoLabel = new JLabel(
			"Το επιλεγμένο αρχείο πρέπει να είναι txt ή xml.");
	private JLabel tryAgainLabel = new JLabel("Δοκιμάστε ξανά");

	public UnsupportedFileWindow() {
		setTitle("Μη υποστηριζόμενο αρχείο!");
		setWindowProperties();
		initializeLabels();
		initializeOkButton();
		getContentPane().add(okButton);
	}

	private void setWindowProperties() {
		setIconImage(Toolkit.getDefaultToolkit().
				getImage(UnsupportedFileWindow.class.getResource(
				"/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		getContentPane().setLayout(null);
	}

	private void initializeLabels() {
		initializeUnsupportedFileLabel();
		initializeInputFileInfoLabel();
		initializeTryAgainLabel();
		addLabelsToWindow();
	}

	private void initializeUnsupportedFileLabel() {
		unsupportedFileLabel.setIcon(
				new ImageIcon(UnsupportedFileWindow.class.getResource(
				"/com/sun/javafx/scene/control/skin/modena/dialog-error.png")));
		unsupportedFileLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		unsupportedFileLabel.setBounds(0, 11, 394, 45);
	}

	private void initializeInputFileInfoLabel() {
		inputFileInfoLabel.setForeground(Color.GRAY);
		inputFileInfoLabel.setIcon(new ImageIcon(
				UnsupportedFileWindow.class.getResource(
				"/com/sun/java/swing/plaf/windows/icons/Inform.gif")));
		inputFileInfoLabel.setFont(new Font("Verdana", Font.ITALIC, 12));
		inputFileInfoLabel.setBounds(10, 67, 374, 37);
	}

	private void initializeTryAgainLabel() {
		tryAgainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tryAgainLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		tryAgainLabel.setBounds(10, 131, 374, 23);
	}

	private void addLabelsToWindow() {
		getContentPane().add(unsupportedFileLabel);
		getContentPane().add(inputFileInfoLabel);
		getContentPane().add(tryAgainLabel);
	}

	private void initializeOkButton() {
		okButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				dispose();
			}
		});
		okButton.setBounds(117, 165, 160, 45);
	}

}
