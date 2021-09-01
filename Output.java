//David Norvell
//Advanced Java OCCC Fall 2020
//Output.java


import java.util.ArrayList;

public class Output //Only used for debug purposes
{
	public void printList(ArrayList<String> x)
	{
		if(!x.isEmpty())
		{
		  for(int i = 0; i < x.size(); i++)
		  {
			System.out.println(x.get(i));
		  }
		  
		  System.out.print(System.lineSeparator());
		  System.out.println("The total number of passwords is: " + x.size());
		}
		else
		System.out.println("List Empty!");
	}

}
