package com.project.connection;

import java.sql.*;
import java.sql.SQLException;

public class DBConnection {
	public Connection getConnection() {
		Connection conn = null;
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			String dbURL = "jdbc:sqlserver://localhost\\sqlexpress:1433;databaseName=TrainProjectDB;user=sa;password=Marimba#123";
			conn = DriverManager.getConnection(dbURL);
			//if (conn != null) {
			 //   System.out.println("Connected");
			//}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
