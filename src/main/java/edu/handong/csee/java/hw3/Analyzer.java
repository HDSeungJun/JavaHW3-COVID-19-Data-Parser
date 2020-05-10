package edu.handong.csee.java.hw3;

public class Analyzer 
{
	private String[][] data;
	private String[] countriesOrRegion;
	private String[] ProvinceOrState;
	private String[][] numberOfPatients;
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
		int count = 0;
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
				count++;
		}
		
		return count;
	}
	
	public int getNumberOfAllPatients()
	{
		int count = 0;
		int index;
		int i, j;
		
		index = Util.findIndex(data[0], "1/22/20");
		
		this.numberOfPatients = new String[data.length][data[0].length-(data[0].length-index)];
		
		for(i=0; i<data.length; i++)
		{
			for(j=index; j<data[0].length; j++)
			{
				this.numberOfPatients[i][j-index] = data[i][j];
			}
		}
		
		for(i=0; i<this.numberOfPatients.length; i++)
			for(j=0; j<this.numberOfPatients[0].length; j++)
				count += Util.stringToNumber(this.numberOfPatients[i][j]);
		
		return count;
	}
	
	public int getNumberOfPatientsOfACountry(String country)
	{
		
	}
	
	public int getNumberOfPatientsFromASpecifiedDate(String date)
	{
		
	}
	
	public int getNumberOfPatientsBeforeASpecifiedDate(String date)
	{
		
	}
	
	public int getNumberOfPatientsBetweenTwoDates(String date1, String date2)
	{
		
	}
}
