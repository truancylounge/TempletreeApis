package com.templetree.dao;

import com.templetree.dao.intf.BillingInvoiceDaoIntf;
import com.templetree.model.BillingInvoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lalith Mannur
 */

@Repository("billingInvoiceDao")
public class BillingInvoiceDao implements BillingInvoiceDaoIntf {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public BillingInvoice getBillingInvoiceById(Integer id) {
        return (BillingInvoice) getCurrentSession().get(BillingInvoice.class, id);
    }

    @Override
    public List<BillingInvoice> getAllBillingInvoices() {
        return getCurrentSession().createCriteria(BillingInvoice.class).list();
    }

    @Override
    public Integer insertBillingInvoice(BillingInvoice billingInvoice) {
        return Integer.parseInt(getCurrentSession().save(billingInvoice).toString());
    }
}
