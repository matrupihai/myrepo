package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.weblib.dao.AuthorDAOImpl;
import com.weblib.json.JsonHelper;

@Path("/authors")
@Produces (MediaType.APPLICATION_JSON)
public class AuthorResource {
	AuthorDAOImpl dao = new AuthorDAOImpl();
	
	@GET
	public String getAuthors(@QueryParam("authorName") String authorName) {
		if (authorName != null) {
			return JsonHelper.objectToJson(dao.findAuthorByName(authorName));
		}
		return JsonHelper.objectToJson(dao.findAllAuthors());
	}
	
	@GET
	@Path("{id}")
	public String getAuthorById(@PathParam("id") Integer id) {
		return JsonHelper.objectToJson(dao.findAuthorById(id));
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
	@Path("{id}/books")
	public String getBooksByAuthorId(@PathParam("id") Integer id) {
		return JsonHelper.objectToJson(dao.findBooksByAuthor(id));
	}
	
}
