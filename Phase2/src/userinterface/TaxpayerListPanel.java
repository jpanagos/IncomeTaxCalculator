package userinterface;

import javax.swing.JPanel;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.util.ArrayList;
import datamanager.Taxpayer;
import datamanager.TaxpayerDatabase;

public class TaxpayerListPanel extends JPanel {

	protected PropertyChangeSupport propertyChangeSupport;
	private TaxpayerDatabase taxpayerDatabase;
	private Taxpayer taxpayer;
	private JScrollPane taxpayerListContainer = new JScrollPane();
	private JList taxpayerList = new JList();
	private JLabel promptLabel = new JLabel(
			"Επιλέξτε έναν φορολογούμενο από τη λίστα:");

	public TaxpayerListPanel(TaxpayerDatabase database) {
		this.taxpayerDatabase = database;
		setProperties();
		initializeListContainer();
	}

	private void setProperties() {
		setLayout(null);
		setBounds(100, 100, 604, 184);
		propertyChangeSupport = new PropertyChangeSupport(this);
		addLabels();
	}

	private void addLabels() {
		promptLabel.setBounds(10, 11, 584, 29);
		promptLabel.setHorizontalAlignment(SwingConstants.CENTER);
		promptLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		add(promptLabel);
	}

	private void initializeListContainer() {
		taxpayerListContainer.setBounds(10, 39, 584, 144);
		add(taxpayerListContainer);
		taxpayerListContainer.setViewportBorder(
				UIManager.getBorder("ScrollPane.border"));
		taxpayerList.setValueIsAdjusting(true);
		initializeTaxpayerList();
		taxpayerListContainer.setViewportView(taxpayerList);
	}

	private void initializeTaxpayerList() {
		taxpayerList.setBorder(UIManager.getBorder(
				"List.focusCellHighlightBorder"));
		taxpayerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taxpayerList.setFont(new Font("Verdana", Font.PLAIN, 15));
		setListModel();
	}

	private void setListModel() {
		taxpayerList.setModel(new AbstractListModel() {
			String[] values = getTaxpayersStringList();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			} });
	}

	private String[] getTaxpayersStringList() {
		ArrayList<Taxpayer> taxpayerList =
				this.taxpayerDatabase.getTaxpayerDatabase();
		return createTaxpayersStringList(taxpayerList);
	}

	private String[] createTaxpayersStringList(
			ArrayList<Taxpayer> taxpayerList) {
		String[] taxpayerStringList = new String[taxpayerList.size()];
		for (int index = 0; index < taxpayerStringList.length; index++) {
			Taxpayer taxpayer = taxpayerList.get(index);
			taxpayerStringList[index] =
					(taxpayer.getFullName() + " | " + taxpayer.getAFM());
		}
		return taxpayerStringList;
	}

	public void addTaxpayerListEventListener() {
		taxpayerList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					if (getSelectedValue() != null) {
						setTaxpayer(getSelectedValue()); }
				}
			}
		});
	}

	public String getSelectedValue() {
		if (taxpayerList.getSelectedValue() != null) {
			return taxpayerList.getSelectedValue().toString();
		} else {
			return null;
		}
	}

	private void setTaxpayer(String selected) {
		String taxpayerName = filterName(selected);
		taxpayer = taxpayerDatabase.getTaxpayer(taxpayerName);
		propertyChangeSupport.firePropertyChange(taxpayerName, null, null);
	}

	private String filterName(String stringToFilter) {
		String[] splitString = stringToFilter.split(" [|] ");
		return splitString[0];
	}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

	public void deleteSelectedTaxpayer() {
		String selected = getSelectedValue();
		String taxpayerName = filterName(selected);
		taxpayerDatabase.removeTaxpayer(taxpayerName);
		refresh();
	}

	private void refresh() {
		Object[] array = getTaxpayersStringList();
		taxpayerList.setListData(array);
		taxpayerListContainer.revalidate();
		taxpayerListContainer.repaint();
	}

	public Taxpayer getSelectedTaxpayer() {
		return taxpayer;
	}

	public TaxpayerDatabase getTaxpayerDatabase() {
		return taxpayerDatabase;
	}

}
