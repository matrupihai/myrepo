package com.weblib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.weblib.dao.BookDAOImpl;
import com.weblib.json.JsonHelper;

@Path ("/books")
@Produces (MediaType.APPLICATION_JSON)
public class BookResource {
	BookDAOImpl dao = new BookDAOImpl();
	
	@GET
	public String getAllBooks(@QueryParam ("title") String title) {
		if (title != null) {
			return JsonHelper.objectToJson(dao.findBookByTitle(title));
		}
		
		return JsonHelper.objectToJson(dao.getAllBooks());
	}
	
	@GET
	@Path ("{isbn}")
	public String getBookById(@PathParam("isbn") Integer isbn) {
		return JsonHelper.objectToJson(dao.findBookByIsbn(isbn));
	}
	
	@GET
	@Path ("{isbn}/copies")
	public String getBookCopies(@PathParam("isbn") Integer isbn) {
		return JsonHelper.objectToJson(dao.findBookCopies(isbn));
	}
	
}
