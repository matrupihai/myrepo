package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.weblib.dao.UserDAOImpl;
import com.weblib.json.JsonHelper;

@Path("/users")
public class UserResource {
	UserDAOImpl dao = new UserDAOImpl();
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public String getAllUsers(@QueryParam ("title") String userName) {
		if (userName != null) {
			return JsonHelper.objectToJson(dao.findByName(userName));
		}
		
		return JsonHelper.objectToJson(dao.findAllUsers());
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path ("{id}")
	public String getUserById(@PathParam("id") Integer id) {
		return JsonHelper.objectToJson(dao.findUserById(id));
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path ("{id}/loans")
	public String getLoansByUserId(@PathParam("id") Integer id) {
		return JsonHelper.objectToJson(dao.findBooksBorrowedByUser(id));
	}
	
}
