package inputparsing;

import datamanager.Taxpayer;
import java.io.FileNotFoundException;

public class TextInputParser extends InputParser {

	public TextInputParser(final String fileName) throws FileNotFoundException {
		super(fileName);
	}

	public void parseInput() {
		super.parseInput();
	}

	protected String splitText(final String textToSplit) {
		String[] splitText;
		splitText = textToSplit.split(": ");
		return splitText[1].trim();
	}

	protected boolean checkForReceipts(final String line) {
		return (line.trim().equals("Receipts:"));
	}

	protected boolean isReceiptID(final String line) {
		String[] splitLine;
		splitLine = line.split(":");
		return (splitLine[0].equals("Receipt ID"));
	}

	public Taxpayer getTaxpayer() {
		return (super.getTaxpayer());
	}

}
