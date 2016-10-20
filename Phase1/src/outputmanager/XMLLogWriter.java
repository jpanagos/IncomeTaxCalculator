package outputmanager;

import java.io.FileNotFoundException;
import datamanager.Taxpayer;

public class XMLLogWriter extends LogWriter {

	public XMLLogWriter(Taxpayer taxpayer) throws FileNotFoundException{
		super(taxpayer);
		String fileName = taxpayer.getAFM() + "_LOG.xml";
    	super.initializeOutput(fileName);
	}
	
	public void createTaxpayerLog(){
		printTaxpayerDetails(super.taxpayer);
		printTaxpayerTaxes(super.taxpayer);
		printReceiptsSums(super.taxpayer);
		super.close();
	}
		
	private void printTaxpayerDetails(Taxpayer taxpayer){
		super.printLine("<Name> " + taxpayer.getFullName() + " </Name>");
		super.printLine("<AFM> " + taxpayer.getAFM() + " </AFM>");
		super.printLine("<Income> " + taxpayer.getIncome() + " </Income>");
	}
	
	private void printTaxpayerTaxes(Taxpayer taxpayer){
		super.printLine("<BasicTax> " + taxpayer.getBasicTax() + " </BasicTax>");
		super.printLine("<TaxIncrease> " + taxpayer.getTaxIncrease() + " </TaxIncrease>");
		super.printLine("<TotalTax> " + taxpayer.getTotalTax() + " </TotalTax>");
	}
	
	private void printReceiptsSums(Taxpayer taxpayer){
		super.printLine("<Receipts> " + taxpayer.getTotalReceiptsSum() + " </Receipts>");
		super.printLine(
				"<Entertainment> " + taxpayer.getEntertainmentReceiptsSum() + " </Entertainment>");
		super.printLine("<Basic> " + taxpayer.getBasicReceiptsSum() + " </Basic>");
		super.printLine("<Travel> " + taxpayer.getTravelReceiptsSum() + " </Travel>");
		super.printLine("<Health> " + taxpayer.getHealthReceiptsSum() + " </Health>");
		super.printLine("<Other> " + taxpayer.getOtherReceiptsSum() + " </Other>");
	}
}
