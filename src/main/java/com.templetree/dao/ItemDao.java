package com.templetree.dao;

import com.templetree.dao.intf.ItemDaoIntf;
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
 * Created by Lalith on 10/2/15.
 */

@Repository("itemDao")
public class ItemDao implements ItemDaoIntf {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Item getItemById(Integer id) {
        System.out.println("Entering ItemDao::getItemById");
        String sql = "select * from items where id = ?" ;
         Item item = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet resultSet, int i) throws SQLException {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setCategory(resultSet.getString("category"));
                item.setBarcode(resultSet.getString("barcode"));
                item.setItemName(resultSet.getString("itemName"));
                item.setSalesPrice(resultSet.getDouble("salesPrice"));
                item.setPurchasePrice(resultSet.getDouble("purchasePrice"));
                item.setCreatedDate(resultSet.getTimestamp("createdDate"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setUpdatedDate(resultSet.getTimestamp("updatedDate"));

                return item;

            }
        });

        return item;
    }

    @Override
    public List<Item> getAllItems() {
        String sql = "select * from items";

        List<Item> itemList = jdbcTemplate.query(sql, new RowMapper<Item>() {

            public Item mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setCategory(resultSet.getString("category"));
                item.setBarcode(resultSet.getString("barcode"));
                item.setItemName(resultSet.getString("itemName"));
                item.setSalesPrice(resultSet.getDouble("salesPrice"));
                item.setPurchasePrice(resultSet.getDouble("purchasePrice"));
                item.setCreatedDate(resultSet.getTimestamp("createdDate"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setUpdatedDate(resultSet.getTimestamp("updatedDate"));

                return item;
            }
        });

        return itemList;
    }

    @Override
    public void insertItems(List<Item> items) {

        List<Item> dbItems = getAllItems();
        // Removing items already present in the DB.
        items.removeAll(dbItems);
        final List<Item> newItems = items;

        String sql = "INSERT INTO ITEMS(barcode, category, itemName, salesPrice, purchasePrice, createdDate, quantity, updatedDate) VALUES (?,?,?,?,?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Item item = newItems.get(i);
                preparedStatement.setString(1, item.getBarcode());
                preparedStatement.setString(2, item.getCategory());
                preparedStatement.setString(3, item.getItemName());
                preparedStatement.setDouble(4, (item.getSalesPrice() == null ? 0 : item.getSalesPrice()));
                preparedStatement.setDouble(5, (item.getPurchasePrice() == null ? 0 : item.getPurchasePrice()));
                preparedStatement.setDate(6, new Date((new java.util.Date()).getTime()));
                preparedStatement.setInt(7, item.getQuantity() == null ? 0 : item.getQuantity());
                preparedStatement.setDate(8, new Date((new java.util.Date()).getTime()));
            }

            @Override
            public int getBatchSize() {
                return newItems.size();
            }
        });

        System.out.println("Number of Items inserted: " + newItems.size());
    }

    @Override
    public Item insertItem(Item item) {
        String sql = "INSERT INTO ITEMS(barcode, category, itemName, salesPrice, purchasePrice, createdDate, quantity, updatedDate) VALUES (?,?,?,?,?,?,?,?)";
        Integer id = jdbcTemplate.update(sql, new Object[]{item.getBarcode(), item.getCategory(), item.getItemName()
        , item.getSalesPrice(), item.getPurchasePrice(), item.getCreatedDate(), item.getQuantity(), item.getUpdatedDate()});

        return getItemById(id);
    }

    @Override
    public Item updateItem(Item item) {
        String sql = "UPDATE ITEMS SET barcode=?, category=?, itemName=?, salesPrice=?, purchasePrice=?, createdDate=?, quantity=?, updatedDate=? where id=?)";
        Integer id = jdbcTemplate.update(sql, new Object[]{item.getBarcode(), item.getCategory(), item.getItemName(),
                item.getSalesPrice(), item.getPurchasePrice(), item.getCreatedDate(), item.getQuantity(), item.getUpdatedDate(), item.getId()});

        return getItemById(id);
    }

    @Override
    public Item deleteItemById(Integer id) {
        return null;
    }
}
