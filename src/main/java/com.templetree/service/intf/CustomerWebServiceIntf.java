package com.templetree.service.intf;


import com.templetree.model.Customer;

import java.util.List;

/**
 * Created by Lalith on 10/27/15.
 */
public interface CustomerWebServiceIntf {
    public List<Customer> getAllCustomers();
    public Customer getCustomerById(Integer id);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public void deleteCustomer(Integer id);
}
