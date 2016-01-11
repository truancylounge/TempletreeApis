package com.templetree.service.intf;

import com.templetree.model.Invoice;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface InvoiceWebServiceIntf {
    public List<Invoice> getAllInvoices();
    public Boolean hasInvoiceBeenUploaded(String invoiceName);
    public Invoice getInvoiceById(Integer id);
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public void deleteInvoice(Invoice invoice);
}
