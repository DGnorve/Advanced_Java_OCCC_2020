//David Norvell
//Advanced Java @ OCCC Fall 2020 Online
//goNEXRADCrawlerQueryDemo.class
//Final Project "WX_Prowler"

package WXApp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Semaphore;

public class goNEXRADCrawlerQueryDemo implements Serializable, Runnable
{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 3804211785201160242L;
	
	//Our file/API handling
	public File nextradData = null;
	public InputStream radStream = null;
	
	//These all exist only to be merged with other objects into StringBuilders, so lets keep them from making dupes constantly.
	public static final String awsPullIndex = "aws s3 ls s3://unidata-nexrad-level2-chunks/"; 
	public static final String awsDownload = " aws s3 cp s3://unidata-nexrad-level2-chunks/";
	public static final String[] awsParams = {"--recursive","--no-sign-request"};
	
	//Our beloved StringBuilders, our REQ string to be passed via Command to hit AWS.
	public StringBuilder awsFinalREQ;
	public StringBuilder path;
	public String radarSite = null; 


	//Two generics, and both StringBuilders due to them being what ingest our AWS query output prior to passing it to SQL DB. 
	public ArrayList<StringBuilder> data = new ArrayList<StringBuilder>(0);
	public ArrayList<StringBuilder> filtered;
	
    public static Path dir;
    public static Path dirU;
    private static final Semaphore mutex = new Semaphore(1);
	
	public goNEXRADCrawlerQueryDemo()
	{
		super();
		
	}
	
	public static synchronized void main(goNEXRADCrawlerQueryDemo Xe, String Ir, int param)
	{
		try{
			mutex.acquire();
			switch(param)
			{
			case 0:
			Xe.init();
			Xe.invokeAwsCli(Ir, param);
			break;
			case 1:
			Xe.invokeAwsCli(Ir, param);
			
			}
		}catch(InterruptedException X) {
			System.err.println(X);
		}finally {mutex.release();}
		
	}
	
	public void init()
	{
		dir = Path.of("C:\\Users\\ArcosMKIII\\eclipse-workspace\\awsNEXRADQueryDemoII\\WXApp\\compressedArchives");
		dirU = Path.of("C:\\Users\\ArcosMKIII\\eclipse-workspace\\awsNEXRADQueryDemoII\\WXApp\\decompressedArchives");
	}
		
	

	public void invokeAwsCli(String args, int mode) 
	{
		
		//Example of a correct query for KTLX (OKC)
		//KTLX/85/20201203-124152-024-I
		//KTLX is the site code for that NEXRAD site
		//85 is the Volume Number
		//20201203 is the YEAR,MONTH, and DAY 
		//124152 is 12 HOURS, 41 MINUTES and 52 SECONDS
		//024 is the Chunk Number
		//I is the Product Type.
		//in CMD to retrieve this file we'd do
		//aws s3 cp s3://unidata-nexrad-level2-chunks/KTLX/85/20201203-124152-024-I
		//The issue is that basically we can't know for sure exactly what Volume Number or Chunk Number we're trying to grab.
	         try { 
	               
                    final ProcessBuilder pb = new ProcessBuilder("cmd.exe","/C", args);
					final Process p = pb.start();
					pb.redirectErrorStream(true);
				    if (mode != 1)
				    {
				    final InputStream silkRoad = p.getInputStream();
				    BufferedReader reader = new BufferedReader(new InputStreamReader(silkRoad));
				    String line = null;
				    StringBuilder secondary = new StringBuilder("");
				    while((line = reader.readLine()) != null)
				    {
				    	secondary = new StringBuilder(line);
				    	this.data.add(secondary);
				    	System.gc();
				    }
				    
				    }
			
					}
		          catch(IOException e){
					System.err.println(e);
				}
				
			  
	         }
	
	public void cleanRS(ArrayList<StringBuilder> x, int mode)
	{
		if(mode == 0)
		{
		String helper = null;
		for(int i = 0; i < x.size();i++)
		{
			helper = x.get(i).toString().strip();
			String[] comps = helper.split("\\s++");
			x.set(i, new StringBuilder(comps[3].replaceAll("\\\\", "\\\\\\\\")));
		}
		}
		if(mode == 1)
		{
	        String helper = null;
			for(int i = 0; i < x.size(); i++)
			{
				helper = x.get(i).toString();
				String[] temp = helper.replace('\\', '/').split("/");
				System.out.println(temp[2]);
				x.set(i, new StringBuilder(temp[2].trim().strip()));
			}
		}
	}
	
	

	@Override
	public void run() {
		for(int i = 0; i < this.data.size();i++)
		{
			
		}
		
	}
	
	
	
	

	

}
