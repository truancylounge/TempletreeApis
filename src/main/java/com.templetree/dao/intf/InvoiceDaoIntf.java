package com.templetree.dao.intf;



import com.templetree.model.Invoice;

import java.util.List;

/**
 * Created by Lalith on 10/6/15.
 */
public interface InvoiceDaoIntf {
    public Invoice getInvoiceById(Integer id);
    public List<Invoice> getAllInvoices();
    public void insertInvoice(List<Invoice> invoices);
    public void insertInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public void deleteInvoiceById(Integer id);
}
