package com.templetree.dao.intf;

import com.templetree.model.Item;
import com.templetree.model.User;

import java.util.List;

/**
 * Created by Lalith on 10/2/15.
 */
public interface ItemDaoIntf {
    public Item getItemById(Integer id);
    public List<Item> getAllItems();
    public void insertItems(List<Item> items);
    public void insertItem(Item item);
    public Item updateItem(Item item);
    public void deleteItemById(Integer id);
}
