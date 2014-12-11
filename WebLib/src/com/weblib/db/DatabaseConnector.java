package com.weblib.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.sybase.jdbc3.jdbc.SybDataSource;

public enum DatabaseConnector {
	INSTANCE;
	
	private static final String USERNAME = "DBA";
	private static final String PASSWORD = "dba";
	
	private static Connection connection = null;
	private static SybDataSource mainDataSource = null;
	private static boolean isConnected = false; 
	
	private DatabaseConnector() {
		connect();
	}
	
	private void connect() {
		try {
			mainDataSource = getDataSource(USERNAME, PASSWORD, 2638);
			connection = mainDataSource.getConnection();
			isConnected = true;
			Logger.getLogger("DatabaseConnector").info("Connected.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static SybDataSource getDataSource(String user, String pass, int portNumber) {
		SybDataSource source = new SybDataSource();
		source.setUser(user);
		source.setPassword(pass);
		source.setPortNumber(portNumber);
		
		return source;
	}
	
	public void disconnect() {
		if (connection != null){
			try {
				connection.close();
				isConnected = false;
				Logger.getLogger("DatabaseConnector").info("Disconnected.");
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

