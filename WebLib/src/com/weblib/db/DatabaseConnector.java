package com.weblib.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public enum DatabaseConnector {
	INSTANCE;
	
	private static final String CONN_STRING = "jdbc:sybase:Tds:localhost:2639";
	private static final String USERNAME = "DBA";
	private static final String PASSWORD = "dba";
	
	private static Connection connection = null;
	private static boolean isConnected = false; 
	
	private DatabaseConnector() {
		connect();
	}
	
	private void connect() {
		try {
			Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			isConnected = true;
			Logger.getLogger("LOG").info("Connected.");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				isConnected = false;
				Logger.getLogger("LOG").info("Disconnected.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}

