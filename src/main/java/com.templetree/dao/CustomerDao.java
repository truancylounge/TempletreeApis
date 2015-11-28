package com.templetree.dao;

import com.templetree.dao.intf.CustomerDaoIntf;
import com.templetree.model.Customer;
import com.templetree.model.Item;
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

        List<Customer> returnedCustomers = new ArrayList<>();
        for(Customer customer : customers) {
            customer.setCreatedDate(new Timestamp(new Date().getTime()));
            customer.setUpdatedDate(new Timestamp(new Date().getTime()));
            Integer i = Integer.parseInt(getCurrentSession().save(customer).toString());
            // Using evict to remove the session object and force Hibernate to do a select
            // Without evict, hibernate isn't doing a select for get() and returning the object in memory after insert
            getCurrentSession().evict(customer);
            Customer customerDb = (Customer) getCurrentSession().get(Customer.class, i);
            returnedCustomers.add(customerDb);
        }
        System.out.println("Number of Customers inserted: " + customers.size());

        return returnedCustomers;
    }

    @Override
    public void saveOrUpdateCustomers(List<Customer> customers) {
        customers.forEach(customer -> {
            customer.setUpdatedDate(new Timestamp(new Date().getTime()));
            getCurrentSession().merge(customer);
        });
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
