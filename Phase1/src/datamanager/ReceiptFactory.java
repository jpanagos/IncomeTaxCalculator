package datamanager;

public abstract class ReceiptFactory {

	public static Receipt createReceipt (
			int receiptID,
			String receiptDate,
			String receiptCategory,
			int receiptAmount,
			Company receiptCompany)
	{
		return new Receipt(
				receiptID,
				receiptDate,
				receiptCategory,
				receiptAmount,
				receiptCompany);
	}
	
}
