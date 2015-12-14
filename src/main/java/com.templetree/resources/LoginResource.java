package com.templetree.resources;

import com.templetree.exception.TempletreeException;
import com.templetree.model.Role;
import com.templetree.model.User;
import com.templetree.service.intf.LoginWebServiceIntf;
import com.templetree.utils.EncryptionUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Lalith on 12/5/15.
 */

@Path("/login")
public class LoginResource {
    private static final Logger LOGGER = getLogger(LoginResource.class);

    @Autowired
    @Qualifier("mainControllerProperties")
    public Properties appProperties;

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
        User userVo =  loginWebService.authenticateUser(user);
        userVo.setPassword("");
        if(userVo.getAuthenticated()) {
            try {
                String templetreeAuthToken = EncryptionUtil.generateAccessToken(userVo.getUsername(), userVo.getRole(), appProperties.getProperty("app.login.delimiter"), appProperties);
                userVo.setToken(templetreeAuthToken);

            } catch (TempletreeException ex ) {
                ex.printStackTrace();
            }

        }
        return userVo;

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User insertUser(User user) {
        System.out.println("Post Insert User");
        User userVo = loginWebService.insertUser(user);
        userVo.setPassword("");
        return userVo;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        System.out.println("Inside GET All Users");
        List<User> userVos = loginWebService.retrieveAllUsers();
        userVos.forEach(userVo -> userVo.setPassword(""));
        return userVos;

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
        User userVo = loginWebService.updateUser(user);
        userVo.setPassword("");
        return userVo;

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") Integer id) {
        System.out.print("Inside Delete User. Deleting User with id: " + id);
        loginWebService.deleteUser(id);
    }


}
