package com.david.springhibernate.test.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	public static Connection connection;
	
	private SQLConnection() {

	}
	
	public static synchronized Connection getSQLConnection() {
		if(connection == null) {
			try {
				Class.forName(TestConstants.JDBC_DRIVER);
				connection = DriverManager.getConnection(TestConstants.DB_URL, TestConstants.DB_USER, TestConstants.DB_PASSWORD);
				connection.setAutoCommit(true);
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
}
