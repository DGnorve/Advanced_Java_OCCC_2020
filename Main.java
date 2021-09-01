//David Norvell
//Advanced Java OCCC Fall 2020
//Main.class

import java.util.ArrayList;

public class Main 
{
	public static PasswordCheckerIO a = new PasswordCheckerIO(); //Our three helper objects so we can call their class functions
	public static Output b = new Output(); 
	public static PasswordChecker c = new PasswordChecker();
	 
    public static void main(String[] Args)
    {
	
	 a.ingestFile();
	 ArrayList<String> temp = a.GetPassCans();
	
	 b.printList(c.CheckList(temp));
	 
	 
    }
}
