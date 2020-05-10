package edu.handong.csee.java.hw3;

public class Analyzer 
{
	private String[][] data;
	private String[] countriesOrRegion;
	private String[] ProvinceOrState;
	private String[][] allPatients;
	private int numberOfCountires;
	private int numberOfAllPatients;
	private int numberOfPatientsOfACountry;
	private int numberOfPatientsFromASpecifiedDate;
	private int numberOfPatientsBeforeASpecifiedDate;
	private int numberOfPatientsBetweenTwoDates;
	
	public Analyzer(String[] data)
	{
		this.data = new String[data.length][];
		
		for(int i=0; i<data.length; i++)
		{
			this.data[i] = data[i].split(",");
		}
		
		numberOfCountires = 0;
		numberOfAllPatients = 0;
		numberOfPatientsOfACountry = 0;
		numberOfPatientsFromASpecifiedDate = 0;
		numberOfPatientsBeforeASpecifiedDate = 0;
		numberOfPatientsBetweenTwoDates = 0;
	}
	
	public int getNumberOfCountries()
	{
		int index;
		int i;
		
		index = Util.findIndex(data[0], "Country/Region");
		
		for(i=0; i<data.length; i++)
		{
			this.countriesOrRegion[i] =  data[i+1][index];
		}
		
		for(i=0; i<data.length-1; i++)
		{
			if(this.countriesOrRegion[i] != this.countriesOrRegion[i+1])
				this.numberOfCountires++;
		}
		
		return this.numberOfCountires;
	}
	
	public int getNumberOfAllPatients()
	{
		int index;
		int i, j;
		
		index = Util.findIndex(data[0], "1/22/20");
		
		this.allPatients = new String[data.length][data[0].length-(data[0].length-index)];
		
		for(i=0; i<data.length; i++)
		{
			for(j=index; j<data[0].length; j++)
			{
				this.allPatients[i][j-index] = data[i][j];
			}
		}
		
		for(i=0; i<this.allPatients.length; i++)
			for(j=0; j<this.allPatients[0].length; j++)
				this.numberOfAllPatients += Util.stringToNumber(this.allPatients[i][j]);
		
		return this.numberOfAllPatients;
	}
	
	public int getNumberOfPatientsOfACountry(String country)
	{
		int index;
		int i;
		
		index = Util.findIndex(this.countriesOrRegion, country);
		
		for(i=0; i<this.allPatients[0].length; i++)
		{
			this.numberOfPatientsOfACountry += Util.stringToNumber(this.allPatients[index][i]);
		}
		
		return this.numberOfPatientsOfACountry;
	}
	
	public int getNumberOfPatientsFromASpecifiedDate(String date)
	{
		int index;
		int i, j;
		
		index = Util.findIndex(this.data[0], date);
		
		for(i=0; i<this.allPatients.length; i++)
		{
			for(j=0; j<=index; j++)
				this.numberOfPatientsFromASpecifiedDate += Util.stringToNumber(this.allPatients[i][j]);
		}
		
		return this.numberOfPatientsFromASpecifiedDate;
	}
	
	public int getNumberOfPatientsBeforeASpecifiedDate(String date)
	{
		
	}
	
	public int getNumberOfPatientsBetweenTwoDates(String date1, String date2)
	{
		
	}
}
