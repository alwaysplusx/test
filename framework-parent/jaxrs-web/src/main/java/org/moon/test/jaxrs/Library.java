package org.moon.test.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/library")
public class Library {

    @GET
    @Path("/books")
    public String books() {
        return "more and more";
    }

}
