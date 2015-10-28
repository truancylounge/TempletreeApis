package com.templetree.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.templetree.model.Customer;
import com.templetree.service.intf.CustomerWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by Lalith on 10/27/15.
 */

@Path("/customers")
public class CustomerResource {

    private static final Logger LOGGER = getLogger(CustomerResource.class);

    @Autowired
    private CustomerWebServiceIntf customerWebService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {
        System.out.print("Inside GET All Customers");
        return customerWebService.getAllCustomers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("id") Integer id) {
        System.out.print("Inside GET Customer by Id: " + id);
        return customerWebService.getCustomerById(id);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Customer createCustomer(Customer customer) {
        System.out.print("Inside POST::createCustomer");
        return customerWebService.createCustomer(customer);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer updateCustomer(@PathParam("id") Integer id, Customer customer) {
        System.out.print("Inside PUT Customer. Updating Customer with id: " + customer.getId());
        //todo: error handling if id is not present
        // todo: rightnow if id not present it creates a new customer rather than throwing an error
        // todo: put should not be creating a new object
        // todo: changed code from merge() in dao to update() coz it was doing a POST when id wanst present
        // todo: check other resouces if this issue exists
        return customerWebService.updateCustomer(customer);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCustomer(@PathParam("id") Integer id) {
        System.out.print("Inside Delete Customer. Deleting Customer with id: " + id);
        customerWebService.deleteCustomer(id);
    }
}
