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

        Integer id = invoiceDao.insertInvoice(invoice);
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
