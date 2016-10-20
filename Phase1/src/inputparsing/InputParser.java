package inputparsing;

import java.util.Scanner;
import datamanager.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class InputParser {
	
	private FileInputStream inputStream = null;
	private Scanner inputReader = null;
	private Taxpayer taxpayer;
	
	
	protected InputParser(String filePath) throws FileNotFoundException{
		initializeInputStream(filePath);
		initializeInputReader();
	}
	
	private void initializeInputStream(String filePath){
		try{
			inputStream = new FileInputStream(filePath);
		}catch(FileNotFoundException exception){
			System.out.println("Error opening file: " + filePath);
			System.exit(0);
		}
	}
	
	private void initializeInputReader(){
		inputReader = new Scanner(inputStream);
	}
	
	private String getLine(){
		return inputReader.nextLine();
	}
	
	protected abstract String splitText(String textToSplit);
	
	public void parseInput(){
		readTaxpayer();
		if (hasReceipts()){
			parseReceipts();
		}
		inputReader.close();
	}
	
	private void readTaxpayer(){
		String name;
		int  afm;
		String status;
		int income;
		name = splitText(getLine());
		afm = Integer.parseInt(splitText(getLine()));
		status = splitText(getLine());
		income = Integer.parseInt(splitText(getLine()));
		taxpayer = TaxpayerFactory.createTaxpayer(name, afm, status, income);
	}
	
	private boolean hasReceipts(){
		String line;
		while (hasNextLine()){
			line = getLine();
			if (!(isEmptyLine(line))){
				if (checkForReceipts(line)){
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasNextLine(){
		return inputReader.hasNextLine();
	}
	
	private boolean isEmptyLine(String line){
		return (line.length() == 0);
	}
	
	protected abstract boolean checkForReceipts(String line);
	
	private void parseReceipts(){
		String line;
		while (hasNextLine()){
			line = getLine();
			if (isReceiptID(line)){
				initializeReceipt(line);
			}
		}
	}
	
	protected abstract boolean isReceiptID(String line);
	
	private void initializeReceipt(String line){
		
		Receipt receipt;
		int  receiptID;
		String date;
		String category;
		int amount;
		Company company;
		receiptID = Integer.parseInt(splitText(line));
		date = splitText(getLine());
		category = splitText(getLine());
		amount = Integer.parseInt(splitText(getLine()));
		company = createCompany();
		receipt = ReceiptFactory.createReceipt(receiptID, date, category, amount, company);
		taxpayer.addReceipt(receipt);
		
	}
	
	private Company createCompany(){
		Company company;
		String name;
		String country;
		String city;
		String address;
		int number;
		name = splitText(getLine());
		country = splitText(getLine());
		city = splitText(getLine());
		address = splitText(getLine());
		number = Integer.parseInt(splitText(getLine()));
		company = CompanyFactory.createCompany(name, country, city, address, number);
		return company;
	}
	
	public Taxpayer getTaxpayer(){
		return taxpayer;
	}
	
}
