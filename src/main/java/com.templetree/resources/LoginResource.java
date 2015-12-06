package com.templetree.resources;

import com.templetree.model.Role;
import com.templetree.model.User;
import com.templetree.service.intf.LoginWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Lalith on 12/5/15.
 */

@Path("/login")
public class LoginResource {
    private static final Logger LOGGER = getLogger(LoginResource.class);

    @Autowired
    private LoginWebServiceIntf loginWebService;

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        LOGGER.info("Pinging Login Resouce");
        System.out.println("Pinging Login Resouce");
        return "Success!!";
    }

    @POST
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public User authenticateUser(User user) {
        System.out.println("Post Authenticate User");
        return loginWebService.authenticateUser(user);

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User insertUser(User user) {
        System.out.println("Post Insert User");
        return loginWebService.insertUser(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        System.out.println("Inside GET All Users");
        return loginWebService.retrieveAllUsers();
    }

    @GET
    @Path("/roles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Role> getAllRoles() {
        System.out.println("Inside GET All Roles");
        return loginWebService.getAllRoles();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        System.out.println("Inside PUT User");
        return loginWebService.updateUser(user);

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") Integer id) {
        System.out.print("Inside Delete User. Deleting User with id: " + id);
        loginWebService.deleteUser(id);
    }


}
