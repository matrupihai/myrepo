package com.weblib.json;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class JsonHelper {
	
	public static String objectToJson(Object... objects) {
		StringBuilder json = new StringBuilder();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		if (objects.length > 0) {
			for (Object object : objects) {
				if (object != null) {
					try {
						json.append(ow.writeValueAsString(object));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			return "No results";
		}
		
		return json.toString();
	}
	
	
}
