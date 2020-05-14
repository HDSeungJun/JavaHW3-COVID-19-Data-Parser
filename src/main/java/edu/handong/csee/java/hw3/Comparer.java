package edu.handong.csee.java.hw3;

public class Comparer extends Analyzer
{
	private int resultOfFirstCountry;
	private int resultOfSecondCountry;
	private String result;
	
	public Comparer()
	{
		super();
		this.resultOfFirstCountry = 0;
		this.resultOfSecondCountry = 0;
	}
	
	public Comparer(String[] data)
	{
		super(data);
		this.resultOfFirstCountry = 0;
		this.resultOfSecondCountry = 0;	
	}
	
	public String compareTheNumberOfPatientsBetweenTwoCountries(String country1, String country2)
	{	
		this.resultOfFirstCountry = super.getNumberOfPatientsOfACountry(country1);
		this.resultOfSecondCountry = super.getNumberOfPatientsOfACountry(country2);

		return this.setResult(country1, country2);
	}

	public String compareTheNumberOfPatientsBetweenTwoCountriesFromASpecifiedDate(String date, String country1, String country2)
	{
		int temp1, temp2;
		
		this.compareTheNumberOfPatientsBetweenTwoCountries(country1, country2);
		temp1 = this.resultOfFirstCountry;
		temp2 = this.resultOfSecondCountry;
		
		this.compareTheNumberOfPatientsBetweenTwoCountriesBeforeASpecifiedDate(date, country1, country2);
		
		this.resultOfFirstCountry = temp1 - this.resultOfFirstCountry;
		this.resultOfSecondCountry = temp2 - this.resultOfSecondCountry;

		return this.setResult(country1, country2);
	}
	
	public String compareTheNumberOfPatientsBetweenTwoCountriesBeforeASpecifiedDate(String date, String country1, String country2)
	{
		int dateIndex, country1Index1, country1Index2, country2Index1, country2Index2;
		this.resultOfFirstCountry = 0;
		this.resultOfSecondCountry = 0;
		
		dateIndex = Util.findIndex(super.data[0], date);
		dateIndex -= 4;
		dateIndex--;
		
		country1Index1 = Util.findIndex(super.countryOrRegion, country1);
		country1Index2 = Util.findLastIndex(super.countryOrRegion, country1);
		
		country2Index1 = Util.findIndex(super.countryOrRegion, country2);
		country2Index2 = Util.findLastIndex(super.countryOrRegion, country2);
		
		if(country1Index1 == country1Index2)
			this.resultOfFirstCountry = Util.stringToNumber(super.allPatients[country1Index1][dateIndex]);
		else
			for(int i=country1Index1; i<=country1Index2; i++)
				if(country1.equals(super.countryOrRegion[i]))
					this.resultOfFirstCountry += Util.stringToNumber(super.allPatients[i][dateIndex]);
		
		if(country2Index1 == country2Index2)
			this.resultOfSecondCountry = Util.stringToNumber(super.allPatients[country2Index1][dateIndex]);
		else
			for(int i=country2Index1; i<=country2Index2; i++)
				if(country2.equals(super.countryOrRegion[i]))
					this.resultOfSecondCountry += Util.stringToNumber(super.allPatients[i][dateIndex]);
		
		return this.setResult(country1, country2);
	}
	
	private String setResult(String country1, String country2)
	{
		if(this.resultOfFirstCountry == this.resultOfSecondCountry)
			result = "Both countries are same(" + this.resultOfFirstCountry + ')';
		else if(this.resultOfFirstCountry > this.resultOfSecondCountry)
			result = country1 +"(" + this.resultOfFirstCountry + ")" + " has more confirmed patients than " + country2 +"(" + this.resultOfSecondCountry + ")";
		else
			result = country2 +"(" + this.resultOfSecondCountry + ")" + " has more confirmed patients than " + country1 +"(" + this.resultOfFirstCountry + ")";		
		
		return result;
	}
}
