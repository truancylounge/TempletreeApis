package com.templetree.resources;

import com.templetree.model.Invoice;
import com.templetree.service.intf.InvoiceWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by Lalith on 10/6/15.
 */
@Path("/invoices")
public class InvoiceResource {
    private static final Logger LOGGER = getLogger(ItemResource.class);

    @Autowired
    private InvoiceWebServiceIntf invoiceWebService;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invoice> getAllInvoices() {
        System.out.print("Inside GET All Invoices");
        return invoiceWebService.getAllInvoices();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Invoice getInvoice(@PathParam("id") Integer id) {
        System.out.print("Inside GET Invoice by Id: " + id);
        return invoiceWebService.getInvoiceById(id);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Invoice createInvoice(Invoice invoice) {
        System.out.print("Inside POST::createInvoice");
        return invoiceWebService.createInvoice(invoice);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Invoice updateItem(@PathParam("id") Integer id, Invoice invoice) {
        System.out.print("Inside PUT Item. Updating Invoice with id: " + invoice.getId());
        return invoiceWebService.updateInvoice(invoice);
    }
}
