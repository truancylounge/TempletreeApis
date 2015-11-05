package com.templetree.dao.intf;



import com.templetree.model.Invoice;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface InvoiceDaoIntf {
    public Invoice getInvoiceById(Integer id);
    public List<Invoice> getAllInvoices();
    public List<Invoice> insertInvoice(List<Invoice> invoices);
    public Integer insertInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public void deleteInvoiceById(Integer id);
}
