package com.templetree.resources;

import com.templetree.model.Customer;
import com.templetree.service.intf.CustomerWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * @author Lalith Mannur
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

    /**
     * This method takes in new customers, customers to be edited, customers to be deleted
     *
     * @param customers
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void createModifyCustomers(List<Customer> customers) {
        System.out.print("Inside POST::createCustomers");
        customerWebService.createCustomers(customers);
        //customerWebService.saveOrUpdateCustomers(customers);
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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateCustomers(List<Customer> customerList) {
        System.out.println("Inside PUT Customers. Number of Customers being updated : " + customerList.size());
        customerWebService.updateCustomers(customerList);
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCustomer(@PathParam("id") Integer id) {
        System.out.print("Inside Delete Customer. Deleting Customer with id: " + id);
        customerWebService.deleteCustomer(id);
    }
}
