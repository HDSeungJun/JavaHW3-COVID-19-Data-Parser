package edu.handong.csee.java.hw3;

public class Analyzer 
{
	protected String[][] data;
	protected String[] countryOrRegion;
	protected String[][] allPatients;
	private int numberOfCountires;
	private int numberOfAllPatients;
	private int numberOfPatientsOfACountry;
	private int numberOfPatientsFromASpecifiedDate;
	private int numberOfPatientsBeforeASpecifiedDate;
	private int numberOfPatientsBetweenTwoDates;
	
	public Analyzer()
	{
		numberOfCountires = 0;
		numberOfAllPatients = 0;
		numberOfPatientsOfACountry = 0;
		numberOfPatientsFromASpecifiedDate = 0;
		numberOfPatientsBeforeASpecifiedDate = 0;
		numberOfPatientsBetweenTwoDates = 0;
	}
	
	public Analyzer(String[] data)
	{
		String[] r1, r2;
		this.data = new String[data.length][];
		
		for(int i=0; i<data.length; i++)
		{
			if(data[i].contains(",\""))
			{
				r1 = data[i].split("\"");
                r2 = r1[2].split(",");

                this.data[i] = new String[r1.length+r2.length-2];

                System.arraycopy(r1, 0, this.data[i], 0, r1.length);
                System.arraycopy(r2, 1, this.data[i], r1.length-1, r2.length-1);
			}
			else if(data[i].contains("\","))
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
		
		this.getNumberOfCountries();
		this.getNumberOfAllPatients();
		
		this.numberOfCountires = 0;
		this.numberOfAllPatients = 0;
		this.numberOfPatientsOfACountry = 0;
		this.numberOfPatientsFromASpecifiedDate = 0;
		this.numberOfPatientsBeforeASpecifiedDate = 0;
		this.numberOfPatientsBetweenTwoDates = 0;
	}
	
	public int getNumberOfCountries()
	{
		
		int index;
		boolean check = true;
		int i, j;
		this.numberOfCountires = 0;
		
		this.countryOrRegion = new String[data.length-1];
		index = Util.findIndex(data[0], "Country/Region");
		
		for(i=0; i<data.length-1; i++)
		{
			this.countryOrRegion[i] = data[i+1][index];
		}
		
		for(i=0; i<this.countryOrRegion.length; i++)
		{
			for(j=0; j<i; j++)
			{	
				if(this.countryOrRegion[i].equals(this.countryOrRegion[j]))
				{
					check = false;
					break;
				}
			}
			
			if(check)
				this.numberOfCountires++;
			check = true;
		}
		
		return this.numberOfCountires;
	}
	
	public int getNumberOfAllPatients()
	{
		int index;
		int i, j;
		this.numberOfAllPatients = 0;

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
		this.numberOfPatientsOfACountry = 0;
		
		i1 = Util.findIndex(this.countryOrRegion, country);
		i2 = Util.findLastIndex(this.countryOrRegion, country);
		
		if(i1 == i2)
			this.numberOfPatientsOfACountry = Util.stringToNumber(this.allPatients[i1][this.allPatients[i1].length-1]);
		else
			for(int i=i1; i<=i2; i++)
			{
				if(country.equals(this.countryOrRegion[i]))
				{
					this.numberOfPatientsOfACountry += Util.stringToNumber(this.allPatients[i][this.allPatients[i].length-1]);
				}
			}
		
		return this.numberOfPatientsOfACountry;
	}
	
	public int getNumberOfPatientsFromASpecifiedDate(String date)
	{
		this.numberOfPatientsBeforeASpecifiedDate = this.getNumberOfPatientsBeforeASpecifiedDate(date);
		this.numberOfPatientsFromASpecifiedDate = this.numberOfAllPatients - this.numberOfPatientsBeforeASpecifiedDate;
		
		return this.numberOfPatientsFromASpecifiedDate;
	}
	
	public int getNumberOfPatientsBeforeASpecifiedDate(String date)
	{
		int index;
		int i;
		this.numberOfPatientsBeforeASpecifiedDate = 0;
		
		index = Util.findIndex(this.data[0], date);
		index -= 4;

		for(i=0; i<this.allPatients.length-1; i++)
			this.numberOfPatientsBeforeASpecifiedDate += Util.stringToNumber(this.allPatients[i][index-1]);
		
		return this.numberOfPatientsBeforeASpecifiedDate;
	}
	
	public int getNumberOfPatientsBetweenTwoDates(String date1, String date2)
	{
		int num1 = 0, num2 = 0;
		
		String[] nDate = date2.split("/");
		nDate[1] = Integer.toString((Util.stringToNumber(nDate[1])+1));
		
		String newDate = nDate[0]+"/"+nDate[1]+"/"+nDate[2];

		num1 = this.getNumberOfPatientsFromASpecifiedDate(date1);
		num2 = this.getNumberOfPatientsBeforeASpecifiedDate(newDate);
		
		
		this.numberOfPatientsBetweenTwoDates = num1 - num2;
		
		return this.numberOfPatientsBetweenTwoDates;
	}
}
