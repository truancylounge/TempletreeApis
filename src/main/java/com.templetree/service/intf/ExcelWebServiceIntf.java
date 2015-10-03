package com.templetree.service.intf;

import com.templetree.model.Item;
import com.templetree.model.User;

import java.util.List;

/**
 * Created by Lalith on 10/1/15.
 */
public interface ExcelWebServiceIntf {
    public List<Item> readExcel(String filename);
}
