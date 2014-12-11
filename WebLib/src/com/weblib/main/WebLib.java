package com.weblib.main;

import com.weblib.dao.TestDAO;


public class WebLib {
	
	public static void main(String[] args) {
		System.out.println(new TestDAO().getTestObjects());
	}
	
}
