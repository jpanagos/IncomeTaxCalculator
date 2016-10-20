package datamanager;

import java.util.ArrayList;

public abstract class Taxpayer {
	private String fullName;
	private int AFM;
	private String familyStatus;
	private int income;
	private ArrayList<Receipt> taxpayerReceipts;
	private int entertainmentReceiptsSum;
	private int basicReceiptsSum;
	private int travelReceiptsSum;
	private int healthReceiptsSum;
	private int otherReceiptsSum;
	private int totalReceiptsSum;

	protected Taxpayer(String fullName, int AFM, String familyStatus, int income){
		this.fullName = fullName;
		this.AFM = AFM;
		this.familyStatus = familyStatus;
		this.income = income;
		initializeReceiptsSums();
		taxpayerReceipts = new ArrayList<Receipt>();
	}
	
	private void initializeReceiptsSums(){
		this.entertainmentReceiptsSum = 0;
		this.basicReceiptsSum = 0;
		this.travelReceiptsSum = 0;
		this.healthReceiptsSum = 0;
		this.otherReceiptsSum = 0;
		this.totalReceiptsSum = 0;
	}
	
	public Taxpayer(){}
	
	public void addReceipt(Receipt receipt){
		taxpayerReceipts.add(receipt);
		updateSums(receipt);
	}
	
	public void deleteReceipt(int receiptIndex){
		Receipt receiptToRemove = taxpayerReceipts.get(receiptIndex);
		taxpayerReceipts.remove(receiptIndex);
		receiptToRemove.setAmount((-1)*receiptToRemove.getReceiptAmount());
		updateSums(receiptToRemove);
	}
	
	public String getFullName(){
		return fullName;
	}
	
	public int getAFM(){
		return AFM;
	}
	
	public String getFamilyStatus(){
		return familyStatus;
	}
	
	public int getIncome(){
		return income;
	}
	
	public ArrayList<Receipt> getTaxpayerReceipts(){
		return taxpayerReceipts;
	}
	
	public void updateTaxpayerReceipts(ArrayList<Receipt> newTaxpayerReceipts){
		taxpayerReceipts = newTaxpayerReceipts;
		initializeReceiptsSums();
		updateReceiptsSums();
	}
	
	private void updateReceiptsSums() {
		for (Receipt receipt : taxpayerReceipts) {
			updateSums(receipt);
		}
	}
	
	public double getTotalTax(){
		return (getBasicTax() + getTaxIncrease());
	}
	
	public double getTaxIncrease(){
		return calculateFinalTax();
	}
	
	private double calculateFinalTax(){
		return (getBasicTax() * getReceiptsTax());
	}
	
	public double getBasicTax(){
		return calculateBasicTax();
	}
	
	protected abstract double calculateBasicTax();
	
	public double getReceiptsTax(){
		return calculateReceiptsTax();
	}
	
	private double calculateReceiptsTax(){
		
		double receiptSum = this.getTotalReceiptsSum();
		
		if ( (receiptSum < ((20.0/100)*income)) ){
			return (0.08);
		}else if( (receiptSum < ((40.0/100)*income)) ){
			return (0.04);
		}else if( (receiptSum < ((60.0/100)*income)) ){
			return (-0.15);
		}else{
			return (-0.30);
		}
		
	}
	
	public int getTotalReceiptsSum(){
		return totalReceiptsSum;
	}
	
	private void updateSums(Receipt receipt){
		
		String receiptCategory = receipt.getReceiptCategory();
		int receiptAmount = receipt.getReceiptAmount();
		
		if (receiptCategory.equals("Entertainment")){
			updateEntertainmentSum(receiptAmount);
		}else if (receiptCategory.equals("Basic")){
			updateBasicSum(receiptAmount);
		}else if (receiptCategory.equals("Travel")){
			updateTravelSum(receiptAmount);
		}else if (receiptCategory.equals("Health")){
			updateHealthSum(receiptAmount);
		}else{
			updateOtherSum(receiptAmount);
		}
		updateTotalReceiptsSum(receiptAmount);
	}
	
	private void updateEntertainmentSum(int amount){
		this.entertainmentReceiptsSum += amount;
	}
	
	private void updateBasicSum(int amount){
		this.basicReceiptsSum += amount;
	}
	
	private void updateTravelSum(int amount){
		this.travelReceiptsSum += amount;
	}
	
	private void updateHealthSum(int amount){
		this.healthReceiptsSum += amount;
	}
	
	private void updateOtherSum(int amount){
		this.otherReceiptsSum += amount;
	}
	
	private void updateTotalReceiptsSum(int amount){
		this.totalReceiptsSum += amount;
	}
	
	public int getEntertainmentReceiptsSum(){
		return entertainmentReceiptsSum;
	}
	
	public int getBasicReceiptsSum(){
		return basicReceiptsSum;
	}
	
	public int getTravelReceiptsSum(){
		return travelReceiptsSum;
	}
	
	public int getHealthReceiptsSum(){
		return healthReceiptsSum;
	}
	
	public int getOtherReceiptsSum(){
		return otherReceiptsSum;
	}
	
	public String getTaxpayerDetails(){
		String details = "";
		details += ("Name: " + getFullName() + " \n");
		details += ("AFM: " + getAFM() + " \n");
		details += ("Family status: " + getFamilyStatus() + " \n");
		details += ("Income: " + getIncome() + " \n");
		return details;
	}
	
}
