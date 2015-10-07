package com.templetree.service.intf;

import com.templetree.model.Invoice;

import java.util.List;

/**
 * Created by Lalith on 10/6/15.
 */
public interface InvoiceWebServiceIntf {
    public List<Invoice> getAllInvoices();
    public Invoice getInvoiceById(Integer id);
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public void deleteInvoice(Integer id);
}
