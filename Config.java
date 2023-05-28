package com.crudoperations.CRUDproj;

import java.sql.Connection;
import java.sql.DriverManager;

public class Config {

	public static Connection getconn() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mycrud", "root", "root");
			return con;
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
