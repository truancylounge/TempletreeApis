package com.templetree.dao.intf;

import com.templetree.model.Invoice;
import com.templetree.model.Item;

import java.util.List;

/**
 * Created by Lalith on 10/6/15.
 */
public interface InvoiceItemsDaoIntf {

    public List<Item> getInvoiceItemsByInvoiceId(Integer invoiceId);
    public void insertInvoiceItemsByInvoiceId(List<Item> items, Invoice invoice);

}
