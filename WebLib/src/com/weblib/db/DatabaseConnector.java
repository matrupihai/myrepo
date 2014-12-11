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
	
	public void connect() {
		try {
			Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			Logger.getLogger("LOG").info("Connected.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				Logger.getLogger("LOG").info("Disconnected.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected() throws SQLException {
		return connection != null && !connection.isClosed();
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}

