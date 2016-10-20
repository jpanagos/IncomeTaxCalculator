package datamanager;

public class MarriedFilingSeparatelyTaxpayer extends Taxpayer {
	
	public MarriedFilingSeparatelyTaxpayer(String fullName, int AFM, String familyStatus, int income){
		super(fullName, AFM, familyStatus, income);
	}

	protected double calculateBasicTax(){
		
		int income = super.getIncome();
		
		if (income < 18040){
			return (5.35/100) * income;
		}else if (income < 71680){
			return 965.14 + (7.05/100) * (income - 18040);
		}else if (income < 90000){
			return 4746.76 + (7.85/100) * (income - 71680);
		}else if (income < 127120){
			return 6184.88 + (7.85/100) * (income - 90000);
		}else{
			return 9098.80 + (9.85/100) * (income - 127120);
		}
	}
	
}
