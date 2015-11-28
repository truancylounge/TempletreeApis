package com.templetree.resources;

import com.templetree.model.Item;
import com.templetree.service.intf.ItemWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * @author Lalith Mannur
 */


@Path("/items")
public class ItemResource {
    private static final Logger LOGGER = getLogger(ItemResource.class);

    @Autowired
    private ItemWebServiceIntf itemWebService;

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
        System.out.println("Inside GET for Ping");
        return "Success!!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getAllItems() {
        System.out.println("Inside GET All Items");
        return itemWebService.getAllItems();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItem(@PathParam("id") Integer id) {
        System.out.println("Inside GET Item by Id: " + id);
        return itemWebService.getItemById(id);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void createItems(List<Item> itemList) {
        System.out.println("Inside POST::createItems");
        itemWebService.createItems(itemList);
        //return itemWebService.createItems(itemList);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item updateItem(@PathParam("id") Integer id, Item item) {
        System.out.print("Inside PUT Item. Updating Item with barcode: " + item.getBarcode());
        return itemWebService.updateItem(item);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateItems(List<Item> itemList) {
        System.out.println("Inside PUT Items. Number of items being updated : " + itemList.size());
        itemWebService.updateItems(itemList);
    }



}
