package com.templetree.dao.intf;

import com.templetree.model.Invoice;
import com.templetree.model.Item;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface InvoiceItemsDaoIntf {

    public List<Item> getInvoiceItemsByInvoiceId(Integer invoiceId);
    public void insertInvoiceItemsByInvoiceId(List<Item> items, Invoice invoice);

}
