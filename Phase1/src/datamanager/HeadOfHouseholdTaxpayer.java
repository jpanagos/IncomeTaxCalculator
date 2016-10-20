package datamanager;

public class HeadOfHouseholdTaxpayer extends Taxpayer {
	
	public HeadOfHouseholdTaxpayer(String fullName, int AFM, String familyStatus, int income){
		super(fullName, AFM, familyStatus, income);
	}

	protected double calculateBasicTax(){
		
		int income = super.getIncome();
		
		if (income < 30390){
			return (5.35/100) * income;
		}else if (income < 90000){
			return 1625.87 + (7.05/100) * (income - 30390);
		}else if (income < 122110){
			return 5828.38 + (7.05/100) * (income - 90000);
		}else if (income < 203390){
			return 8092.13 + (7.85/100) * (income - 122110);
		}else{
			return 14472.61 + (9.85/100) * (income - 203390);
		}
	}
	
}
