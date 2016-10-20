package userinterface;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import datamanager.Receipt;

public class ReceiptListPanel extends JPanel {

	private ArrayList<Receipt> receipts;
	private JScrollPane receiptListContainer = new JScrollPane();
	private JList receiptList = new JList();

	public ReceiptListPanel(ArrayList<Receipt> receiptList) {
		receipts = receiptList;
		setProperties();
		initializeContainer();
		this.add(receiptListContainer);
		setListProperties();
		initializeList();
		receiptListContainer.setViewportView(this.receiptList);
	}

	private void setProperties() {
		setBounds(0, 0, 374, 264);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
	}

	private void initializeContainer() {
		receiptListContainer.setViewportBorder(
				UIManager.getBorder("ScrollPane.border"));
		receiptListContainer.setBounds(10, 11, 354, 240);
	}

	private void setListProperties() {
		receiptList.setValueIsAdjusting(true);
		receiptList.setBorder(UIManager.getBorder(
				"List.focusCellHighlightBorder"));
		receiptList.setSelectionMode(
				ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		receiptList.setFont(new Font("Verdana", Font.PLAIN, 14));
	}

	private void initializeList() {
		receiptList.setModel(new AbstractListModel() {
			String[] values = getReceiptsStringList();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			} });
	}

	private String[] getReceiptsStringList() {
		return createReceiptsStringList();
	}

	private String[] createReceiptsStringList() {
		String[] receiptsStringList = new String[receipts.size()];
		for (int index = 0; index < receiptsStringList.length; index++) {
			receiptsStringList[index] = "ID: ";
			receiptsStringList[index] += receipts.get(index).getReceiptID()
				+ " | Κατηγορία: " + receipts.get(index).getReceiptCategory()
				+ " | Ποσό: " + receipts.get(index).getReceiptAmount();
		}
		return receiptsStringList;
	}

	public String getSelectedValue() {
		return (String) receiptList.getSelectedValue();
	}

	public void refresh(ArrayList<Receipt> receipts) {
		this.receipts = receipts;
		Object[] array = getReceiptsStringList();
		receiptList.setListData(array);
		receiptListContainer.revalidate();
		receiptListContainer.repaint();
	}

}
