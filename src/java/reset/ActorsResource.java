/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reset;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Ivan
 */
@Path("actors/{actor_id}")
public class ActorsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of actors
     */
    public ActorsResource() {
    }

    /**
     * Retrieves representation of an instance of reset.actors
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("actor_id") String actor_id) {
        //TODO return proper representation object
        
        return "{'id ':'" + actor_id +"','name ':' Chus Lampreave'}";
//        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of actors
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
