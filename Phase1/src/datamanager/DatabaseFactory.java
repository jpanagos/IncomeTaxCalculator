package datamanager;

public abstract class DatabaseFactory {
	
	public static TaxpayerDatabase createDatabase(){
		return new TaxpayerDatabase();
	}
	
}
