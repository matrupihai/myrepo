package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.weblib.dao.AuthorDAOImpl;
import com.weblib.json.JsonCreator;

@Path("/authors")
public class AuthorResource {
	
//	@GET
//    @Path("{name}")
//    public String sayHello(@PathParam("name") String name){
//        StringBuilder stringBuilder = new StringBuilder("Hello ");
//        stringBuilder.append(name).append("!");
//
//        return stringBuilder.toString();
//    }

	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public String getAuthors() {
		return JsonCreator.INSTANCE.objectToJson(new AuthorDAOImpl().findAll());
	}
}
