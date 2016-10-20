package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConfirmExitWindow extends JDialog {

	private JLabel confirmLabel = new JLabel("Είστε σίγουρος;");
	private JButton confirmExitButton = new JButton("Έξοδος");
	private JButton cancelExitButton = new JButton("Άκυρο");

	public ConfirmExitWindow() {
		setTitle("Επιβεβαίωση εξόδου");
		setWindowProperties();
		initializeWindowComponents();
		setConfirmButtonListener();
		setCancelButtonListener();
		addComponentsToWindow();
	}

	private void setWindowProperties() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
			ConfirmExitWindow.class.getResource(
			"/com/sun/javafx/scene/control/skin/caspian/dialog-confirm.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 284, 190);
		getContentPane().setLayout(null);
	}

	private void initializeWindowComponents() {
		confirmLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		confirmLabel.setIcon(new ImageIcon(ConfirmExitWindow.class.getResource(
			"/com/sun/javafx/scene/control/skin/caspian/dialog-confirm.png")));
		confirmLabel.setBounds(10, 11, 258, 74);
		confirmExitButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		confirmExitButton.setBounds(10, 96, 117, 55);
		cancelExitButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		cancelExitButton.setBounds(151, 96, 117, 55);
	}

	private void setConfirmButtonListener() {
		confirmExitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
	}

	private void setCancelButtonListener() {
		cancelExitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				setVisible(false);
				dispose();
			}
		});
	}

	private void addComponentsToWindow() {
		getContentPane().add(confirmLabel);
		getContentPane().add(confirmExitButton);
		getContentPane().add(cancelExitButton);
	}

}
