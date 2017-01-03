package org.moon.test.jaxrs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.moon.test.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@Produces("text/xml")
public class UserService {

    Map<Long, User> users = new HashMap<Long, User>();
    static Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService() {
        init();
    }

    @GET
    @Path("/users/{id}")
    public User getUser(@PathParam("id") Long id) {
        log.info("get user, id:{}", id);
        return users.get(id);
    }

    @POST
    @Path("/users/")
    public Response addUser(User user) {
        log.info("add user, user:{}", user);
        users.put(user.getId(), user);
        return Response.ok().build();
    }

    @DELETE
    @Path("/users/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        if (users.containsKey(id)) {
            users.remove(id);
            log.info("remove user, id:{}", id);
            return Response.ok().build();
        } else {
            log.info("user not exist,id {}", id);
            return Response.notModified().build();
        }
    }

    @PUT
    @Path("/users/")
    public Response updateUser(User user) {
        Long id = user.getId();
        if (users.containsKey(id)) {
            users.put(id, user);
            log.info("update user, user:{}", user);
            return Response.ok().build();
        }
        log.info("user not exist, user:{}", user);
        return Response.notModified().build();
    }

    private void init() {
        users.put(0l, new User(0l, "user_0", 23));
        users.put(1l, new User(1l, "wuxii", 23));
    }

}
