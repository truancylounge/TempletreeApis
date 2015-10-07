package com.templetree.service;

import com.templetree.dao.intf.ItemDaoIntf;
import com.templetree.model.Item;
import com.templetree.service.intf.ItemWebServiceIntf;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Lalith on 10/4/15.
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
    public Item updateItem(Item item) {
        return itemDao.updateItem(item);
    }

    @Override
    public void deleteItem(Integer id) {
        itemDao.deleteItemById(id);
    }
}
