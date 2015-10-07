package com.templetree.dao;

import com.templetree.dao.intf.InvoiceItemsDaoIntf;
import com.templetree.model.Invoice;
import com.templetree.model.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lalith on 10/6/15.
 */
@Repository("invoiceItemsDao")
public class InvoiceItemsDao implements InvoiceItemsDaoIntf {

    @Override
    public List<Item> getInvoiceItemsByInvoiceId(Integer invoiceId) {
        return null;
    }

    @Override
    public void insertInvoiceItemsByInvoiceId(List<Item> items, Invoice invoice) {

    }
}
