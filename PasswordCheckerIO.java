import java.util.*;  
//David Norvell
//Advanced Java OCCC Fall 2020
//PasswordCheckerIO.class

import java.io.File;
import java.io.IOException;

public class PasswordCheckerIO 
{
	private ArrayList<String> PassCans = new ArrayList<String>(20);  //Our helper object for ingesting the file
	
	//Getter
	public ArrayList<String> GetPassCans()
	{
		return this.PassCans;
	}
	//Setter
	public void SetPassCans(ArrayList<String> x)
	{
		this.PassCans = x;
	}
	
	public void ingestFile()
	{
		try
		{
			File passwords = new File("Passwords.txt");
			Scanner IO = new Scanner(passwords);
			if(passwords.exists())
			{
				ArrayList<String> Temp = GetPassCans();
				
				while(IO.hasNext())
				{
					Temp.add(IO.nextLine());
				}
				SetPassCans(Temp);
				IO.close();
			}
		}
		catch (IOException e)
		{
			System.exit(0);
		}
	}

}
