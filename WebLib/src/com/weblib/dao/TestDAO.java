package com.weblib.dao;

import java.util.List;

import com.weblib.db.Query;


public class TestDAO extends AbstractDAO {
	
	public List<Object> getTestObjects() {
		Query query = new Query("SELECT * FROM DBA.\"TEST_TABLE\" ");
		return genericSelect(query);
	}
	
}
