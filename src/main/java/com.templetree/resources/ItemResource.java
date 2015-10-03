package com.templetree.resources;

import com.templetree.model.User;
import com.templetree.service.ItemListExcelWebService;
import com.templetree.service.intf.ExcelWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * Created by Lalith on 10/1/15.
 */


@Path("/items")
public class ItemResource {
    private static final Logger LOGGER = getLogger(ItemResource.class);

    @Autowired
    private ExcelWebServiceIntf itemListExcelWebService;

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
        LOGGER.info("Inside GET for Ping");
        System.out.print("Inside GET for Ping");
        return "Success!!";
    }

}
