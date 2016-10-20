package inputparsing;

import java.util.Scanner;
import datamanager.TaxpayerFactory;
import datamanager.Taxpayer;
import datamanager.Receipt;
import datamanager.Company;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class InputParser {

	private FileInputStream inputStream = null;
	private Scanner inputReader = null;
	private Taxpayer taxpayer;

	protected InputParser(final String filePath) throws FileNotFoundException {
		initializeInputStream(filePath);
		initializeInputReader();
	}

	private void initializeInputStream(final String filePath) {
		try {
			inputStream = new FileInputStream(filePath);
		} catch (FileNotFoundException exception) {
			System.out.println("Error opening file: " + filePath);
			System.exit(0);
		}
	}

	private void initializeInputReader() {
		inputReader = new Scanner(inputStream);
	}

	public void parseInput() {
		readTaxpayer();
		if (hasReceipts()) {
			parseReceipts();
		}
		inputReader.close();
	}

	private void readTaxpayer() {
		String name = splitText(getLine());
		int afm = Integer.parseInt(splitText(getLine()));
		String status = splitText(getLine());
		int income = Integer.parseInt(splitText(getLine()));
		taxpayer = TaxpayerFactory.createTaxpayer(name, afm, status, income);
	}

	protected abstract String splitText(String textToSplit);

	private String getLine() {
		return inputReader.nextLine();
	}

	private boolean hasReceipts() {
		while (hasNextLine()) {
			String line = getLine();
			if (!(isEmptyLine(line))) {
				if (checkForReceipts(line)) { return true; }
			}
		}
		return false;
	}

	private boolean hasNextLine() {
		return inputReader.hasNextLine();
	}

	private boolean isEmptyLine(final String line) {
		return (line.length() == 0);
	}

	protected abstract boolean checkForReceipts(String line);

	private void parseReceipts() {
		String line;
		while (hasNextLine()) {
			line = getLine();
			if (isReceiptID(line)) {
				initializeReceipt(line);
			}
		}
	}

	protected abstract boolean isReceiptID(String line);

	private void initializeReceipt(final String line) {
		Receipt receipt;
		int receiptID = Integer.parseInt(splitText(line));
		String date = splitText(getLine());
		String category = splitText(getLine());
		int amount = Integer.parseInt(splitText(getLine()));
		Company company = createCompany();
		receipt = new Receipt(receiptID, date, category, amount, company);
		taxpayer.addReceipt(receipt);
	}

	private Company createCompany() {
		String name = splitText(getLine());
		String country = splitText(getLine());
		String city = splitText(getLine());
		String address = splitText(getLine());
		int number = Integer.parseInt(splitText(getLine()));
		Company company = new Company(name, country, city, address, number);
		return company;
	}

	public Taxpayer getTaxpayer() {
		return taxpayer;
	}

}
