package com.templetree.service.intf;

import com.templetree.model.Item;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface ItemWebServiceIntf {

    public List<Item> getAllItems();
    public Item getItemById(Integer id);
    public List<Item> createItems(List<Item> items);
    public void saveOrUpdateItems(List<Item> items);
    public Item updateItem(Item item);
    public void updateItems(List<Item> itemList);
    public void deleteItem(Integer id);
}
