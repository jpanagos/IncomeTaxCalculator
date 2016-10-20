package datamanager;

public abstract class TaxpayerFactory {

	private static int[] singleLimits = {24680, 81080, 90000, 152540 };
	private static double[] singleFlat =
		{0, 1320.38, 5296.58, 5996.80, 10906.19 };
	private static double[] singleMultipliers =
		{(5.35 / 100), (7.05 / 100), (7.85 / 100), (7.85 / 100), (9.85 / 100) };
	private static int[] marriedJointlyLimits =
		{36080, 90000, 143350, 254240 };
	private static double[] marriedJointlyFlat =
		{0, 1930.28, 5731.64, 9492.82, 18197.69 };
	private static double[] marriedJointlyMultipliers =
		{(5.35 / 100), (7.05 / 100), (7.05 / 100), (7.85 / 100), (9.85 / 100) };
	private static int[] marriedSeparatelyLimits =
		{18040, 71680, 90000, 127120 };
	private static double[] marriedSeparatelyFlat =
		{0, 965.14, 4746.76, 6184.88, 9098.80 };
	private static double[] marriedSeparatelyMultipliers = singleMultipliers;
	private static int[] headOfHouseholdLimits =
		{30390, 90000, 122110, 203390 };
	private static double[] headOfHouseholdFlat =
		{0, 1625.87, 5828.38, 8092.13, 14472.61 };
	private static double[] headOfHouseholdMultipliers =
			marriedJointlyMultipliers;

	public static Taxpayer createTaxpayer(String fullName,
			int AFM, String familyStatus, int income) {
		if (familyStatus.equalsIgnoreCase("Single")) {
			return new Taxpayer(fullName, AFM, familyStatus, income,
			singleLimits, singleFlat, singleMultipliers);
		} else if (familyStatus.equalsIgnoreCase("Married Filing Jointly")) {
			return new Taxpayer(fullName, AFM, familyStatus, income,
			marriedJointlyLimits, marriedJointlyFlat,marriedJointlyMultipliers);
		} else if (familyStatus.equalsIgnoreCase("Married Filing Separately")) {
			return new Taxpayer(fullName, AFM, familyStatus, income,
			marriedSeparatelyLimits, marriedSeparatelyFlat,
			marriedSeparatelyMultipliers);
		} else if (familyStatus.equalsIgnoreCase("Head Of Household")) {
			return new Taxpayer(fullName, AFM, familyStatus, income,
			headOfHouseholdLimits, headOfHouseholdFlat,
			headOfHouseholdMultipliers);
		} else {
			return null;
		}
	}

}
