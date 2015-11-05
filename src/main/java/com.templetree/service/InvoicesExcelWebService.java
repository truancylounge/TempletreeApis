package com.templetree.service;

import com.templetree.model.Invoice;
import com.templetree.model.InvoicesItems;
import com.templetree.service.intf.InvoiceWebServiceIntf;
import com.templetree.service.intf.InvoicesExcelWebServiceIntf;
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
 * @author Lalith Mannur
 */

@Service("invoicesExcelWebService")
public class InvoicesExcelWebService implements InvoicesExcelWebServiceIntf {

    @Autowired
    private InvoiceWebServiceIntf invoiceWebService;

    @Override
    public Invoice readExcel(String uploadFilePath, String filename) {

        System.out.println("Inside Read Excel method for Invoice : " + filename);

        Invoice invoice = new Invoice();
        List<InvoicesItems> invoicesItemsList = new ArrayList<InvoicesItems>();
        try {

            FileInputStream file = new FileInputStream(uploadFilePath);
            // Use XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(file);
            System.out.println("Reading workbook : " + filename);
            System.out.println("Importing sheet : " + workbook.getSheetName(0));

            invoice.setInvoiceName(filename);
            Sheet invoiceMasterSheet = workbook.getSheetAt(0);

            for(Row myRow : invoiceMasterSheet) {

                InvoicesItems invoicesItems = new InvoicesItems();
                invoicesItems.setInvoice(invoice);
                if(myRow.getRowNum()==0 || myRow.getRowNum()==1 || myRow.getRowNum()==2) {
                    continue; //just skip the rows if row number is 0, 1 or 2
                }
                // If Row is empty we have reached the end of items list.
                // We will run through the sheet again to capture invoice attributes later.
                if(isRowEmpty(myRow)) {
                    break;

                }

                for(Cell myCell : myRow) {

                    switch (myCell.getColumnIndex()) {
                        case 0:
                            break;
                        case 1:
                            invoicesItems.setItemName(myCell.getRichStringCellValue().getString().trim());
                            break;
                        case 2:
                            invoicesItems.setBarcode(myCell.getRichStringCellValue().getString().trim());
                            break;
                        case 3:
                            if (myCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                                invoicesItems.setQuantity(Integer.valueOf((int) myCell.getNumericCellValue()));
                            else
                                invoicesItems.setQuantity(Integer.parseInt(myCell.getStringCellValue().trim()));
                            break;
                        case 4:
                            if (myCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                                invoicesItems.setPurchasePrice(myCell.getNumericCellValue());
                            else
                                invoicesItems.setPurchasePrice(Double.parseDouble(myCell.getStringCellValue().trim()));
                            break;
                    }
                }
                invoicesItems.setTotal(invoicesItems.getQuantity() * invoicesItems.getPurchasePrice());
                invoicesItemsList.add(invoicesItems);
            }
            addInvoiceAttributes(invoiceMasterSheet, invoice);
            file.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        invoice.setInvoicesItemsList(invoicesItemsList);
        invoiceWebService.createInvoice(invoice);

        return invoice;
    }

    private Boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
                return false;
        }
        return true;
    }

    private void addInvoiceAttributes(Sheet invoiceMasterSheet, Invoice invoice) {

        for(Row myRow : invoiceMasterSheet) {

            if (isRowEmpty(myRow)) {
                continue;
            }
            if (myRow.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && myRow.getCell(4).getStringCellValue().equals("Sub Total")) {
                invoice.setSubTotal(myRow.getCell(5).getNumericCellValue());
            }

            if (myRow.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && myRow.getCell(4).getStringCellValue().equals("Shipping")) {
                invoice.setShipping(myRow.getCell(5).getNumericCellValue());
            }

            if (myRow.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && myRow.getCell(4).getStringCellValue().equals("Packing")) {
                invoice.setPacking(myRow.getCell(5).getNumericCellValue());
            }

            if (myRow.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && myRow.getCell(4).getStringCellValue().equals("Grand Total")) {
                invoice.setGrandTotal(myRow.getCell(5).getNumericCellValue());
            }
        }
    }
}
