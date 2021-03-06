package inputparsing;

import java.io.FileNotFoundException;

import datamanager.Taxpayer;

public class XMLInputParser extends InputParser {

	public XMLInputParser(final String fileName) throws FileNotFoundException {
		super(fileName);
	}

	public void parseInput() {
		super.parseInput();
	}

	protected String splitText(final String textToSplit) {
		String[] splitText;
		splitText = textToSplit.split(" ");
		return getXMLSplitText(splitText);
	}

	private String getXMLSplitText(final String[] splitText) {
		String text = "";
		for (int i = 1; i < (splitText.length - 1); i++) {
			text += (splitText[i] + " ");
		}
		return text.trim();
	}

	protected boolean checkForReceipts(final String line) {
		return (line.trim().equals("<Receipts>"));
	}

	protected boolean isReceiptID(final String line) {
		String[] splitLine;
		splitLine = line.split(" ");
		return (splitLine[0].equals("<ReceiptID>"));
	}

	public Taxpayer getTaxpayer() {
		return (super.getTaxpayer());
	}

}
