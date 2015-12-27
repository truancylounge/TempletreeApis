package com.templetree.dao;

import com.templetree.dao.intf.InvoiceDaoIntf;
import com.templetree.model.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lalith Mannur
 */

@Repository("invoiceDao")
public class InvoiceDao implements InvoiceDaoIntf {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Invoice getInvoiceById(Integer id) {
        return (Invoice) getCurrentSession().get(Invoice.class, id);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return getCurrentSession().createCriteria(Invoice.class).list();
    }

    @Override
    public List<Invoice> insertInvoice(List<Invoice> invoices) {
        List<Invoice> returnedInvoices = new ArrayList<Invoice>();
        for(Invoice invoice : invoices) {
            Integer i = Integer.parseInt(getCurrentSession().save(invoice).toString());
            // Using evict to remove the session object and force Hibernate to do a select
            // Without evict, hibernate isn't doing a select for get() and returning the object in memory after insert
            getCurrentSession().evict(invoice);
            Invoice invoiceDb = (Invoice) getCurrentSession().get(Invoice.class, i);
            returnedInvoices.add(invoiceDb);
        }

        return returnedInvoices;
    }

    @Override
    public Integer insertInvoice(Invoice invoice) {
        invoice.setCreatedDate(new Timestamp(new Date().getTime()));
        invoice.setUpdatedDate(new Timestamp(new Date().getTime()));
        return Integer.parseInt(getCurrentSession().save(invoice).toString());
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        invoice.setUpdatedDate(new Timestamp(new Date().getTime()));
        getCurrentSession().merge(invoice);
        return  getInvoiceById(invoice.getId());
    }


    @Override
    public void deleteInvoiceById(Invoice invoice) {
        getCurrentSession().delete(invoice);

    }
}
