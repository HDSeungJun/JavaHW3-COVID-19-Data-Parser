package edu.handong.csee.java.hw3;

public class Analyzer 
{
	private String[][] data;
	private String[] countryOrRegion;
	private String[][] allPatients;
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
		
		this.getCountryOrRegion();
		this.getAllPatients();
		
		this.numberOfCountires = 0;
		this.numberOfAllPatients = 0;
		this.numberOfPatientsOfACountry = 0;
		this.numberOfPatientsFromASpecifiedDate = 0;
		this.numberOfPatientsBeforeASpecifiedDate = 0;
		this.numberOfPatientsBetweenTwoDates = 0;
	}
	
	public String[][] getData()
	{
		return this.data;
	}
	
	public String[] getCountryOrRegion()
	{
		int index;
		int i;
		
		this.countryOrRegion = new String[data.length-1];
		index = Util.findIndex(data[0], "Country/Region");
		
		for(i=0; i<data.length-1; i++)
		{
			this.countryOrRegion[i] = data[i+1][index];
		}
		
		return this.countryOrRegion;
	}
	
	public String[][] getAllPatients()
	{
		int index;
		int i, j;
		
		index = Util.findIndex(data[0], "1/22/20");
		this.allPatients = new String[data.length][data[0].length-index];
		
		for(i=0; i<data.length-1; i++)
			for(j=index; j<data[0].length; j++)
				this.allPatients[i][j-index] = data[i+1][j];
		
		return this.allPatients;
	}
	
	public int getNumberOfCountries()
	{
		boolean check = true;
		int i, j;
		
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
		int i;
		
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
					//System.out.println(this.countryOrRegion[i] + " " + this.numberOfPatientsOfACountry);
				}
			}
		return this.numberOfPatientsOfACountry;
	}
	
	public int getNumberOfPatientsFromASpecifiedDate(String date)
	{
		int index;
		int i;
		this.numberOfPatientsFromASpecifiedDate = 0;
		
		index = Util.findIndex(this.data[0], date);
		index -= 4;

		for(i=0; i<this.allPatients.length-1; i++)
			this.numberOfPatientsFromASpecifiedDate += Util.stringToNumber(this.allPatients[i][index]);
		
		return this.numberOfPatientsFromASpecifiedDate;
	}
	
	public int getNumberOfPatientsBeforeASpecifiedDate(String date)
	{
		String[] newDate = date.split("/");
		newDate[1] = Integer.toString(Util.stringToNumber(newDate[1])-1);
		String nDate = newDate[0] + "/" + newDate[1] + "/" + newDate[2];
	
		this.numberOfPatientsBeforeASpecifiedDate = this.getNumberOfPatientsFromASpecifiedDate(nDate);
		
		return this.numberOfPatientsBeforeASpecifiedDate;
	}
	
	public int getNumberOfPatientsBetweenTwoDates(String date1, String date2)
	{
		int num1 = 0, num2 = 0;

		num1 = this.getNumberOfPatientsBeforeASpecifiedDate(date1);
		num2 = this.getNumberOfPatientsFromASpecifiedDate(date2);
		
		this.numberOfPatientsBetweenTwoDates = num2 - num1;
		
		return this.numberOfPatientsBetweenTwoDates;
	}
}
