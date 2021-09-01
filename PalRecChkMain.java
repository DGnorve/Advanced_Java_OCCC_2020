import java.util.Scanner;


public class PalRecChkMain 
{
	public static String input = null;
	private static Scanner z = new Scanner(System.in);
	public static boolean mode = false;

	public static void main(String[] args) 
	{
		System.out.println("Please enter a word or phrase to check ");
        input = z.nextLine().toLowerCase();
        System.out.println("Check for Strict Palindromes?");
        if (z.nextLine().equalsIgnoreCase("Yes"))
        {
        	mode = true;
        }
        
        System.out.println("The word " + input + " Is a Palindrome: " + isPalindrome(input,mode));
	}
	
	public static boolean isPalindrome(String x, boolean y)
	{
		boolean StrictMode = y;
		
		if(StrictMode == false)
		{
			x = x.replaceAll("\\s", "");
			x = x.replaceAll("\\p{Punct}", "");
		}
		else
		{
			x = x.replaceAll("\\p{Punct}", "");
		}
		
		if (x.length() == 0 || x.length() == 1)
		{
			return true;
		}
		
		if(x.charAt(0) == x.charAt(x.length()-1))
		{
		return isPalindrome(x.substring(1, x.length()-1), StrictMode);
		}
		else
		return false;
	}

}
