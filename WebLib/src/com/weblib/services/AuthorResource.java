package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.weblib.dao.AuthorDAOImpl;
import com.weblib.json.JsonHelper;

@Path("/authors")
@Produces (MediaType.APPLICATION_JSON)
public class AuthorResource {
	
	@GET
	public String getAuthors() {
		return JsonHelper.INSTANCE.objectToJson(new AuthorDAOImpl().findAllAuthors());
	}
	
	@GET
    @Path("{name}")
    public String getAuthorByName(@PathParam("name") String name){
		return JsonHelper.INSTANCE.objectToJson(new AuthorDAOImpl().findAuthorByName(name));
    }
	
	@GET
	@Path("{id}")
	public String getAuthorById(@PathParam("id") Integer id) {
		return JsonHelper.INSTANCE.objectToJson(new AuthorDAOImpl().findAuthorById(id));
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
	@Path("{name}/books")
	public String getBooksByAuthorName(@PathParam("name") String name) {
		return JsonHelper.INSTANCE.objectToJson(new AuthorDAOImpl().findBooksByAuthor(name));
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
	@Path("{id}/books")
	public String getBooksByAuthorId(@PathParam("id") Integer id) {
		return JsonHelper.INSTANCE.objectToJson(new AuthorDAOImpl().findBooksByAuthor(id));
	}
	
}
