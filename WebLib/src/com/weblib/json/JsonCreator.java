package com.weblib.json;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public enum JsonCreator {
	INSTANCE;
	
	public String objectToJson(Object... objects) {
		StringBuilder json = new StringBuilder();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		for (Object object : objects) {
			try {
				json.append(ow.writeValueAsString(object));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return json.toString();
	}
	
	
}
