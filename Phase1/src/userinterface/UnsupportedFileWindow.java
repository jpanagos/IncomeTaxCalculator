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

	public UnsupportedFileWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UnsupportedFileWindow.class.getResource(
				"/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		setTitle("Μη υποστηριζόμενο αρχείο!");
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		getContentPane().setLayout(null);
		
		JLabel unsupportedFileLabel = new JLabel("Ο επιλεγμένος τύπος αρχείου δεν υποστηρίζεται!");
		unsupportedFileLabel.setIcon(new ImageIcon(UnsupportedFileWindow.class.getResource(
				"/com/sun/javafx/scene/control/skin/modena/dialog-error.png")));
		unsupportedFileLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		unsupportedFileLabel.setBounds(0, 11, 394, 45);
		getContentPane().add(unsupportedFileLabel);
		
		JButton okButton = new JButton("OK");
		okButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		okButton.setBounds(117, 165, 160, 45);
		getContentPane().add(okButton);
		
		JLabel inputFileInfoLabel = new JLabel("Το επιλεγμένο αρχείο πρέπει να είναι txt ή xml.");
		inputFileInfoLabel.setForeground(Color.GRAY);
		inputFileInfoLabel.setIcon(new ImageIcon(UnsupportedFileWindow.class.getResource(
				"/com/sun/java/swing/plaf/windows/icons/Inform.gif")));
		inputFileInfoLabel.setFont(new Font("Verdana", Font.ITALIC, 12));
		inputFileInfoLabel.setBounds(10, 67, 374, 37);
		getContentPane().add(inputFileInfoLabel);
		
		JLabel tryAgainLabel = new JLabel("Δοκιμάστε ξανά");
		tryAgainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tryAgainLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		tryAgainLabel.setBounds(10, 131, 374, 23);
		getContentPane().add(tryAgainLabel);
	}
}
