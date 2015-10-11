package com.templetree.service.intf;

import com.templetree.model.Item;

import java.util.List;

/**
 * Created by Lalith on 10/4/15.
 */
public interface ItemWebServiceIntf {

    public List<Item> getAllItems();
    public Item getItemById(Integer id);
    public List<Item> createItems(List<Item> items);
    public void saveOrUpdateItems(List<Item> items);
    public Item updateItem(Item item);
    public void deleteItem(Integer id);
}
