package datamanager;

public class Company {
	
	private String companyName;
	private String companyCountry;
	private String companyCity;
	private String companyAddress;
	private int companyAddressNumber;
	
	public Company(
			String companyName,
			String companyCountry,
			String companyCity,
			String companyAddress,
			int companyAddressNumber)
	{		
		this.companyName = companyName;
		this.companyCountry = companyCountry;
		this.companyCity = companyCity;
		this.companyAddress = companyAddress;
		this.companyAddressNumber = companyAddressNumber;
	}
	
	public String getCompanyName(){
		return companyName;
	}
	
	public String getCompanyCountry(){
		return companyCountry;
	}
	
	public String getCompanyCity(){
		return companyCity;
	}
	
	public String getCompanyAddress(){
		return companyAddress;
	}
	
	public int getCompanyAddressNumber(){
		return companyAddressNumber;
	}
	
}
