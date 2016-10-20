package datamanager;

public abstract class CompanyFactory {

	public static Company createCompany(
			String companyName, 
			String companyCountry,
			String companyCity,
			String companyAddress,
			int companyAddressNumber)
	{
		return new Company(
				companyName,
				companyCountry,
				companyCity,
				companyAddress,
				companyAddressNumber);
	}
	
}
