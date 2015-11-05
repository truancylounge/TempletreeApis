package com.templetree.resources;

import com.templetree.model.BillingInvoice;
import com.templetree.service.intf.BillingInvoiceWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Lalith Mannur
 */

@Path("/billingInvoices")
public class BillingInvoiceResource {

    private static final Logger LOGGER = getLogger(BillingInvoiceResource.class);

    @Autowired
    private BillingInvoiceWebServiceIntf billingInvoiceWebService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BillingInvoice> getAllBillingInvoices() {
        System.out.print("Inside GET All Billing Invoices");
        return billingInvoiceWebService.getAllBillingInvoices();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BillingInvoice getBillingInvoice(@PathParam("id") Integer id) {
        System.out.print("Inside GET Billing Invoice by Id: " + id);
        return billingInvoiceWebService.getBillingInvoiceById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public BillingInvoice createBillingInvoice(BillingInvoice billingInvoice) {
        System.out.print("Inside POST::createBillingInvoice");
        return billingInvoiceWebService.createBillingInvoice(billingInvoice);
    }
}
