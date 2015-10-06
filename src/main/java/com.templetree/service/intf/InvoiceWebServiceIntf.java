package com.templetree.service.intf;

import com.templetree.model.Invoice;
import com.templetree.model.Item;

import java.util.List;

/**
 * Created by Lalith on 10/6/15.
 */
public interface InvoiceWebServiceIntf {
    public List<Invoice> getAllInvoices();
    public Invoice getInvoiceById(Integer id);
    public void createInvoice(Invoice item);
    public Invoice updateInvoice(Invoice item);
    public void deleteInvoice(Integer id);
}
