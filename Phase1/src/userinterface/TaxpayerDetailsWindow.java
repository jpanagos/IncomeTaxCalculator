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

	public TaxpayerDetailsWindow() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Πληροφορίες Φορολογουμένου");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TaxpayerDetailsWindow.class.getResource(
				"/javax/swing/plaf/metal/icons/ocean/info.png")));
		setResizable(false);
		setBounds(100, 100, 362, 230);
		getContentPane().setLayout(null);
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBounds(10, 161, 336, 36);
			getContentPane().add(buttonPanel);
			buttonPanel.setLayout(null);
			{
				JButton returnButton = new JButton("Επιστροφή");
				returnButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				returnButton.setBounds(112, 0, 111, 36);
				buttonPanel.add(returnButton);
				returnButton.setFont(new Font("Verdana", Font.PLAIN, 13));
				returnButton.setActionCommand("OK");
				getRootPane().setDefaultButton(returnButton);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setBounds(10, 11, 336, 139);
			getContentPane().add(panel);
			

			textPane.setFont(new Font("Verdana", Font.PLAIN, 17));
			textPane.setEditable(false);
			textPane.setBackground(UIManager.getColor("Button.background"));
			textPane.setBounds(0, 0, 336, 139);
			panel.add(textPane);
		}
	}
	
	public void setDetails(String taxpayerDetails){
		MutableAttributeSet set = new SimpleAttributeSet(textPane.getParagraphAttributes());
		StyleConstants.setLineSpacing(set, (float) 0.2);
		textPane.setParagraphAttributes(set, true);
		textPane.setText(taxpayerDetails);
	}
	
}
