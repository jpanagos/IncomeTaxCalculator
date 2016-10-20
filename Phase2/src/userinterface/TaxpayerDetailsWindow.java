package userinterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;

public class TaxpayerDetailsWindow extends JDialog {

	private JTextPane textPane = new JTextPane();
	private JPanel buttonPanel = new JPanel();
	private JButton returnButton = new JButton("Επιστροφή");
	private JPanel infoPanel = new JPanel();

	public TaxpayerDetailsWindow() {
		setTitle("Πληροφορίες Φορολογουμένου");
		setWindowProperties();
		initializeWindowPanels();
		getContentPane().add(buttonPanel);
		getContentPane().add(infoPanel);
		initializeTextPane();
	}

	private void setWindowProperties() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				TaxpayerDetailsWindow.class.getResource(
				"/javax/swing/plaf/metal/icons/ocean/info.png")));
		setResizable(false);
		setBounds(100, 100, 362, 230);
		getContentPane().setLayout(null);
	}

	private void initializeWindowPanels() {
		buttonPanel.setBounds(10, 161, 336, 36);
		buttonPanel.setLayout(null);
		initializeReturnButton();
		buttonPanel.add(returnButton);
		infoPanel.setLayout(null);
		infoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		infoPanel.setBounds(10, 11, 336, 139);
	}

	private void initializeReturnButton() {
		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
			} });
		returnButton.setBounds(112, 0, 111, 36);
		returnButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		returnButton.setActionCommand("OK");
	}

	private void initializeTextPane() {
		textPane.setFont(new Font("Verdana", Font.PLAIN, 17));
		textPane.setEditable(false);
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setBounds(0, 0, 336, 139);
		infoPanel.add(textPane);
	}

	public void setDetails(String taxpayerDetails) {
		MutableAttributeSet set = new SimpleAttributeSet(
				textPane.getParagraphAttributes());
		StyleConstants.setLineSpacing(set, (float) 0.2);
		textPane.setParagraphAttributes(set, true);
		textPane.setText(taxpayerDetails);
	}

}
