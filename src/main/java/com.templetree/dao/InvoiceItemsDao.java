package com.templetree.dao;

import com.templetree.dao.intf.InvoiceItemsDaoIntf;
import com.templetree.model.Invoice;
import com.templetree.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lalith on 10/6/15.
 */
@Repository("invoiceItemsDao")
public class InvoiceItemsDao implements InvoiceItemsDaoIntf {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Item> getInvoiceItemsByInvoiceId(Integer invoiceId) {
        String sql = "select * from invoicesItems where invoiceId = ?";

        List<Item> itemList = jdbcTemplate.query(sql,new Object[]{invoiceId}, new RowMapper<Item>() {

            public Item mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Item itemDb = new Item();
                itemDb.setId(resultSet.getInt("id"));
                itemDb.setBarcode(resultSet.getString("barcode"));
                itemDb.setItemName(resultSet.getString("itemName"));
                itemDb.setSalesPrice(resultSet.getDouble("total"));
                itemDb.setPurchasePrice(resultSet.getDouble("purchasePrice"));
                itemDb.setCreatedDate(resultSet.getTimestamp("createdDate"));
                itemDb.setQuantity(resultSet.getInt("quantity"));
                itemDb.setUpdatedDate(resultSet.getTimestamp("updatedDate"));

                return itemDb;
            }
        });

        return itemList;
    }

    @Override
    public void insertInvoiceItemsByInvoiceId(List<Item> items, final Invoice invoice) {

        final List<Item> invoiceItems = items;

        String sql = "INSERT INTO invoicesItems(invoiceId, barcode, itemName, total, purchasePrice, createdDate, quantity, updatedDate) VALUES (?,?,?,?,?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Item item = invoiceItems.get(i);
                preparedStatement.setInt(1, invoice.getId());
                preparedStatement.setString(2, item.getBarcode());
                preparedStatement.setString(3, item.getItemName());
                preparedStatement.setDouble(4, (item.getTotal() == null ? 0 : item.getSalesPrice()));
                preparedStatement.setDouble(5, (item.getPurchasePrice() == null ? 0 : item.getPurchasePrice()));
                preparedStatement.setDate(6, new Date((new java.util.Date()).getTime()));
                preparedStatement.setInt(7, item.getQuantity() == null ? 0 : item.getQuantity());
                preparedStatement.setDate(8, new Date((new java.util.Date()).getTime()));
            }

            @Override
            public int getBatchSize() {
                return invoiceItems.size();
            }
        });

        System.out.println("Number of Invoice Items inserted: " + invoiceItems.size());

    }
}
