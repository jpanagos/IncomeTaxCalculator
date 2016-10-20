package datamanager;

import java.util.ArrayList;

public class TaxpayerDatabase {

	private ArrayList<Taxpayer> taxpayerList;
	private ArrayList<String> taxpayerInputFilesList;

	public TaxpayerDatabase() {
		this.taxpayerList = new ArrayList<Taxpayer>();
		this.taxpayerInputFilesList = new ArrayList<String>();
	}

	public ArrayList<Taxpayer> getTaxpayerDatabase() {
		return taxpayerList;
	}

	public void updateTaxpayerList(ArrayList<Taxpayer> newDatabase) {
		this.taxpayerList = newDatabase;
	}

	public void updateInputFilesList(ArrayList<String> newInputFilesList) {
		this.taxpayerInputFilesList = newInputFilesList;
	}

	public void addTaxpayer(Taxpayer taxpayer) {
		taxpayerList.add(taxpayer);
	}

	public void addFilePath(final String filePath) {
		taxpayerInputFilesList.add(filePath);
	}

	public String getTaxpayerInputFile(String taxpayersName) {
		for (int index = 0; index < taxpayerList.size(); index++) {
			if (checkNameEquality(taxpayerList.get(index), taxpayersName)) {
				return taxpayerInputFilesList.get(index).toString();
			}
		}
		return "";
	}

	public Taxpayer findTaxpayer(final String taxpayerNameToFind) {
		return taxpayerList.get(findTaxpayersIndex(taxpayerNameToFind));
	}

	private int findTaxpayersIndex(final String taxpayerName) {
		for (int index = 0; index < taxpayerList.size(); index++) {
			if (checkNameEquality(taxpayerList.get(index), taxpayerName)) {
				return index;
			}
		}
		return -1;
	}

	private boolean checkNameEquality(
			Taxpayer taxpayerToCheck, String taxpayerNameToMatch) {
		return (taxpayerToCheck.getFullName().equalsIgnoreCase(
				taxpayerNameToMatch));
	}

	public void updateInputFileList(ArrayList<String> newList) {
		this.taxpayerInputFilesList = newList;
	}

	public void removeTaxpayer(final String taxpayerNameToRemove) {
		int indexToRemove = findTaxpayersIndex(taxpayerNameToRemove);
		taxpayerList.remove(indexToRemove);
		taxpayerInputFilesList.remove(indexToRemove);
	}

	public String getTaxpayerFilePath(Taxpayer taxpayer) {
		String taxpayerName = taxpayer.getFullName();
		return taxpayerInputFilesList.get(findTaxpayersIndex(taxpayerName));
	}

	public void updateTaxpayer(
			Taxpayer taxpayer, ArrayList<Receipt> newReceiptList) {
		String taxpayerName = taxpayer.getFullName();
		getTaxpayer(taxpayerName).updateTaxpayerReceipts(newReceiptList);
	}

	public Taxpayer getTaxpayer(String taxpayerName) {
		return (findTaxpayer(taxpayerName));
	}

}
