package outputmanager;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import datamanager.Company;
import datamanager.Receipt;
import datamanager.Taxpayer;

public class TextOutputWriter extends OutputWriter {
	
	public TextOutputWriter (Taxpayer taxpayer, String filePath) throws FileNotFoundException{
		super(taxpayer, filePath);
	}

	public void printFile() {
		printTaxpayerDetails(super.taxpayer);
		printReceiptsDetails(super.taxpayer);
		super.close();
	}
	
	private void printTaxpayerDetails(Taxpayer taxpayer) {
		super.printLine("Name: " + taxpayer.getFullName());
		super.printLine("AFM: " + taxpayer.getAFM());
		super.printLine("Status: " + taxpayer.getFamilyStatus());
		super.printLine("Income: " + taxpayer.getIncome());
		super.printLine("");
		super.printLine("");
	}
	
	private void printReceiptsDetails(Taxpayer taxpayer) {
		super.printLine("Receipts:");
		super.printLine("");
		ArrayList <Receipt> taxpayerReceipts = taxpayer.getTaxpayerReceipts();
		for (Receipt receipt : taxpayerReceipts) {
			printReceiptInfo(receipt);
			super.printLine("");
		}
	}
	
	private void printReceiptInfo(Receipt receipt) {
		super.printLine("Receipt ID: " + receipt.getReceiptID());
		super.printLine("Date: " + receipt.getReceiptDate());
		super.printLine("Kind: " + receipt.getReceiptCategory());
		super.printLine("Amount: " + receipt.getReceiptAmount());
		printCompanyDetails(receipt.getReceiptCompany());
	}
	
	private void printCompanyDetails(Company company) {
		super.printLine("Company: " + company.getCompanyName());
		super.printLine("Country: " + company.getCompanyCountry());
		super.printLine("City: " + company.getCompanyCity());
		super.printLine("Street: " + company.getCompanyAddress());
		super.printLine("Number: " + company.getCompanyAddressNumber());
	}
	
}
