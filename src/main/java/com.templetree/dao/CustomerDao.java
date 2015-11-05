package com.templetree.dao;

import com.templetree.dao.intf.CustomerDaoIntf;
import com.templetree.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Lalith Mannur
 */

@Repository("customerDao")
public class CustomerDao implements CustomerDaoIntf {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public Customer getCustomerById(Integer id) {
        return (Customer) getCurrentSession().get(Customer.class, id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return getCurrentSession().createCriteria(Customer.class).list();
    }

    @Override
    public List<Customer> insertCustomers(List<Customer> customers) {
        return null;
    }

    @Override
    public void saveOrUpdateCustomers(List<Customer> customers) {
        customers.forEach(customer -> getCurrentSession().merge(customer));
    }

    @Override
    public Integer insertCustomer(Customer customer) {
        return Integer.parseInt(getCurrentSession().save(customer).toString());
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customer.setUpdatedDate(new Timestamp(new Date().getTime()));
        getCurrentSession().update(customer);
        return getCustomerById(customer.getId());
    }

    @Override
    public void deleteCustomerById(Integer id) {
        getCurrentSession().delete(this.getCustomerById(id));
    }
}
