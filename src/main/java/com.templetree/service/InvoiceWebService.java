package com.templetree.service;

import com.templetree.dao.intf.InvoiceDaoIntf;
import com.templetree.dao.intf.InvoiceItemsDaoIntf;
import com.templetree.dao.intf.ItemDaoIntf;
import com.templetree.model.Invoice;
import com.templetree.model.InvoicesItems;
import com.templetree.model.Item;
import com.templetree.service.intf.InvoiceWebServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lalith on 10/6/15.
 */

@Service("invoiceWebService")
@Transactional
public class InvoiceWebService implements InvoiceWebServiceIntf {


    @Autowired
    private InvoiceDaoIntf invoiceDao;

    @Autowired
    private ItemDaoIntf itemDao;

    @Autowired
    private InvoiceItemsDaoIntf invoiceItemsDao;


    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDao.getAllInvoices();
    }

    @Override
    public Invoice getInvoiceById(Integer id) {
        return invoiceDao.getInvoiceById(id);
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        List<Item> items = itemDao.getAllItems();

        List<Item> itemsToBeCreated = new ArrayList<Item>();

        List<InvoicesItems> invoicesItemsList = invoice.getInvoicesItemsList();

        for(InvoicesItems invoicesItems : invoicesItemsList) {
            String barcode = invoicesItems.getBarcode();
            Boolean barcodeExists = Boolean.FALSE;
            for(Item item : items) {
                if(item.getBarcode().equals(barcode))
                    barcodeExists = Boolean.TRUE;
            }

            if(!barcodeExists)
                itemsToBeCreated.add(new Item(invoicesItems.getBarcode(), invoicesItems.getItemName(), invoicesItems.getPurchasePrice(),
                        invoicesItems.getQuantity()));
        }

        itemDao.insertItems(itemsToBeCreated);

        List<Item> itemsToBeUpdated = new ArrayList<Item>(); // List of items whose quantity will be updated.

        // Iterate through Invoice Items and add quantity to master items
        for(InvoicesItems invoicesItems : invoicesItemsList) {
            Item item = itemDao.getItemByBarcode(invoicesItems.getBarcode());
            item.addQuantity(invoicesItems.getQuantity());
            itemsToBeUpdated.add(item);
        }

        // Removing itemsCreated from this collection, if not the qty is getting updated twice, once during creation
        // and again during saveUpdate.
        itemsToBeUpdated.removeAll(itemsToBeCreated);
        itemDao.saveOrUpdateItems(itemsToBeUpdated);

        Integer id = invoiceDao.insertInvoice(invoice);

        // todo : forloops to be deleted
        for(Item item : itemsToBeCreated) {
            System.out.println("Creating new products from invoice barcode: " + item.getBarcode());
        }
        for(Item item : itemsToBeUpdated) {
            System.out.println("Addding quantity: "  + item.getQuantity() +  " to barcode : " + item.getBarcode());
        }
        return invoiceDao.getInvoiceById(id);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceDao.updateInvoice(invoice);
    }

    @Override
    public void deleteInvoice(Integer id) {
        invoiceDao.deleteInvoiceById(id);
    }
}
