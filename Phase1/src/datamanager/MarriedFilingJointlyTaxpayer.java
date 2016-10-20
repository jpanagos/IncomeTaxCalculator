package datamanager;

public class MarriedFilingJointlyTaxpayer extends Taxpayer {
	
	public MarriedFilingJointlyTaxpayer(String fullName, int AFM, String familyStatus, int income){
		super(fullName, AFM, familyStatus, income);
	}

	protected double calculateBasicTax(){
		
		int income = super.getIncome();
		
		if (income < 36080){
			return (5.35/100) * income;
		}else if (income < 90000){
			return 1930.28 + (7.05/100) * (income - 36080);
		}else if (income < 143350){
			return 5731.64 + (7.05/100) * (income - 90000);
		}else if (income < 254240){
			return 9492.82 + (7.85/100) * (income - 143350);
		}else{
			return 18197.69 + (9.85/100) * (income - 254240);
		}
	}
	
}
