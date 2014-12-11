package com.weblib.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.weblib.db.DatabaseConnector;

public abstract class AbstractDAO {
	
	public List<Object> genericSelect(String query) throws SQLException {
		List<Object> list = new ArrayList<Object>();
		try {
			Connection connection = DatabaseConnector.INSTANCE.getConnection();
			if (connection != null) {
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet resultSet = statement.executeQuery(query);
				ResultSetMetaData meta = resultSet.getMetaData();
				
				while (resultSet.next()) {
					for (int i = 1; i <= meta.getColumnCount(); i++) {
						Object colValue = resultSet.getObject(i);
						list.add(colValue);
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
}
