package datamanager;

import java.util.ArrayList;

public class TaxpayerDatabase {
	private ArrayList<Taxpayer> taxpayerList;
	private ArrayList<String> taxpayerInputFilesList;
	
	public TaxpayerDatabase(){
		this.taxpayerList = new ArrayList<Taxpayer>();
		this.taxpayerInputFilesList = new ArrayList<String>();
	}
	
	public ArrayList<Taxpayer> getTaxpayerDatabase(){
		return taxpayerList;
	}
	
	public void updateTaxpayerList(ArrayList<Taxpayer> newDatabase){
		this.taxpayerList = newDatabase;
	}
	
	public void updateInputFilesList(ArrayList <String> newInputFilesList){
		this.taxpayerInputFilesList = newInputFilesList;
	}
	
	public void addTaxpayer(Taxpayer taxpayer){
		taxpayerList.add(taxpayer);
	}
	
	public void addFilePath(String filePath){
		taxpayerInputFilesList.add(filePath);
	}

	public String getTaxpayerInputFile(String taxpayersName){
		for (int index = 0; index < taxpayerList.size(); index++){
			if (checkNameEquality(taxpayerList.get(index), taxpayersName)){
				return taxpayerInputFilesList.get(index).toString();
			}
		}
		return "";
	}
	
	public Taxpayer findTaxpayer(String taxpayerNameToFind){
		return taxpayerList.get(findTaxpayersIndex(taxpayerNameToFind));
	}
	
	private int findTaxpayersIndex(String taxpayerName) {
		for (int index = 0; index < taxpayerList.size(); index++){
			if (checkNameEquality(taxpayerList.get(index), taxpayerName)){
				return index;
			}
		}
		return -1;
	}
	
	public void updateInputFileList(ArrayList<String> newList){
		this.taxpayerInputFilesList = newList;
	}
	
	private boolean checkNameEquality(Taxpayer taxpayerToCheck, String taxpayerNameToMatch){
		return (taxpayerToCheck.getFullName().equalsIgnoreCase(taxpayerNameToMatch));
	}
	
	public void removeTaxpayer(String taxpayerNameToRemove){
		int indexToRemove = -1;
		for (int index = 0; index < taxpayerList.size(); index++){
			if (taxpayerList.get(index).getFullName().equals(taxpayerNameToRemove)){
				indexToRemove = index;
				break;
			}
		}
		taxpayerList.remove(indexToRemove);
		taxpayerInputFilesList.remove(indexToRemove);
	}
	
	public Taxpayer getTaxpayer(String taxpayerName){
		return (findTaxpayer(taxpayerName));
	}
	
	public String getTaxpayerFilePath(String taxpayerName) {
		return taxpayerInputFilesList.get(findTaxpayersIndex(taxpayerName));
	}
	
	public void updateTaxpayer(String taxpayerName, ArrayList<Receipt> newReceiptList) {
		getTaxpayer(taxpayerName).updateTaxpayerReceipts(newReceiptList);
	}
}
