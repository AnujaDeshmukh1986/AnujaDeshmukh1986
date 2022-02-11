package com.loggerAssignment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MonitorLogsDao {
	
	public void saveToDB(String key, double duration, String loggerType, String host, boolean alert) {
		System.out.println("In MonitorLogsDao:: saveToDB::Start::");
		Connection con = null; 
	      Statement stmt = null; 
	      int result = 0; 
	      try { 
	         Class.forName("org.hsqldb.jdbc.JDBCDriver"); 
	         con = DriverManager.getConnection( "jdbc:hsqldb:hsql://localhost/testdb", "SA", ""); 
	         stmt = con.createStatement(); 
	         result = stmt.executeUpdate("INSERT INTO T_AUDIT_LOGS VALUES ("+key+","+duration+","+loggerType+","+host+","+alert+")"); 
	         con.commit(); 
	      }catch (Exception e) { 
	      } 
	      System.out.println(result+" rows effected"); 
	      System.out.println("Rows inserted successfully"); 
	      System.out.println("In MonitorLogsDao:: saveToDB::End::");
	}

}
