package com.templetree.resources.files;

import com.templetree.model.Invoice;
import com.templetree.model.Item;
import com.templetree.service.intf.ExcelWebServiceIntf;
import com.templetree.service.intf.InvoicesExcelWebServiceIntf;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.List;

/**
 * Created by Lalith on 10/1/15.
 */
@Path("/fileservice")
public class FilesResource {

    public static final String UPLOAD_FILE_SERVER = "/Users/Lalith/Development/projects/TempletreeSystem/files/";

    @Autowired
    private ExcelWebServiceIntf itemListExcelWebService;

    @Autowired
    private InvoicesExcelWebServiceIntf invoicesExcelWebService;


    @POST
    @Path("/upload/itemlist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadItemList(
            @FormDataParam("uploadFile") InputStream fileInputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition) {

        String fileName;
        String uploadFilePath = null;

        try {
            fileName = fileFormDataContentDisposition.getFileName();
            uploadFilePath = writeToFileServer(fileInputStream, fileName);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        finally{
            // release resources, if any
        }
        itemListExcelWebService.readExcel(uploadFilePath);
    }

    @POST
    @Path("/upload/invoices")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Invoice uploadInvoices(
            @FormDataParam("uploadFile") InputStream fileInputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition) {

        System.out.println("Invoices File Upload in progress!!");
        String fileName  = null;
        String uploadFilePath = null;

        try {
            fileName = fileFormDataContentDisposition.getFileName();
            uploadFilePath = writeToFileServer(fileInputStream, fileName);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        finally{
            // release resources, if any
        }

        return invoicesExcelWebService.readExcel(uploadFilePath, fileName);
    }



    /**
     * write input stream to file server
     * @param inputStream
     * @param fileName
     * @throws IOException
     */
    private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {

        System.out.println("Writing Excel file to server.");
        OutputStream outputStream = null;
        String qualifiedUploadFilePath = UPLOAD_FILE_SERVER + fileName;

        try {
            outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally{
            //release resource, if any
            outputStream.close();
        }
        return qualifiedUploadFilePath;
    }

}
