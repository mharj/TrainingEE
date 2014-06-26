/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kurssi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Oppi
 */
@Path("users")
public class RestTest {
	@Path("user")
	@GET
	@Produces("text/plain")
	public Response getUser() {
		return Response.status(200).entity("getUser is called").build();
	}
}

