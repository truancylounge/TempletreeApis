package com.templetree.dao.intf;

import com.templetree.model.Item;

import java.util.List;

/**
 * Created by Lalith on 10/2/15.
 */
public interface ItemDaoIntf {
    public Item getItemById(Integer id);
    public Item getItemByBarcode(String barcode);
    public List<Item> getAllItems();
    public List<Item> insertItems(List<Item> items);
    public void saveOrUpdateItems(List<Item> items);
    public void insertItem(Item item);
    public Item updateItem(Item item);
    public void deleteItemById(Integer id);
}
