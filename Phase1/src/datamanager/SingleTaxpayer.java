package datamanager;

public class SingleTaxpayer extends Taxpayer {
	
	public SingleTaxpayer(String fullName, int AFM, String familyStatus, int income){
		super(fullName, AFM, familyStatus, income);
	}

	protected double calculateBasicTax(){
				
		int income = super.getIncome();
		
		if (income < 24680){
			return (5.35/100) * income;
		}else if (income < 81080){
			return 1320.38 + (7.05/100) * (income - 24680);
		}else if (income < 90000){
			return 5296.58 + (7.85/100) * (income - 81080);
		}else if (income < 152540){
			return 5996.80 + (7.85/100) * (income - 90000);
		}else{
			return 10906.19 + (9.85/100) * (income - 152540);
		}
	}

}
