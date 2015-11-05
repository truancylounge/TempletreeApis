package com.templetree.dao;

import com.templetree.dao.intf.ItemDaoIntf;
import com.templetree.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lalith Mannur
 */

@Repository("itemDao")
public class ItemDao implements ItemDaoIntf {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Item getItemById(Integer id) {
        return (Item) getCurrentSession().get(Item.class, id);
    }

    @Override
    public Item getItemByBarcode(String barcode) {
        return (Item) getCurrentSession().createCriteria(Item.class)
                .add(Restrictions.eq("barcode", barcode))
                .uniqueResult();
    }

    @Override
    public List<Item> getAllItems() {
        return getCurrentSession().createCriteria(Item.class).list();
    }

    @Override
    public void saveOrUpdateItems(List<Item> items) {

        /*
        List<Item> deletedItems = getAllItems();
        deletedItems.removeAll(items);

        for(Item item : deletedItems) {
            System.out.println("Deleting Item id : " + item.getId());
            this.deleteItemById(item.getId());
        }

        */

        for(Item item : items) {
            getCurrentSession().merge(item);
        }
    }

    @Override
    public List<Item> insertItems(List<Item> items) {
        List<Item> dbItems = getAllItems();
        items.removeAll(dbItems);

        List<Item> returnedItems = new ArrayList<Item>();
        for(Item item : items) {
            Integer i = Integer.parseInt(getCurrentSession().save(item).toString());
            // Using evict to remove the session object and force Hibernate to do a select
            // Without evict, hibernate isn't doing a select for get() and returning the object in memory after insert
            getCurrentSession().evict(item);
            Item itemDb = (Item) getCurrentSession().get(Item.class, i);
            returnedItems.add(itemDb);
        }
        System.out.println("Number of Items inserted: " + items.size());

        return returnedItems;

    }

    @Override
    public void insertItem(Item item) {

    }

    @Override
    public Item updateItem(Item item) {
        return null;
    }

    @Override
    public void deleteItemById(Integer id) {
        getCurrentSession().delete(this.getItemById(id));
    }
}
