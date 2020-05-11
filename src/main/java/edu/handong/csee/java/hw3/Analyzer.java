package edu.handong.csee.java.hw3;

public class Analyzer 
{
	private String[][] data;
	private String[] countriesOrRegion;
	private String[][] allPatients;
	private int numberOfCountires;
	private int numberOfAllPatients;
	private int numberOfPatientsOfACountry;
	private int numberOfPatientsFromASpecifiedDate;
	private int numberOfPatientsBeforeASpecifiedDate;
	private int numberOfPatientsBetweenTwoDates;
	
	public Analyzer(String[] data)
	{
		String[] r1, r2;
		this.data = new String[data.length][];
		
		for(int i=0; i<data.length; i++)
		{
			if(data[i].contains("Korea, South"))
			{
				r1 = data[i].split("\"");
                r2 = r1[2].split(",");

                this.data[i] = new String[r1.length+r2.length-2];

                System.arraycopy(r1, 0, this.data[i], 0, r1.length);
                System.arraycopy(r2, 1, this.data[i], r1.length-1, r2.length-1);
			}
			else if(data[i].contains("Bonaire, Sint Eustatius and Saba"))
			{
				r1 = data[i].split("\"");
                r2 = r1[2].split(",");
                this.data[i] = new String[r1.length+r2.length-2];
                
                System.arraycopy(r1, 1, this.data[i], 0, r1.length-1);
                System.arraycopy(r2, 1, this.data[i], r1.length-2, r2.length-1);
			}
			else
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
		int count;
		int i, j;
		
		index = Util.findIndex(data[0], "Country/Region");
		
		for(i=1, count = 0; i<data.length-1; i++)
			if(!data[i][index].equals(data[i+1][index]))
				count++;
		this.numberOfCountires = count;
		
		this.countriesOrRegion = new String[data.length-1];
		
		for(i=0; i<data.length-1; i++)
			this.countriesOrRegion[i] =  data[i+1][index];	
	
		return this.numberOfCountires;
	}
	
	public int getNumberOfAllPatients()
	{
		int index;
		int i, j;
		
		index = Util.findIndex(data[0], "1/22/20");
		this.allPatients = new String[data.length][data[0].length-index];
		
		for(i=0; i<data.length-1; i++)
			for(j=index; j<data[0].length; j++)
				this.allPatients[i][j-index] = data[i+1][j];
		
		for(i=0; i<this.allPatients.length-1; i++)
			this.numberOfAllPatients += Util.stringToNumber(this.allPatients[i][this.allPatients[i].length-1]);
		
		return this.numberOfAllPatients;
	}
	
	public int getNumberOfPatientsOfACountry(String country)
	{
		int i1, i2;
		
		i1 = Util.findIndex(this.countriesOrRegion, country);
		i2 = Util.findLastIndex(this.countriesOrRegion, country);
		
		if(i1 == i2)
			this.numberOfPatientsOfACountry = Util.stringToNumber(this.allPatients[i1][this.allPatients[i1].length-1]);
		else
			for(int i=i1; i<=i2; i++)
				this.numberOfPatientsOfACountry += Util.stringToNumber(this.allPatients[i][this.allPatients[i].length-1]);
		
		return this.numberOfPatientsOfACountry;
	}
	
	public int getNumberOfPatientsFromASpecifiedDate(String date)
	{
		int index;
		int i;
		
		index = Util.findIndex(this.data[0], date);
		index -= 4;
		
		for(i=0; i<this.allPatients.length-1; i++)
			this.numberOfPatientsFromASpecifiedDate += Util.stringToNumber(this.allPatients[i][index]);
		
		return this.numberOfPatientsFromASpecifiedDate;
	}
	
	public int getNumberOfPatientsBeforeASpecifiedDate(String date)
	{
		int index;
		int i;
		
		index = Util.findIndex(this.data[0], date);
		index -= 4;
		
		for(i=0; i<this.allPatients.length-1; i++)
			this.numberOfPatientsBeforeASpecifiedDate += Util.stringToNumber(this.allPatients[i][index-1]);
		
		return this.numberOfPatientsBeforeASpecifiedDate;
	}
	
	public int getNumberOfPatientsBetweenTwoDates(String date1, String date2)
	{
		int indexFrom, indexTo;
		int num1 = 0, num2 = 0;
		int i;
		
		indexFrom = Util.findIndex(this.data[0], date1);
		indexTo = Util.findIndex(data[0], date2);
		indexFrom -= 4;
		indexTo -= 4;
		
		for(i=0; i<data.length-1; i++)
			num1 += Util.stringToNumber(this.allPatients[i][indexFrom-1]);
		
		for(i=0; i<data.length-1; i++)
			num2 += Util.stringToNumber(this.allPatients[i][indexTo]);
		
		this.numberOfPatientsBetweenTwoDates = num2 - num1;
		
		return this.numberOfPatientsBetweenTwoDates;
	}
}
