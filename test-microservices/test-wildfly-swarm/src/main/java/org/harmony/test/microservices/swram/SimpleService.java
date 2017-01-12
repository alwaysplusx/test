package org.harmony.test.microservices.swram;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author wuxii@foxmail.com
 */
@Path("/")
@Stateless
public class SimpleService {

    @GET
    @Path("/")
    public String hello() {
        return "Hello World!";
    }

}
