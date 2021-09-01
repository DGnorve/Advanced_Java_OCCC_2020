//David Norvell
//Advanced Java @ OCCC Fall 2020 Online
//prowlerMain.class
//Final Project "WX_Prowler"

package WXApp;

import java.util.concurrent.Semaphore;
import javax.swing.SwingUtilities;

public class prowlerMain 
{
	 public static final Semaphore lock = new Semaphore(1);

	public static void main(String...args) 
	{
	   SwingUtilities.invokeLater(new Runnable() {

		@Override
		public void run() {
			
			new GenericGUIApp();
			
		}
		   
	   });
	   
	  
	   
		
	}
	
	

}
