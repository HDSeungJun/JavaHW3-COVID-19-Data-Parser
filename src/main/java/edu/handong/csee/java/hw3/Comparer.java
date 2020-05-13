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
		
		if(this.resultOfFirstCountry == this.resultOfSecondCountry)
			result = "Both countries are same (" + this.resultOfFirstCountry + ')';
		else if(this.resultOfFirstCountry > this.resultOfSecondCountry)
			result = country1 +"(" + this.resultOfFirstCountry + ")" + " has more confirmed patients than " + country2 +"(" + this.resultOfSecondCountry + ")";
		else
			result = country2 +"(" + this.resultOfSecondCountry + ")" + " has more confirmed patients than " + country1 +"(" + this.resultOfFirstCountry + ")";		
		
		return result;
	}

	public String compareTheNumberOfPatientsBetweenTwoCountriesFromASpecifiedDate(String date, String country1, String country2)
	{
		int dateIndex, country1Index1, country1Index2, country2Index1, country2Index2;
		this.resultOfFirstCountry = 0;
		this.resultOfSecondCountry = 0;
		
		dateIndex = Util.findIndex(super.data[0], date);
		dateIndex -= 4;
		
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
		
		if(this.resultOfFirstCountry == this.resultOfSecondCountry)
			result = "Both countries are same(" + this.resultOfFirstCountry + ')';
		else if(this.resultOfFirstCountry > this.resultOfSecondCountry)
			result = country1 +"(" + this.resultOfFirstCountry + ")" + " has more confirmed patients than " + country2 +"(" + this.resultOfSecondCountry + ")";
		else
			result = country2 +"(" + this.resultOfSecondCountry + ")" + " has more confirmed patients than " + country1 +"(" + this.resultOfFirstCountry + ")";		
		return result;
	}
	
	public String compareTheNumberOfPatientsBetweenTwoCountriesBeforeASpecifiedDate(String date, String country1, String country2)
	{
		String[] newDate = date.split("/");
		newDate[1] = Integer.toString(Util.stringToNumber(newDate[1])-1);
		String nDate = newDate[0] + "/" + newDate[1] + "/" + newDate[2];
		
		return this.compareTheNumberOfPatientsBetweenTwoCountriesFromASpecifiedDate(nDate, country1, country2);
	}
}
