package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExitWindow extends JDialog {

	public ExitWindow() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExitWindow.class.getResource(
				"/com/sun/javafx/scene/control/skin/caspian/dialog-confirm.png")));
		setTitle("Επιβεβαίωση εξόδου");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 284, 190);
		getContentPane().setLayout(null);
		
		JLabel confirmLabel = new JLabel("Είστε σίγουρος;");
		confirmLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		confirmLabel.setIcon(new ImageIcon(ExitWindow.class.getResource(
				"/com/sun/javafx/scene/control/skin/caspian/dialog-confirm.png")));
		confirmLabel.setBounds(10, 11, 258, 74);
		getContentPane().add(confirmLabel);
		
		JButton confirmExitButton = new JButton("Έξοδος");
		confirmExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		confirmExitButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		confirmExitButton.setBounds(10, 96, 117, 55);
		getContentPane().add(confirmExitButton);
		
		JButton cancelExitButton = new JButton("Άκυρο");
		cancelExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
		cancelExitButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		cancelExitButton.setBounds(151, 96, 117, 55);
		getContentPane().add(cancelExitButton);
	}
}
