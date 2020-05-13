package edu.handong.csee.java.hw3;

public class Comparer extends Analyzer
{
	private int resultOfFirstCountry;
	private int resultOfSecondCountry;
	private String result;
	private String[][] data;
	private String[] countryOrRegion;
	private String[][] allPatients;
	
	public Comparer()
	{
		this.resultOfFirstCountry = 0;
		this.resultOfSecondCountry = 0;
	}
	
	public Comparer(String[] data)
	{
		super(data);
		this.data = super.getData();
		this.countryOrRegion = super.getCountryOrRegion();
		this.allPatients = super.getAllPatients();
		
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

	public String compareTheNumberOfPatientsBetweenTwoCountriesFromSpecifiedDate(String date, String country1, String country2)
	{
		int dateIndex, country1Index1, country1Index2, country2Index1, country2Index2;
		
		dateIndex = Util.findIndex(this.data[0], date);
		dateIndex -= 4;
		
		country1Index1 = Util.findIndex(this.countryOrRegion, country1);
		country1Index2 = Util.findLastIndex(this.countryOrRegion, country1);
		
		country2Index1 = Util.findIndex(this.countryOrRegion, country2);
		country2Index2 = Util.findLastIndex(this.countryOrRegion, country2);
		
		this.resultOfFirstCountry = 0;
		this.resultOfSecondCountry = 0;
		
		if(country1Index1 == country1Index2)
			this.resultOfFirstCountry = Util.stringToNumber(this.allPatients[country1Index1][dateIndex]);
		else
			for(int i=country1Index1; i<=country1Index2; i++)
				if(country1.equals(this.countryOrRegion[i]))
					this.resultOfFirstCountry += Util.stringToNumber(this.allPatients[i][dateIndex]);
		
		if(country2Index1 == country2Index2)
			this.resultOfSecondCountry = Util.stringToNumber(this.allPatients[country2Index1][dateIndex]);
		else
			for(int i=country2Index1; i<=country2Index2; i++)
				if(country2.equals(this.countryOrRegion[i]))
					this.resultOfSecondCountry += Util.stringToNumber(this.allPatients[i][dateIndex]);
		
		if(this.resultOfFirstCountry == this.resultOfSecondCountry)
			result = "Both countries are same(" + this.resultOfFirstCountry + ')';
		else if(this.resultOfFirstCountry > this.resultOfSecondCountry)
			result = country1 +"(" + this.resultOfFirstCountry + ")" + " has more confirmed patients than " + country2 +"(" + this.resultOfSecondCountry + ")";
		else
			result = country2 +"(" + this.resultOfSecondCountry + ")" + " has more confirmed patients than " + country1 +"(" + this.resultOfFirstCountry + ")";		
		return result;
	}
	
	public String compareTheNumberOfPatientsBetweenTwoCountriesBeforeSpecifiedDate(String date, String country1, String country2)
	{
		String[] newDate = date.split("/");
		newDate[1] = Integer.toString(Util.stringToNumber(newDate[1])-1);
		String nDate = newDate[0] + "/" + newDate[1] + "/" + newDate[2];
		
		return this.compareTheNumberOfPatientsBetweenTwoCountriesFromSpecifiedDate(nDate, country1, country2);
	}
}
