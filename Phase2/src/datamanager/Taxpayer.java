package datamanager;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import outputmanager.*;

public class Taxpayer {

	private OutputFactory outputFactory;
	private OutputWriter outputWriter;
	private LogFactory logFactory;
	private LogWriter logWriter;
	private String fileExtension;
	private String fullName;
	private int AFM;
	private String familyStatus;
	private int income;
	private ArrayList<Receipt> taxpayerReceipts;
	private int entertainmentSum, basicSum, travelSum, healthSum, otherSum;
	private int totalSum;
	private int[] limits;
	private double[] flatTax;
	private double[] multiplier;

	public Taxpayer(String fullName, int AFM, String familyStatus, int income,
			int[] incomeLimits, double[] flatTax, double[] taxMultiplier) {
		this.fullName = fullName;
		this.AFM = AFM;
		this.familyStatus = familyStatus;
		this.income = income;
		this.limits = incomeLimits;
		this.flatTax = flatTax;
		this.multiplier = taxMultiplier;
		taxpayerReceipts = new ArrayList<Receipt>();
	}

	public void createLogFactory(String fileExtension) {
		this.fileExtension = fileExtension;
		logFactory = LogFactory.createLogFactory(fileExtension);
	}

	public void createLogWriter() throws FileNotFoundException {
		logWriter = logFactory.createLogWriter();
		logWriter.initialize(getAFM());
	}

	public void createLog() {
		logWriter.write("Name", fullName);
		logWriter.write("AFM", Integer.toString(AFM));
		logWriter.write("Income", Integer.toString(income));
		logWriter.write("Basic Tax", Double.toString(getBasicTax()));
		logWriter.write("Tax Increase", Double.toString(getTaxIncrease()));
		logWriter.write("Total Tax", Double.toString(getTotalTax()));
		createReceiptsLog();
	}

	public double getBasicTax() {
		if (income < limits[0]) { return (flatTax[0] + multiplier[0] *income); }
		if (income < limits[1]) { 
			return (flatTax[1] + multiplier[1] * (income - limits[0])); }
		if (income < limits[2]) { 
			return (flatTax[2] + multiplier[2] * (income - limits[1])); }
		if (income < limits[3]) { 
			return (flatTax[3] + multiplier[3] * (income - limits[2])); }
		return (flatTax[4] + multiplier[4] * (income - limits[3]));
	}

	public double getTaxIncrease() {
		return (getBasicTax() * getReceiptsTax());
	}

	public double getReceiptsTax() {
		calculateSums();
		double receiptSum = this.getTotalReceiptsSum();
		if ((receiptSum < ((20.0 / 100) * income))) { return (0.08); }
		if ((receiptSum < ((40.0 / 100) * income))) { return (0.04); }
		if ((receiptSum < ((60.0 / 100) * income))) { return (-0.15); }
		return (-0.30);
	}
	
	public void calculateSums() {
		totalSum = 0; healthSum = 0; travelSum = 0; otherSum = 0;
		entertainmentSum = 0; basicSum = 0;
		for (Receipt receipt : taxpayerReceipts) {
			updateReceiptsSums(receipt);
		}
	}

	private void updateReceiptsSums(Receipt receipt) {
		String category = receipt.getReceiptCategory();
		int amount = receipt.getReceiptAmount();
		if (category.equals("Entertainment")) { entertainmentSum += amount; }
		if (category.equals("Basic")) { basicSum += amount; }
		if (category.equals("Travel")) { travelSum += amount; }
		if (category.equals("Health")) { healthSum += amount; }
		if (category.equals("Other")) { otherSum += amount; }
		totalSum += amount;
	}

	public int getTotalReceiptsSum() {
		return totalSum;
	}

	public double getTotalTax() {
		return (getBasicTax() + getTaxIncrease());
	}

	private void createReceiptsLog() {
		logWriter.write("Receipts", Integer.toString(totalSum));
		logWriter.write("Entertainment", Integer.toString(entertainmentSum));
		logWriter.write("Basic", Integer.toString(basicSum));
		logWriter.write("Travel", Integer.toString(travelSum));
		logWriter.write("Health", Integer.toString(healthSum));
		logWriter.write("Other", Integer.toString(otherSum));
		logWriter.close();
	}

	public void createOutputFactory(String filePath) {
		String fileExtension = getFileExtension(filePath);
		this.fileExtension = fileExtension;
		outputFactory = OutputFactory.createOutputFactory(fileExtension);
		try {
			outputWriter = outputFactory.createOutputWriter(filePath);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	private String getFileExtension(String filePath) {
		return filePath.substring(filePath.lastIndexOf(".") + 1);
	}

	public void printFile() {
		outputWriter.write("Name", fullName);
		outputWriter.write("AFM", Integer.toString(AFM));
		outputWriter.write("Status", familyStatus);
		outputWriter.write("Income", Integer.toString(income));
		outputWriter.writeEmpty();
		outputWriter.writeEmpty();
		printReceipts();
	}

	private void printReceipts() {
		if (taxpayerReceipts.size() == 0) { return; }
		outputWriter.write("Receipts");
		for (Receipt receipt : taxpayerReceipts) {
			receipt.setOutputWriter(outputWriter);
			receipt.writeReceiptDetails();
		}
		if (fileExtension.equals("xml")) { outputWriter.write("/Receipts"); }
		outputWriter.close();
	}

	public void addReceipt(Receipt receipt) {
		taxpayerReceipts.add(receipt);
	}

	public void deleteReceipt(int receiptIndex) {
		taxpayerReceipts.remove(receiptIndex);
	}

	public String getFullName() {
		return fullName;
	}

	public int getAFM() {
		return AFM;
	}

	public int getIncome() {
		return income;
	}

	public ArrayList<Receipt> getTaxpayerReceipts() {
		return taxpayerReceipts;
	}

	public void updateTaxpayerReceipts(ArrayList<Receipt> newTaxpayerReceipts) {
		taxpayerReceipts = newTaxpayerReceipts;
	}

	public int getReceiptsSum(String category) {
		if (category.equals("Entertainment")) { return entertainmentSum; }
		if (category.equals("Basic")) { return basicSum; }
		if (category.equals("Travel")) { return travelSum; }
		if (category.equals("Health")) { return healthSum; }
		if (category.equals("Other")) { return otherSum; }
		return 0;
	}

	public String getTaxpayerDetails() {
		String details = ("Name: " + fullName + " \n" + "AFM: " + AFM + " \n");
		details += ("Family status: " + familyStatus + " \n");
		details += ("Income: " + income + " \n");
		return details;
	}

}
