package com.templetree.service.intf;

import com.templetree.model.Invoice;

/**
 * @author Lalith Mannur
 */
public interface InvoicesExcelWebServiceIntf {
    public Invoice readExcel(String uploadFilePath, String filename);

}
