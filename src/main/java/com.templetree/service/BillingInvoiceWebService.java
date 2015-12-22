package com.templetree.service;

import com.templetree.dao.intf.BillingInvoiceDaoIntf;
import com.templetree.dao.intf.ItemDaoIntf;
import com.templetree.model.BillingInvoice;
import com.templetree.model.BillingInvoicesItems;
import com.templetree.model.Item;
import com.templetree.service.intf.BillingInvoiceWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Lalith Mannur
 */

@Service("billingInvoiceWebService")
@Transactional
public class BillingInvoiceWebService implements BillingInvoiceWebServiceIntf {

    private static final Logger LOGGER = getLogger(BillingInvoiceWebService.class);

    @Autowired
    private BillingInvoiceDaoIntf billingInvoiceDao;

    @Autowired
    private ItemDaoIntf itemDao;

    @Override
    public List<BillingInvoice> getAllBillingInvoices() {
        return billingInvoiceDao.getAllBillingInvoices();
    }

    @Override
    public BillingInvoice getBillingInvoiceById(Integer id) {
        return billingInvoiceDao.getBillingInvoiceById(id);
    }

    @Override
    public BillingInvoice createBillingInvoice(BillingInvoice billingInvoice) {
        billingInvoice.setInvoiceName("InvoiceNumber-" + System.currentTimeMillis());
        billingInvoice.setCreatedDate(new Timestamp(new Date().getTime()));
        billingInvoice.setUpdatedDate(new Timestamp(new Date().getTime()));

        List<BillingInvoicesItems> billingInvoicesItemsList = billingInvoice.getBillingInvoicesItemsList();
        List<Item> itemList = new ArrayList<>();
        billingInvoicesItemsList.forEach(billingItem -> {
            Item item = itemDao.getItemByBarcode(billingItem.getBarcode());
            item.minusQuantity(billingItem.getQuantity());
            itemList.add(item);
        });

        itemDao.saveOrUpdateItems(itemList);

        Integer id = billingInvoiceDao.insertBillingInvoice(billingInvoice);
        return getBillingInvoiceById(id);
    }
}
