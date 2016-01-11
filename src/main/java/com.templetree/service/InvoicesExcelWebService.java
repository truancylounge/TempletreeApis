package com.templetree.service;

import com.templetree.exception.ExceptionMessages;
import com.templetree.exception.TempletreeException;
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

import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public Invoice readExcel(String uploadFilePath, String filename) throws TempletreeException {

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

            if(invoiceWebService.hasInvoiceBeenUploaded(filename)) {
                throw new TempletreeException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "ExcelParserError", ExceptionMessages.INVOICE_ALREADY_EXISTS,
                        filename + ":" + ExceptionMessages.INVOICE_ALREADY_EXISTS);
            }

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
            //throw new TempletreeException(ExceptionMessages.FILE_READ_ERROR, ex);
            System.out.println("Error parsing Invoice File: " + ex.getMessage());
            throw new TempletreeException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "ExcelParserError", ExceptionMessages.FILE_READ_ERROR,
                    ex.getMessage());
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
            // Retrieve the InvoiceDate and Invoice Number from first row.
            if(myRow.getRowNum()==0 && myRow.getCell(7).getCellType() == Cell.CELL_TYPE_STRING ) {
                String invoiceNumber = myRow.getCell(7).getStringCellValue();
                String[] str = invoiceNumber.split("\\n");

                invoice.setInvoiceNumber(str[1].substring(str[1].lastIndexOf(":") + 1).trim());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy");
                try {
                    invoice.setInvoiceDate(new Timestamp(dateFormat.parse(str[0].substring(str[0].lastIndexOf(":") + 1).trim()).getTime()));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }

            if (isRowEmpty(myRow)) {
                continue;
            }

            if (myRow.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && myRow.getCell(4).getStringCellValue().trim().toUpperCase().equals("SUB TOTAL")) {
                invoice.setSubTotal(myRow.getCell(7).getNumericCellValue());
            }

            if (myRow.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && myRow.getCell(4).getStringCellValue().trim().toUpperCase().equals("SHIPPING")) {
                invoice.setShipping(myRow.getCell(7).getNumericCellValue());
            }

            if (myRow.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && myRow.getCell(4).getStringCellValue().trim().toUpperCase().equals("PACKING")) {
                invoice.setPacking(myRow.getCell(7).getNumericCellValue());
            }

            if (myRow.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && myRow.getCell(4).getStringCellValue().trim().toUpperCase().equals("GRAND TOTAL")) {
                invoice.setGrandTotal(myRow.getCell(7).getNumericCellValue());
            }
        }
    }
}
