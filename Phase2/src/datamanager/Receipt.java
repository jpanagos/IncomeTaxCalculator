package datamanager;

import outputmanager.OutputWriter;

public class Receipt {

	private int receiptID;
	private String receiptDate;
	private String receiptCategory;
	private int receiptAmount;
	private Company receiptCompany;
	private OutputWriter outputWriter = null;

	public Receipt(
			int receiptID,
			String receiptDate,
			String receiptCategory,
			int receiptAmount,
			Company receiptCompany) {
		this.receiptID = receiptID;
		this.receiptDate = receiptDate;
		this.receiptCategory = receiptCategory;
		this.receiptAmount = receiptAmount;
		this.receiptCompany = receiptCompany;
	}

	public void setOutputWriter(OutputWriter outputWriter) {
		this.outputWriter = outputWriter;
	}

	public void writeReceiptDetails() {
		outputWriter.writeEmpty();
		outputWriter.write("Receipt ID", Integer.toString(receiptID));
		outputWriter.write("Date", receiptDate);
		outputWriter.write("Kind", receiptCategory);
		outputWriter.write("Amount", Integer.toString(receiptAmount));
		writeCompanyDetails();
	}

	private void writeCompanyDetails() {
		outputWriter.write("Company", receiptCompany.getCompanyName());
		outputWriter.write("Country", receiptCompany.getCompanyCountry());
		outputWriter.write("City", receiptCompany.getCompanyCity());
		outputWriter.write("Street", receiptCompany.getCompanyAddress());
		outputWriter.write("Number",
				Integer.toString(receiptCompany.getCompanyAddressNumber()));
		outputWriter.writeEmpty();
	}

	public int getReceiptID() {
		return receiptID;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public String getReceiptCategory() {
		return receiptCategory;
	}

	public int getReceiptAmount() {
		return receiptAmount;
	}

	public Company getReceiptCompany() {
		return receiptCompany;
	}

	public void setAmount(final int newAmount) {
		receiptAmount = newAmount;
	}

}
