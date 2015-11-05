package com.templetree.service.intf;

import com.templetree.model.BillingInvoice;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface BillingInvoiceWebServiceIntf {
    public List<BillingInvoice> getAllBillingInvoices();
    public BillingInvoice getBillingInvoiceById(Integer id);
    public BillingInvoice createBillingInvoice(BillingInvoice billingInvoice);
}
