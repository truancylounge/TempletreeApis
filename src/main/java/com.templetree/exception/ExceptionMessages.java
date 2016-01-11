package com.templetree.exception;

/**
 * @author Lalith Mannur
 */
public final class ExceptionMessages {
    public static final String INVALID_PASSWORD_ERROR = "Invalid Password";

    //Database related messages
    public static final String GENERIC_DATABASE_ERROR = "Database error occurred";
    public static final String GENERIC_NOT_FOUND = "Not found";
    public static final String DATABASE_CONNECTION_ERROR = "Error in connecting to database";
    public static final String DATABASE_SQLGRAMMAR_ERROR = "Error in SQL syntax";
    public static final String DATABASE_CONSTRAINT_VOILATION_ERROR = "Object with same Id present in database";

    //File error Messages
    public static final String FILE_READ_ERROR = "File Read error";
    public static final String FILE_WRITE_ERROR = "File Write error";
    public static final String GENERIC_FILE_ERROR = "File I/O error occurred";

    public static final String USER_NOT_FOUND = "User doesn't exist. Please login using correct username";
    public static final String TOKEN_ENCYPTION_ERROR = "Token Encryption Error";
    public static final String TOKEN_NOT_FOUND = "Token Not Found";

    public static final String INVOICE_ALREADY_EXISTS = "Invoice Already Exists";


}
