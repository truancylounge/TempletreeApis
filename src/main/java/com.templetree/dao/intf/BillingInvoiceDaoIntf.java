package com.templetree.dao.intf;

import com.templetree.model.BillingInvoice;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface BillingInvoiceDaoIntf {
    public BillingInvoice getBillingInvoiceById(Integer id);
    public List<BillingInvoice> getAllBillingInvoices();
    public Integer insertBillingInvoice(BillingInvoice billingInvoice);
}
