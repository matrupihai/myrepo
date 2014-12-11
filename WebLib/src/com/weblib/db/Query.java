package com.weblib.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

public class Query {
	
	private String query = null;
	private Map<Integer, Object> params = new HashMap<Integer, Object>();
	
	public Query() {
	}
	
	public Query(String query) {
		setQuery(query);
	}
	
	public Query(String format, Object... args) {
		this(String.format(format, args));
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public void setQuery(String format, Object... args) {
		setQuery(String.format(format, args));
	}
	
	public void addParameter(Integer key, Object value) {
		params.put(key, value);
	}
	
	public Object getParameter(Integer i) {
		return params.get(i);
	}
	
	public Set<Integer> getParameterKeys() {
		return params.keySet();
	}

	@Override
	public String toString() {
		String result = query;
		for (int i = 1; i <= params.size(); i++) {
			if (params.get(i) == null) {
				result = result.replaceFirst("\\?", "'null'");
				
			} else {
				result = result.replaceFirst("\\?", "'" + Matcher.quoteReplacement(params.get(i).toString().replace('\n', ' ')) + "'");
			}
		}
		return result;
	}

}
