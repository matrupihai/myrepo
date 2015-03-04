package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.weblib.dao.BookDAOImpl;

@Path ("/books")
public class BookResource {
	BookDAOImpl dao = new BookDAOImpl();
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getAllBooks(@QueryParam ("title") String title) {
		if (title != null) {
			return Response.ok(dao.findBookByTitle(title)).build();
		}
		
		return Response.ok(dao.getAllBooks()).build();
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path ("{isbn}")
	public Response getBookById(@PathParam("isbn") Integer isbn) {
		return Response.ok(dao.findBookByIsbn(isbn)).build();
	}
	
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Path ("{isbn}/copies")
	public Response getBookCopies(@PathParam("isbn") Integer isbn) {
		return Response.ok(dao.findBookCopies(isbn)).build();
	}
	
}
