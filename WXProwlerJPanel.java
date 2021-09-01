//David Norvell
//Advanced Java OCCC Fall 2020
//WXProwlerJFrame.class

package WXApp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WXProwlerJPanel extends JPanel implements ActionListener
{
   private static final long serialVersionUID = 2282633284062557602L;
   private String[] radarSites;
   private String State;
   private String selected = null;
   private Image mainImage;
   private goNEXRADCrawlerQueryDemo ae = null;
   private goNEXRADDatabase b = null;
  
   JTextField locale;
   JPictureFrame main;
   JButton commit, siteS;
   JComboBox<String> selection;
   
   
   public WXProwlerJPanel()
   {
	   setLayout(new BorderLayout());
	   JPanel northPanel = new JPanel();
	   JPanel eastPanel = new JPanel();
	   JPanel centerPanel = new JPanel();
	   JPanel westPanel = new JPanel();
	   JPanel southPanel = new JPanel();
	   
	   JMenuBar mb = new JMenuBar();
	   mb.setPreferredSize(new Dimension(590,20));
	   northPanel.add(mb);
	   
	    JMenu m1 = new JMenu("Settings");
		JMenu m2 = new JMenu("Select WX Product");
		JMenu m3 = new JMenu("Documentation");
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		
		JMenuItem m01 = new JMenuItem("Select NEXRAD Site");
		JMenuItem m02 = new JMenuItem("Force Refresh");
		JMenuItem m03 = new JMenuItem("Save Current Image");
		m1.add(m01);
		m1.add(m02);
		m1.add(m03);
		
		JMenuItem m11 = new JMenuItem("Base Reflectivity");
		JMenuItem m21 = new JMenuItem("Bulk Shear");
		JMenuItem m31 = new JMenuItem("SPC Outlook_Day1");
		JMenuItem m32 = new JMenuItem("SPC Outlook_Day2");
		JMenuItem m33 = new JMenuItem("SPC Outlook_Day3");
		JMenuItem m34 = new JMenuItem("Meso_Disscusions");
		JMenuItem m35 = new JMenuItem("Mesonet_Temps");
		JMenuItem m36 = new JMenuItem("Mesonet_Humidity");
		m2.add(m11);
		m2.add(m21);
		m2.add(m31);
		m2.add(m32);
		m2.add(m33);
		m2.add(m34);
		m2.add(m35);
		m2.add(m36);
		
		JMenuItem m40 = new JMenuItem("Manual");
		m3.add(m40);
		
		selection = new JComboBox<String>();
		commit = new JButton("Set State");
		siteS = new JButton("Select Site");
		siteS.addActionListener(this);
		commit.addActionListener(this);
        selection.addActionListener(this);
		
		main = new JPictureFrame();
		main.setPreferredSize(new Dimension(600,600));
		main.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		locale = new JTextField(20);
		
		southPanel.add(locale);
		southPanel.add(commit);
		southPanel.add(selection);
		southPanel.add(siteS);
		centerPanel.add(main);
		
		add(northPanel, BorderLayout.NORTH);
		add(eastPanel,BorderLayout.EAST);
		add(centerPanel,BorderLayout.CENTER);
		add(westPanel,BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);
		
		

   }
   
   public String[] setSelections(String State)
   {
	   if(State.equalsIgnoreCase("Alabama"))
	   {
		   return new String[] {"KBMX","KEOX","KHTX","KMOB","KMMX"};
	   }
	   if(State.equalsIgnoreCase("Alaska"))
	   {
		   return new String[] {"PABC","PACG","PAEC","PAHG","PAIH","PAKC","PAPD"};
	   }
	   if(State.equalsIgnoreCase("Arizona"))
	   {
		   return new String[] {"KEMX","KFSX","KIWA","KYUX"};
	   }
	   if(State.equalsIgnoreCase("Arkansas"))
	   {
		   return new String[] {"KLKZ","KSRX"};
	   }
	   if(State.equalsIgnoreCase("California"))
	   {
		   return new String[] {"KBBX","KBHX","KDAX","KEYX","KHNX","KMUX","KNKX","KSOX","KVBG","KVTX"};
	   }
	   if(State.equalsIgnoreCase("Colorado"))
	   {
		   return new String[] {"KFTG","KGJX","KPUX"};
	   }
	   if(State.equalsIgnoreCase("Delaware"))
	   {
		   return new String[] {"KDOX"};
	   }
	   if(State.equalsIgnoreCase("Florida"))
	   {
		   return new String[] {"KAMX","KBYX","KEVX","KJAX","KMLB","KTLH","KTBW"};
	   }
	   if(State.equalsIgnoreCase("Georgia"))
	   {
		   return new String[] {"KFFC","KJGX","KVAX"};
	   }
	   if(State.equalsIgnoreCase("Hawaii"))
	   {
		   return new String[] {"PHKI","PHKM","PHMO","PHWA"};
	   }
	   if(State.equalsIgnoreCase("Idaho"))
	   {
		   return new String[] {"KCBX","KSFX"};
	   }
	   if(State.equalsIgnoreCase("Illinois"))
	   {
		   return new String[] {"KILX","KLOT"};
	   }
	   if(State.equalsIgnoreCase("Indiana"))
	   {
		   return new String[] {"KIND","KIWX","KVWX"};
	   }
	   if(State.equalsIgnoreCase("Iowa"))
	   {
		   return new String[] {"KDMX","KDVN"};
	   }
	   if(State.equalsIgnoreCase("Kansas"))
	   {
		   return new String[] {"KDDC","KGLD","KICT","KTWX"};
	   }
	   if(State.equalsIgnoreCase("Kentucky"))
	   {
		   return new String[] {"KHPX","KJKL","KLVX","KPAH"};
	   }
	   if(State.equalsIgnoreCase("Louisiana"))
	   {
		   return new String[] {"KLCH","KLIX","KPOE","KSHV"};
	   }
	   if(State.equalsIgnoreCase("Maine"))
	   {
		   return new String[] {"KCBW","KGYX"};
	   }
	   if(State.equalsIgnoreCase("Maryland"))
	   {
		   return new String[] {"KLWX"};
	   }
	   if(State.equalsIgnoreCase("Massachusetts"))
	   {
		   return new String[] {"KBOX"};
	   }
	   if(State.equalsIgnoreCase("Michigan"))
	   {
		   return new String[] {"KAPX","KDTX","KGRR","KMQT"};
	   }
	   if(State.equalsIgnoreCase("Minnesota"))
	   {
		   return new String[] {"KDLH","KMPX"};
	   }
	   if(State.equalsIgnoreCase("Mississippi"))
	   {
		   return new String[] {"KDGX","KGWX"};
	   }
	   if(State.equalsIgnoreCase("Missouri"))
	   {
		   return new String[] {"KEAX","KLSX","KSGF"};
	   }
	   if(State.equalsIgnoreCase("Montana"))
	   {
		   return new String[] {"KBLX","KGGW","KMSX","KTFX"};
	   }
	   if(State.equalsIgnoreCase("Nebraska"))
	   {
		   return new String[] {"KLNX","KOAX","KUEX"};
	   }
	   if(State.equalsIgnoreCase("Nevada"))
	   {
		   return new String[] {"KESX","KLRX","KRGX"};
	   }
	   if(State.equalsIgnoreCase("New Jersey"))
	   {
		   return new String[] {"KDOX"};
	   }
	   if(State.equalsIgnoreCase("New Mexico"))
	   {
		   return new String[] {"KABX","KFDX","KHDX"};
	   }
	   if(State.equalsIgnoreCase("New York"))
	   {
		   return new String[] {"KBGM","KBUF","KENX","KOKX","KTYX"};
	   }
	   if(State.equalsIgnoreCase("North Carolina"))
	   {
		   return new String[] {"KLTX","KMHX","KRAX"};
	   }
	   if(State.equalsIgnoreCase("North Dakota"))
	   {
		   return new String[] {"KBIS","KMVX","KMBX"};
	   }
	   if(State.equalsIgnoreCase("Ohio"))
	   {
		   return new String[] {"KCLE","KILN"};
	   }
	   if(State.equalsIgnoreCase("Oklahoma"))
	   {
		   //return home;
		   return new String[] {"KFDR","KINX","KTLX","KVNX"};
	   }
	   if(State.equalsIgnoreCase("Oregon"))
	   {
		   return new String[] {"KMAX","KPDT","KRTX"};
	   }
	   if(State.equalsIgnoreCase("Pennsylvania"))
	   {
		   return new String[] {"KCCX","KDIX","KPBZ"};
	   }
	   if(State.equalsIgnoreCase("South Carolina"))
	   {
		   return new String[] {"KCAE","KCLX","KGSP"};
	   }
	   if(State.equalsIgnoreCase("South Dakota"))
	   {
		   return new String[] {"KABR","KFSD","KUDX"};
	   }
	   if(State.equalsIgnoreCase("Tennessee"))
	   {
		   return new String[] {"KMRX","KNQA","KOHX"};
	   }
	   if(State.equalsIgnoreCase("Texas"))
	   {
		   //return new String[]{"MessingWithTX"};
		   return new String[] {"KAMA","KBRO","KCRP","KDFX","KDYX","KEPZ","KEWX","KFWS","KGRK","KHGX","KLBB","KMAF","KSJT"};
	   }
	   if(State.equalsIgnoreCase("Utah"))
	   {
		   return new String[] {"KICX","KMTX"};
	   }
	   if(State.equalsIgnoreCase("Vermont"))
	   {
		   return new String[] {"KCXX"};
	   }
	   if(State.equalsIgnoreCase("Virginia"))
	   {
		   return new String[] {"KAKQ","KFCX","KLWX"};
	   }
	   if(State.equalsIgnoreCase("Washington"))
	   {
		   return new String[] {"KATX","KLGX","KOTX"};
	   }
	   if(State.equalsIgnoreCase("Washington D.C."))
	   {
		   return new String[] {"KLWX"};
	   }
	   if(State.equalsIgnoreCase("West Virginia"))
	   {
		   return new String[] {"KRLX"};
	   }
	   if(State.equalsIgnoreCase("Wisconsin"))
	   {
		   return new String[] {"KARX","KGRB","KMKX"};
	   }
	   if(State.equalsIgnoreCase("Wyoming"))
	   {
		   return new String[] {"KCYS","KRIW"};
	   }
	   
	   return new String[] {};
	   
   }
   
   public void setImage(BufferedImage g)
   {
	   this.mainImage = g;
   }
   

@Override
public void actionPerformed(ActionEvent e) 
{

		Object Src = e.getSource();
		
		if(Src.equals(this.commit))
		{
			String s = (String)this.locale.getText();
			this.State = s;
			this.radarSites = setSelections(this.State);
			for(int i = 0; i < this.radarSites.length;i++)
			{
			this.selection.addItem(this.radarSites[i]);
			}
			revalidate();
		}
		
		if(Src.equals(this.siteS))
		{
			//Once we have site, create Query and DB if not exists!
			//Then create aws string
			
			 if(this.ae == null)
			 {
			 this.ae = new goNEXRADCrawlerQueryDemo();
			 }
			 
			 if(this.b != null)  {
				 b.CastleBravo();
			 }
			 
			this.ae.awsFinalREQ = new StringBuilder(goNEXRADCrawlerQueryDemo.awsPullIndex + this.selected + " " + goNEXRADCrawlerQueryDemo.awsParams[0] + " " + goNEXRADCrawlerQueryDemo.awsParams[1]);
			this.ae.radarSite = this.selected;
			try {
			prowlerMain.lock.acquire();
            goNEXRADCrawlerQueryDemo.main(ae, ae.awsFinalREQ.toString(), 0); //Desperately attemping to get as much off of AWT with overloaded mains, we'll see how badly this ends
			}catch(InterruptedException ae) {
				System.err.println(ae);}
			
			prowlerMain.lock.release();
			
			 if(this.b == null)
			 {
			   this.b = new goNEXRADDatabase();
			   this.b.CreateDBTables();
			 }
			 
			 
			 
			
			 
			for(int i = 0; i < ae.data.size(); i++)
			{
			int a = i;
			try {
				prowlerMain.lock.acquire();
			Thread runSQL = new Thread(()->this.b.addRecordRow(a, this.ae.data.get(a).toString(), goNEXRADDatabase.defaultTime));
			runSQL.start();
			runSQL.join();
			prowlerMain.lock.release();
			}catch(InterruptedException ael) {
				System.err.println(ael);
			}
			}
			
			
			
			this.ae.filtered = this.b.runPatternMatchQuery(goNEXRADDatabase.QUERY_RADAR, this.b.dateSpool().toString());
			this.ae.cleanRS(this.ae.filtered, 0);
			
			
		   
			for(int i = 0; i < this.ae.filtered.size(); i++)
			{
			this.ae.awsFinalREQ = new StringBuilder(goNEXRADCrawlerQueryDemo.awsDownload + this.ae.filtered.get(i) + " " + goNEXRADCrawlerQueryDemo.awsParams[1] + " " + goNEXRADCrawlerQueryDemo.dir.toString());
			try {
			prowlerMain.lock.acquire();
			goNEXRADCrawlerQueryDemo.main(ae, this.ae.awsFinalREQ.toString(), 1);
			
			}catch(InterruptedException aeli){
				System.err.println(aeli);
			}finally {prowlerMain.lock.release();}
			}
			
			
			
			
			this.ae.cleanRS(this.ae.filtered, 1);
			decompressor ze = new decompressor();
			
		
		   
			 
			for(int i = 0; i < this.ae.filtered.size(); i++)
			{
			  String helper = this.ae.filtered.get(i).toString().trim().strip();
			  try {
			  prowlerMain.lock.acquire();
			 
			  Thread getBytes = new Thread(()->ze.bzip2toBytes(goNEXRADCrawlerQueryDemo.dir.toString() + '\\' + helper.toString()));
			  getBytes.run();
			  getBytes.join();
              prowlerMain.lock.release();
			  
			  prowlerMain.lock.acquire();
			  int x = i;
			  Thread decomp = new Thread((()->ze.unpackBzip(ze.rawB, helper +"DC" + x)));
			  decomp.run();
			  decomp.join();
			  prowlerMain.lock.release();
			 
			  }catch(InterruptedException aelita) {
				  System.err.print(aelita);
			  }
		     // System.out.println((char)ze.rawB[0] + (char)ze.rawB[1] + (char)ze.rawB[2] + (char)ze.rawB[3]);
			 //ze.unpackBzip(rawB, Dpath, this.ae.filtered.get(i).toString() + "_" + i);
			}
			
			
			
		}

		else if(Src.equals(this.selection))
		{
			 if(this.selected == null || this.selected.equalsIgnoreCase((String)this.selection.getSelectedItem()) == false) {
				 this.selected = new String((String)this.selection.getSelectedItem()); }
	
		 
		}
   }
	
	
}

   




