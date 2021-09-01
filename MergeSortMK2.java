import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MergeSortMK2
{
	private static Scanner z = new Scanner(System.in);
	
	
	public static String T1N = "";
	public static String T2N = "";

	public static String FileName = "";
	public static String reply = "";
	
	public static int[] TapeFinal;
	public static int[] tempMArray; 
	public static int[] TF;
	public static int[] Semi_final;
	public static int[] subarray_Right;
	public static int[] subarray_Left;
	
	public static int[] Tape1;
	public static int[] Tape2;

    public static int Tape1s = 0;
	public static int Tape2s = 0;

	public String answer = null;
	
	
	public static void main(String[] args) 
	{
		if (args.length == 0)
		{

			
			System.out.println("Please specify the name of the Tape to be loaded ");
			T1N = z.nextLine();
			
			System.out.println("Please specify the name of the second Tape to be loaded ");
			T2N = z.nextLine();
			
			System.out.println("Would you like to dump the output into a file after sorting? ");
			reply = z.nextLine();
			if (reply.equalsIgnoreCase("Yes"))
			{
				System.out.println("Please enter the name of the file; ending in .txt");
				FileName = z.nextLine();
				
				InsertCart();
				TapeFinal = new int[Tape1.length + Tape2.length];
			    TapeSplice(Tape1, Tape2);
			    sort(TapeFinal);
			    dumpArray(TapeFinal);
		
			}
			else
			{
			
			InsertCart();
			TapeFinal = new int[Tape1.length + Tape2.length];
		    TapeSplice(Tape1, Tape2);
		    sort(TapeFinal);
		    for (int i = 0; i < TapeFinal.length; i++)
		    {
		    	System.out.println(TapeFinal[i]);
		    }
	
		   
	 
		}
		}
		if(args.length == 2)
		{
			args[0] = T1N;
			args[1] = T2N;
			
			InsertCart();
			TapeFinal = new int[Tape1.length + Tape2.length];
		    TapeSplice(Tape1, Tape2);
		    sort(TapeFinal);
		    for (int i = 0; i < TapeFinal.length; i++)
		    {
		    	System.out.println(TapeFinal[i]);
		    }
		}
		if (args.length == 3)
		{
			args[0] = T1N;
			args[1] = T2N;
			args[2] = FileName;
			
			InsertCart();
			TapeFinal = new int[Tape1.length + Tape2.length];
		    TapeSplice(Tape1, Tape2);
		    sort(TapeFinal);
		    dumpArray(TapeFinal);
					
		}
		
		
	}
	
	
	public static void TapeSplice(int[] t, int[] t2) 
	{
		int i = 0;
		int x = 0;
		int j = 0;
		while (x < t.length)
		{
			 TapeFinal[i++] = t[x++];
			
		}
		while(j < t2.length)
		TapeFinal[i++] = t2[j++];
		
	}

	
	
	public static void sort(int[] x) 
	{
	    int N_E = x.length;

	     TF = new int[N_E]; 
	     Semi_final = new int[N_E + 1]; 

	   
	    int RC = 0;
	    TF[0] = 0;
	    for (int i = 1; i <= N_E; i++) 
	    {
	      if (i == N_E || x[i] < x[i - 1]) 
	      {
	        Semi_final[++RC] = i;
	      }
	    }

	   
	    int[] f = x;
	    int[] to = TF;

	    while (RC > 1) 
	    {
	      int NC = 0;

	     
	      for (int i = 0; i < RC - 1; i += 2) 
	      {
	        merge(f, to, Semi_final[i], Semi_final[i + 1], Semi_final[i + 2]);
	        Semi_final[NC++] = Semi_final[i];
	      }

	      
	      if (RC % 2 == 1) 
	      {
	        int LS = Semi_final[RC - 1];
	        System.arraycopy(f, LS, to, LS,
	              N_E - LS);
	        Semi_final[NC++] = LS;
	      }

	      
	      Semi_final[NC] = N_E;
	      RC = NC;

	    
	      int[] h = f;
	      f = to;
	      to = h;
	    }

	   
	    if (f != x) 
	    {
	      System.arraycopy(f, 0, x, 0, N_E);
	    }
	  }

	  private static void merge(int[] x, int[] y, int sL,
	                     int sR, int eR) 
	  {
	    int lp = sL;
	    int rp = sR;
	    int dp = sL;

	
	    while (lp < sR && rp < eR) 
	    {
	  
	      int leftValue = x[lp];
	      int rightValue = x[rp];
	      if (leftValue <= rightValue) 
	      {
	        y[dp++] = leftValue;
	        lp++;
	      } else 
	      {
	        y[dp++] = rightValue;
	        rp++;
	      }
	    }
	   
	    while (lp < sR) 
	    {
	      y[dp++] = x[lp++];
	    }
	    while (rp < eR) 
	    {
	      y[dp++] = x[rp++];
	    }
	  }

	
	

public static void InsertCart()
{
	
	
	File SpoolX1 = new File(T1N);
	try 
	{
	Scanner SX1 = new Scanner(SpoolX1);
	if (SpoolX1.canRead())
	{
		Tape1s = SX1.nextInt();
		Tape1 = new int[Tape1s];
		for (int i = 0; i <Tape1.length; i++)
		{
			Tape1[i] = SX1.nextInt();
		}
		SX1.close();
	}
	else
	System.out.print("Cannot open/read file!");
	}
	catch (FileNotFoundException e)
	{
		System.exit(0);
	}
	File SpoolX2 = new File(T2N);
	try
	{
	Scanner SX2 = new Scanner(SpoolX2);
	if(SpoolX2.canRead())
	{
		Tape2s = SX2.nextInt();
		Tape2 = new int[Tape2s];
		for (int i = 0; i <Tape2.length; i++)
		{
			Tape2[i] = SX2.nextInt();
			
		}
		SX2.close();
		
	}
	else
	System.out.print("Cannot open/read file!");
	}
	catch (FileNotFoundException e)
	{
		System.exit(0);
	}
	
	
	
}



public static void dumpArray(int[] x)
{
	File contents = new File(FileName);
	try
	{
		FileWriter DAC = new FileWriter(contents);
		if(contents.canWrite())
		{
			for (int i = 0; i < x.length; i++)
			{
				DAC.write(Integer.toString(x[i]) + System.lineSeparator());
			}
			DAC.close();
		}
		else
		System.out.print("File is protected or cannot be written to!");
	}
	catch (IOException e)
	{
		System.exit(0);
	}
}

}

	



