//David Norvell
//Advanced Java @ OCCC Fall ONLINE 2020
//goNEXRADDatabase.class
//Final Project WX_Prowler

package WXApp;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class goNEXRADDatabase implements Serializable, Runnable
{
	/**
	 * SerialID
	 */
	private static final long serialVersionUID = 6588958317985678548L;

	public Connection conn = null;
	public static final String QUERY_RADAR = " '%-019-I%' ";
	public static final  String QUERY_SHEAR = " '%-026-I%' ";
	public static final String defaultTime = "CURRENT_TIMESTAMP";
	public boolean hasTables = false;
	
	public goNEXRADDatabase()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			String URL = "jdbc:sqlite:goNEXRADDB.db";
			conn = DriverManager.getConnection(URL, "root"," ");
			System.out.println("Connection established! ");
            
			
		}
		catch(SQLException | ClassNotFoundException e)
		{
			System.err.println(e);
		}
		
		
		
	}
	
	public void CreateDBTables()
	{
		if(hasTables == false)
		{
		String SQL_CREATE = "Create TABLE RECORD"
				+"("
				+"RecordID INTEGER PRIMARY KEY,"
				+"RecordDESC TEXT,"
				+"sqltime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL"
				+ ")";
		
		runSQLStatement(SQL_CREATE);
		
		String SQL_CREATEINDEX_0 ="CREATE INDEX desc ON RECORD(recordDESC)";
		
		runSQLStatement(SQL_CREATEINDEX_0);
		
		String SQL_CREATE2 = "Create TABLE RADAR"
				+"("
				+"RadarID INTEGER NOT NULL,"
				+"AvaliableRadProds TEXT,"
				+"baseReflectivity blob null default (x''),"
				+"bulkShear blob null default (x''),"
		        +"FOREIGN KEY(RadarID) REFERENCES RECORD(RecordID)"
		        +")";
		
		runSQLStatement(SQL_CREATE2);
		
		String SQL_CREATE3 = "Create TABLE WXMAPS"
				+"("
				+"RecID INTEGER NOT NULL,"
				+"MapID INTEGER NOT NULL,"
				+"ElementDescription TEXT,"
				+"ConvectiveOutlook blob null default (x''),"
				+"MesoDiscussions blob null default (x''),"
				+"MesoNetTemp blob null default (x''),"
				+"MesoNetHumidity blob null default (x''),"
				+"FOREIGN KEY(RecID) REFERENCES RECORD(RecordID),"
				+"PRIMARY KEY(MapID, RecID)"
				+")";
		
		runSQLStatement(SQL_CREATE3);
		this.hasTables = true;
		}
		else
		return;
		        
		
	}
	
    public StringBuilder dateSpool()
    {
		LocalDate CD = LocalDate.now();
		DateTimeFormatter fmt =  DateTimeFormatter.ISO_LOCAL_DATE;
		String date = CD.format(fmt);
		return new StringBuilder(date);
    }
	
	public ResultSet runSQLQuery(String SQL)
	{
		ResultSet emptySet = null;
		if(conn != null)
		{
			try
			{
		    Statement sta = conn.createStatement();
		    return sta.executeQuery(SQL);
			}
			catch(SQLException e)
			{
				System.err.println(e);
			}
		}
		
		return emptySet;
		
	}
	
	public ArrayList<StringBuilder> runPatternMatchQuery(String query, String date)
	{
		ResultSet emptySet = null;
		ArrayList<StringBuilder> data = new ArrayList<StringBuilder>();
	    StringBuilder PMQ = new StringBuilder("SELECT RecordDESC FROM RECORD WHERE RecordDESC LIKE " + query + " AND RecordDESC LIKE " + "'" + date + "%'" + " ORDER BY RecordID DESC LIMIT 10" );
		if(conn != null)
		{
			try
			{
				PreparedStatement sta = conn.prepareStatement(PMQ.toString());
				emptySet = sta.executeQuery();
				
				
				try {
				
				while(emptySet.next())
				{
					StringBuilder temp = new StringBuilder(emptySet.getString(1));
					data.add(temp);
					
				}
				return data;
				}
				catch(SQLException e)
				{
					System.err.println(e);
				}
			}
			catch(SQLException e)
			{
				System.err.println(e);
			}
		}
		return data;
	}
	
	public boolean runSQLStatement(String SQL2)
	{
		if(conn != null)
		{
			try
			{
			Statement sta = conn.createStatement();
		    return sta.execute(SQL2);
			}
			catch(SQLException e)
			{
				System.err.println(e);
			}
		}
		
		return false;
		
	}
	
	public boolean CastleBravo()
	{
		if(conn != null)
		{
			try{
				Statement sta = conn.createStatement();
				return sta.execute("DELETE FROM RECORD");
			}
			catch(SQLException e)
			{
				System.err.println(e);
			}
		}
		return false;
	}
	
	public boolean addRecordRow(int RecordID, String RecordDESC, String time )
	{
		StringBuilder addColumn = new StringBuilder("INSERT INTO RECORD VALUES" + "(" +  RecordID  + "," + "'" + RecordDESC + "'" + "," + time + ")" ); 
				
		if(conn != null)
		{
			try {
				
				PreparedStatement sta = conn.prepareStatement(addColumn.toString());
			    return sta.execute();
			}
			catch(SQLException e)
			{
				System.err.println(e);
			}
		}
		return false;
	}
	
	
	public boolean addRadarRow(int RadarID, String AvaliableRadProds) //First of multiple overloaded functions, because at a minimum we need the ID field and description
	{
		StringBuilder addColumn = new StringBuilder("INSERT INTO RADAR (RadarID,AvailableRadProds,baseReflectivity,bulkShear)"
				+"VALUES(),"
				+"VALUES(),"
				+"VALUES(),"
				+"VALUES()"
				+";");
		
		if(conn != null)
		{
			try {
				Statement sta = conn.createStatement();
				return sta.execute(addColumn.toString());
			}catch(SQLException e)
			{
				System.err.println(e);
			}
			
			
		}
		return false;
	}
	
	public boolean addRadarRow(int RadarID, String AvaliableRadProds, byte[] baseRefBLOB) //How does one BLOB in Java?
	{
		StringBuilder addColumn = new StringBuilder();
		if(conn != null)
		{
			try {
				Statement sta = conn.createStatement();
				return sta.execute(addColumn.toString());
			}catch(SQLException e)
			{
				System.err.println(e);
			}
			
		}
		return false;
	}
	
	public boolean addRadarRow(int RadarID, String AvailableRadProds, byte[] baseRefBLOB,byte[] bulkShearBLOB)
	{
		StringBuilder addColumn = new StringBuilder();
		if(conn != null)
		{
			try {
				Statement sta = conn.createStatement();
				return sta.execute(addColumn.toString());
			}catch(SQLException e)
			{
				System.err.println(e);
			}
		}
		return false;
	}
	
	@Override
	protected void finalize() throws Throwable 
	{
		if (conn == null || !conn.isClosed())
		{
			conn.close();
		}
	}

	@Override
	public void run() {
		
		
	}


}
