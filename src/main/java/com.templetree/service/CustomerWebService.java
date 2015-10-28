package com.templetree.service;

import com.templetree.dao.intf.CustomerDaoIntf;
import com.templetree.model.Customer;
import com.templetree.service.intf.CustomerWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Lalith on 10/27/15.
 */

@Service("customerWebService")
@Transactional
public class CustomerWebService implements CustomerWebServiceIntf {

    private static final Logger LOGGER = getLogger(CustomerWebService.class);

    @Autowired
    private CustomerDaoIntf customerDao;

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setCreatedDate(new Timestamp(new Date().getTime()));
        customer.setUpdatedDate(new Timestamp(new Date().getTime()));
        Integer id =  customerDao.insertCustomer(customer);
        return customerDao.getCustomerById(id);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customer.setUpdatedDate(new Timestamp(new Date().getTime()));
        return customerDao.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerDao.deleteCustomerById(id);
    }
}
