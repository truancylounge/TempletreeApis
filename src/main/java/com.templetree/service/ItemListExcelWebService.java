package com.templetree.service;

import com.templetree.dao.intf.ItemDaoIntf;
import com.templetree.model.Item;
import com.templetree.service.intf.ExcelWebServiceIntf;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lalith on 10/1/15.
 */

@Service("itemListExcelWebService")
public class ItemListExcelWebService implements ExcelWebServiceIntf {

    @Autowired
    ItemDaoIntf itemDao;

    public ItemListExcelWebService() {

    }

    @Override
    public List<Item> readExcel(String filename) {

        List<Item> items = new ArrayList<Item>();
        try{
            FileInputStream file = new FileInputStream(filename);
            // Use XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(file);
            int numberOfSheets = workbook.getNumberOfSheets();
            System.out.println("Importing sheet : " + workbook.getSheetName(0));

            Sheet itemMasterSheet = workbook.getSheetAt(0);
            for (Row myRow : itemMasterSheet) {
                Item item = new Item();
                if(myRow.getRowNum()==0) {
                    continue; //just skip the rows if row number is 0
                }

                for (Cell myCell : myRow) {
                    switch (myCell.getColumnIndex()) {
                        case 0:
                            item.setCategory(myCell.getRichStringCellValue().getString().trim());
                            break;
                        case 1:
                            item.setBarcode(myCell.getRichStringCellValue().getString().trim());
                            break;
                        case 2:
                            item.setItemName(myCell.getRichStringCellValue().getString().trim());
                            break;
                        case 3:
                            if (myCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                                item.setSalesPrice(myCell.getNumericCellValue());
                            else
                                item.setSalesPrice(Double.parseDouble(myCell.getStringCellValue().trim()));
                            break;
                        case 4:
                            break;
                        case 5:
                            if (myCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                                item.setPurchasePrice(myCell.getNumericCellValue());
                            else
                                item.setPurchasePrice(Double.parseDouble(myCell.getStringCellValue().trim()));
                            break;
                    }
                }
                items.add(item);
            }

            file.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        itemDao.insertItems(items);

        return itemDao.getAllItems();
    }

}
