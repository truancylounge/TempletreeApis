package com.templetree.service.intf;

import com.templetree.model.Item;

import java.util.List;

/**
 * @author Lalith Mannur
 */
public interface ExcelWebServiceIntf {
    public List<Item> readExcel(String filename);
}
