package edu.handong.csee.java.hw3;

public class Util {
	public static int stringToNumber(String str)
	{
		return Integer.parseInt(str);
	}
	
	public static int findIndex(String[] str, String search)
	{
		int index=0;
		
		for(int i=0; i<str.length; i++)
		{
			if(str[i].equals(search))
			{
				index = i;
				break;
			}
		}
		
		return index;
	}
	public static int findLastIndex(String[] str, String search)
	{
		int index = 0;
		
		for(int i=str.length-1; i>=0; i--)
		{
			if(str[i].equals(search))
			{
				index = i;
				break;
			}
		}
		
		return index;
	}
}
