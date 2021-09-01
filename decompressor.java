//David Norvell
//Advanced Java OCCC Fall 2020 ONLINE
//decompressor.class
//Final Project "WXProwler"

package WXApp;


import java.io.*;
import java.util.concurrent.Semaphore;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;


public class decompressor implements Serializable, Runnable
{
	 /**
	 * serial ID
	 */
	private static final long serialVersionUID = 5177295431232219415L;
	
	//General fields
     public byte[] rawB;
     private byte[] rawData;
     private static final Semaphore mutexD = new Semaphore(1);
	 
    public decompressor()
	{
		super();
		
	}

  
  public void setRawB(byte[] x)
  {
	  this.rawB = new byte[x.length];
	  System.arraycopy(x, 0, this.rawB, 0, x.length);
  }
  
	
	public void bzip2toBytes(String x)
	{
            File target = new File(x);
       try {
			FileInputStream fi = new FileInputStream(x);
			byte[] rawData = fi.readAllBytes();
			
			BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(rawData));
			is.skipNBytes(4);
			ByteArrayOutputStream rawOut = new ByteArrayOutputStream();
		
				byte[] buffer = new byte[4096];
				int len = 0;
				while((len = is.read(buffer, 0, buffer.length)) != -1 )
				{
					rawOut.write(buffer,0,len);
					
				}
				fi.close();
				byte[] xe = rawOut.toByteArray();
			    rawB = new byte[xe.length];
			    System.arraycopy(xe, 0, rawB,0, rawB.length);
			    
	           }catch(IOException e){
				System.err.println(e);
			}
	
	
	   
	}
	
	
  public void unpackBzip(byte[] data, String fileName)
  {
	   
	   File deDir = new File(goNEXRADCrawlerQueryDemo.dirU.toString());
	   File dest = new File(deDir.toPath() + "//" + fileName);
	   try {
		 deDir.mkdir();
		 dest.createNewFile();
	   }catch(IOException e) {
		   System.err.println(e);
		   }
	   
      
       try {
              
	          OutputStream os = new FileOutputStream(dest);
		      ByteArrayInputStream inflateRaw = new ByteArrayInputStream(data);
		      BZip2CompressorInputStream decomp = new BZip2CompressorInputStream(inflateRaw);
		      byte[] block = new byte[1024];
		      int len = 0;
		      while((len = decomp.read(block,0,block.length)) != -1)
		      {
			  os.write(block);
		      }
		      
		       decomp.close();
		      os.close();
		     
		  }
		  catch(IOException e){
		  System.err.println(e);
		  }
		  
	     
	}
  
  
@Override
public void run() 
{

	
}
		

}
