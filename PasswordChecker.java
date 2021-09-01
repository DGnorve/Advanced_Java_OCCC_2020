//David Norvell
//Advanced Java OCCC Fall 2020
//PasswordChecker.class

import java.lang.String;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Character;  

public class PasswordChecker 
{
	public boolean isValid(String x)
	{
		if (x.length() <12) //Easiest check; we see if the entire string is less than our minimum and if it is; return false.
		{
			return false;
		}
		else //if we don't throw it out there, then we have to actually check for the nitty gritty elements.
		{
		char c;
		int Daccumulator =0;  
		int UC =0;
		int LC =0;
		int Special =0;
		int CC = 0;
		
		if (Daccumulator != 0)
			Daccumulator =0;
		if(UC != 0)
			UC =0;
		if(LC != 0)
			LC=0;
		if(Special != 0)
			Special =0;
		if(CC !=0)
			CC=0;
		
		
		for(int i = 0; i < x.length();i++)
		{
			c = x.charAt(i);
			if (Character.isWhitespace(c))
			{
				return false;
			}
		    if (Character.isDigit(c))
			{
				Daccumulator++;
				CC++;
				
			}
		    if (Character.isUpperCase(c))
			{
				++UC;
				CC++;
			}
		    if(Character.isLowerCase(c))
			{
				++LC;
				CC++;
			}
		    if (c>= 33&&c<=46 || c==64)
			{
	                Special++;
	                CC++;
	        }
		    
		}
		
		if(Daccumulator < 2)
		{
			
			return false;
		}
		if (UC <1 || LC <1 )
		{
		
			return false;
		}
		if (Special < 1)
		{
		
			return false;
		}
		if(CC < 12)
		{
			
			return false;
		}
		
		}
		
		
		
		return true;
		
	}
	
	public ArrayList<String> CheckList(ArrayList<String> x)
	{
		final Iterator<String> passchk = x.iterator();
		if (!x.isEmpty())
		{
		while (passchk.hasNext())
		{
			final String Password = passchk.next();
			if(isValid(Password) == false)
			{
				passchk.remove();
			}
		}
		}
	   else
	    System.out.print("List is empty!");
		return x;
		
	}
	

}
