package com.templetree.service;

import com.templetree.dao.intf.ItemDaoIntf;
import com.templetree.model.Action;
import com.templetree.model.Item;
import com.templetree.service.intf.ItemWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Lalith Mannur
 */
@Service("itemWebService")
@Transactional
public class ItemWebService implements ItemWebServiceIntf {

    private static final Logger LOGGER = getLogger(ItemWebService.class);

    @Autowired
    private ItemDaoIntf itemDao;

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public Item getItemById(Integer id) {
        return itemDao.getItemById(id);
    }

    @Override
    public List<Item> createItems(List<Item> items) {
        return itemDao.insertItems(items);
    }

    @Override
    public void saveOrUpdateItems(List<Item> items) {

        List<Item> deletedItems = getAllItems();
        deletedItems.removeAll(items);

        for(Item item : deletedItems) {
            System.out.println("Deleting Item id : " + item.getId());
            itemDao.deleteItemById(item.getId());
        }

        itemDao.saveOrUpdateItems(items);
    }

    @Override
    public Item updateItem(Item item) {
        return itemDao.updateItem(item);
    }

    @Override
    public void updateItems(List<Item> itemList) {
        List<Item> deletedItems = itemList.stream()
                .filter(item -> item.getAction() == Action.D)
                .collect(Collectors.toList());
        List<Item> updatedItems = itemList.stream()
                .filter(item -> item.getAction() == Action.U)
                .collect(Collectors.toList());
        deletedItems.forEach(i -> itemDao.deleteItemById(i.getId()));
        itemDao.saveOrUpdateItems(updatedItems);
    }

    @Override
    public void deleteItem(Integer id) {
        itemDao.deleteItemById(id);
    }
}
