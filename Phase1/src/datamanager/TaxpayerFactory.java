package datamanager;

public abstract class TaxpayerFactory {
	
	public static Taxpayer createTaxpayer(String fullName, int AFM, String familyStatus, int income){
		
		if (familyStatus.equalsIgnoreCase("Single")){
			return  new SingleTaxpayer(fullName, AFM, familyStatus, income);
		}else if (familyStatus.equalsIgnoreCase("Married Filing Jointly")){
			return new MarriedFilingJointlyTaxpayer(fullName, AFM, familyStatus, income);
		}else if (familyStatus.equalsIgnoreCase("Married Filing Separately")){
			return new MarriedFilingSeparatelyTaxpayer(fullName, AFM, familyStatus, income);
		}else if (familyStatus.equalsIgnoreCase("Head Of Household")){
			return new HeadOfHouseholdTaxpayer(fullName, AFM, familyStatus, income);
		}else{
			return null;
		}
	}
	
}
