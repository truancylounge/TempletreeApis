package com.templetree.dao.intf;

import com.templetree.model.Customer;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface CustomerDaoIntf {
    public Customer getCustomerById(Integer id);
    public List<Customer> getAllCustomers();
    public List<Customer> insertCustomers(List<Customer> customers);
    public void saveOrUpdateCustomers(List<Customer> customers);
    public Integer insertCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public void deleteCustomerById(Integer id);
}
