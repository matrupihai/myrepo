package com.weblib.main;

import java.sql.Connection;

import com.weblib.db.DatabaseConnector;

public class WebLib {
	
	public static void main(String[] args) {
		Connection connection = DatabaseConnector.INSTANCE.getConnection();
	}
	
}
