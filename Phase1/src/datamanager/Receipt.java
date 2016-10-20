package datamanager;

public class Receipt {
	
	private int receiptID;
	private String receiptDate;
	private String receiptCategory;
	private int receiptAmount;
	private Company receiptCompany;
	
	public Receipt(
			int receiptID,
			String receiptDate,
			String receiptCategory,
			int receiptAmount,
			Company receiptCompany)
	{
		this.receiptID = receiptID;
		this.receiptDate = receiptDate;
		this.receiptCategory = receiptCategory;
		this.receiptAmount = receiptAmount;
		this.receiptCompany = receiptCompany;
	}
	
	public int getReceiptID(){
		return receiptID;
	}
	
	public String getReceiptDate(){
		return receiptDate;
	}
	
	public String getReceiptCategory(){
		return receiptCategory;
	}
	
	public int getReceiptAmount(){
		return receiptAmount;
	}
	
	public Company getReceiptCompany(){
		return receiptCompany;
	}
	
	public void setAmount(int newAmount){
		receiptAmount = newAmount;
	}
	
}
