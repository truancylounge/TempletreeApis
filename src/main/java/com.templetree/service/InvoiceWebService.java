package com.templetree.service;

import com.templetree.dao.intf.InvoiceDaoIntf;
import com.templetree.dao.intf.InvoiceItemsDaoIntf;
import com.templetree.model.Invoice;
import com.templetree.service.intf.InvoiceWebServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
