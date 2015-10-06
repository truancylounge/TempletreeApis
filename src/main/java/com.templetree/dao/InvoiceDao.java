package com.templetree.dao;

import com.templetree.dao.intf.InvoiceDaoIntf;
import com.templetree.model.Invoice;
import com.templetree.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lalith on 10/6/15.
 */

@Repository("invoiceDao")
public class InvoiceDao implements InvoiceDaoIntf {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Invoice getInvoiceById(Integer id) {


        System.out.println("Entering InvoiceDao::getInvoiceById");
        String sql = "select * from invoices where id = ?" ;
        Invoice invoice = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Invoice>() {
            @Override
            public Invoice mapRow(ResultSet resultSet, int i) throws SQLException {
                Invoice invoiceDb = new Invoice();
                invoiceDb.setId(resultSet.getInt("id"));
                invoiceDb.setInvoiceName(resultSet.getString("invoicename"));
                invoiceDb.setSubTotal(resultSet.getDouble("subTotal"));
                invoiceDb.setShipping(resultSet.getDouble("shipping"));
                invoiceDb.setPacking(resultSet.getDouble("packing"));
                invoiceDb.setGrandTotal(resultSet.getDouble("grandTotal"));
                invoiceDb.setCreatedDate(resultSet.getTimestamp("createdDate"));
                invoiceDb.setUpdatedDate(resultSet.getTimestamp("updatedDate"));

                return invoiceDb;

            }
        });

        return invoice;
    }

    @Override
    public List<Invoice> getAllInvoices() {

        String sql = "select * from invoices";

        List<Invoice> invoiceList = jdbcTemplate.query(sql, new RowMapper<Invoice>() {

            public Invoice mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Invoice invoiceDb = new Invoice();
                invoiceDb.setId(resultSet.getInt("id"));
                invoiceDb.setInvoiceName(resultSet.getString("invoiceName"));
                invoiceDb.setSubTotal(resultSet.getDouble("subTotal"));
                invoiceDb.setShipping(resultSet.getDouble("shipping"));
                invoiceDb.setPacking(resultSet.getDouble("packing"));
                invoiceDb.setGrandTotal(resultSet.getDouble("grandTotal"));
                invoiceDb.setCreatedDate(resultSet.getTimestamp("createdDate"));
                invoiceDb.setUpdatedDate(resultSet.getTimestamp("updatedDate"));

                return invoiceDb;
            }
        });

        return invoiceList;
    }

    @Override
    public void insertInvoice(List<Invoice> invoices) {

    }

    @Override
    public void insertInvoice(Invoice invoice) {

        KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO INVOICES (invoiceName, subTotal, shipping, packing, grandTotal, createdDate, updatedDate) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{invoice.getInvoiceName(), invoice.getSubTotal(), invoice.getShipping(),
                invoice.getPacking(), invoice.getGrandTotal(), new Date((new java.util.Date()).getTime()), new Date((new java.util.Date()).getTime())});

        List<Item> items = invoice.getItemList();


    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public void deleteInvoiceById(Integer id) {

    }
}
