package outputmanager;

import java.io.FileNotFoundException;
import datamanager.Taxpayer;

public class TextLogWriter extends LogWriter {
	
    public TextLogWriter(Taxpayer taxpayer) throws FileNotFoundException{
    	super(taxpayer);
    	String fileName = taxpayer.getAFM() + "_LOG.txt";
    	super.initializeOutput(fileName);
    }
	
	public void createTaxpayerLog(){
		printTaxpayerDetails(super.taxpayer);
		printTaxpayerTaxes(super.taxpayer);
		printReceiptsSums(super.taxpayer);
		super.close();
	}
	
	private void printTaxpayerDetails(Taxpayer taxpayer){
		super.printLine("Name: " + taxpayer.getFullName());
		super.printLine("AFM: " + taxpayer.getAFM());
		super.printLine("Income: " + taxpayer.getIncome());
	}
	
	private void printTaxpayerTaxes(Taxpayer taxpayer){
		super.printLine("Basic Tax: " + taxpayer.getBasicTax());
		super.printLine("Tax Increase: " + taxpayer.getTaxIncrease());
		super.printLine("Total Tax: " + taxpayer.getTotalTax());
	}
	
	private void printReceiptsSums(Taxpayer taxpayer){
		super.printLine("Total Receipts Gathered: " + taxpayer.getTotalReceiptsSum());
		super.printLine("Entertainment: " + taxpayer.getEntertainmentReceiptsSum());
		super.printLine("Basic: " + taxpayer.getBasicReceiptsSum());
		super.printLine("Travel: " + taxpayer.getTravelReceiptsSum());
		super.printLine("Health: " + taxpayer.getHealthReceiptsSum());
		super.printLine("Other: " + taxpayer.getOtherReceiptsSum());
	}
	
}
