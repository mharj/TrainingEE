/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kurssi.rest;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import kurssi.ejb.ForumServicesEJB;

/**
 *
 * @author Oppi
 */
@Path("users")
public class RestTest {
	Gson gson = new Gson();
	@EJB ForumServicesEJB forumService;
	@GET
	@Produces("application/json")
	public Response getUser() {
		return Response.status(200).entity( gson.toJson( forumService.findAllUsers() ) ).build();
	}
}

