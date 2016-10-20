package outputmanager;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import datamanager.Company;
import datamanager.Receipt;
import datamanager.Taxpayer;

public class XMLOutputWriter extends OutputWriter {
	
	public XMLOutputWriter (Taxpayer taxpayer, String filePath) throws FileNotFoundException{
		super(taxpayer, filePath);
	}
	
	public void printFile() {
		printTaxpayerDetails(super.taxpayer);
		printReceiptsDetails(super.taxpayer);
		super.close();
	}
	
	private void printTaxpayerDetails(Taxpayer taxpayer) {
		super.printLine("<Name> " + taxpayer.getFullName() + " </Name>");
		super.printLine("<AFM> " + taxpayer.getAFM() + " </AFM>");
		super.printLine("<Status> " + taxpayer.getFamilyStatus() + " </Status>");
		super.printLine("<Income> " + taxpayer.getIncome() + " </Income>");
		super.printLine("");
		super.printLine("");
	}
	
	private void printReceiptsDetails(Taxpayer taxpayer) {
		super.printLine("<Receipts>");
		super.printLine("");
		ArrayList <Receipt> taxpayerReceipts = taxpayer.getTaxpayerReceipts();
		for (Receipt receipt : taxpayerReceipts) {
			printReceiptInfo(receipt);
			super.printLine("");
		}
		super.printLine("</Receipts>");
	}
	
	private void printReceiptInfo(Receipt receipt) {
		super.printLine("<ReceiptID> " + receipt.getReceiptID() + " </ReceiptID>");
		super.printLine("<Date> " + receipt.getReceiptDate() + " </Date>");
		super.printLine("<Kind> " + receipt.getReceiptCategory() + " </Kind>");
		super.printLine("<Amount> " + receipt.getReceiptAmount() + " </Amount>");
		printCompanyDetails(receipt.getReceiptCompany());
	}
	
	private void printCompanyDetails(Company company) {
		super.printLine("<Company> " + company.getCompanyName() + " </Company>");
		super.printLine("<Country> " + company.getCompanyCountry() + " </Country>");
		super.printLine("<City> " + company.getCompanyCity() + " </City>");
		super.printLine("<Street> " + company.getCompanyAddress() + " </Street>");
		super.printLine("<Number> " + company.getCompanyAddressNumber() + " </Number>");
	}
	
}
