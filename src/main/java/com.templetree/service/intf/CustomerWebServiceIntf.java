package com.templetree.service.intf;


import com.templetree.model.Customer;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface CustomerWebServiceIntf {
    public List<Customer> getAllCustomers();
    public Customer getCustomerById(Integer id);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public void saveOrUpdateCustomers(List<Customer> customers);
    public void deleteCustomer(Integer id);
}
