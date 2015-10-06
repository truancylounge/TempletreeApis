package com.templetree.service.intf;

import com.templetree.model.Invoice;

import java.util.List;

/**
 * Created by Lalith on 10/6/15.
 */
public interface InvoicesExcelWebServiceIntf {
    public Invoice readExcel(String uploadFilePath, String filename);

}
