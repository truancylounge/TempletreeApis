package com.templetree.exception;

import org.slf4j.Logger;

import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Lalith Mannur
 */
public class TempletreeException extends Exception {

    private static final Logger LOGGER = getLogger(TempletreeException.class);

    private static final String DEFAULT_MSG = "Templetree Exception";

    private static final String TEMPLETREE_EXCEPTION_CODE = "TEMPLETREE_EXCEPTION";

    private final String code;

    private final Throwable reason;

    private final String hostName;

    private final String message;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    /**
     * Create exception object with the default message.
     */
    public TempletreeException() {

        this(DEFAULT_MSG);
    }

    /**
     * Create exception object with the given message.
     *
     * @param msg the exception message
     */
    public TempletreeException(final String msg) {

        this(msg, TEMPLETREE_EXCEPTION_CODE, null);
    }

    /**
     * Create exception object with the given message and exception code.
     *
     * @param msg the exception message
     * @param code the exception code
     */
    public TempletreeException(final String msg, final String code) {

        this(msg, code, null);
    }

    /**
     * Create exception object with the given message and exception code.
     *
     * @param msg the exception message
     * @param code the exception code
     * @param reason actual exception
     */
    public TempletreeException(final String msg, final String code,
                         final Throwable reason) {

        super(msg);
        this.message = msg;
        this.hostName = "";
        this.code = code;
        this.reason = reason;
    }

    /**
     * Create exception object with the given message.
     *
     * @param message exception message
     * @param exception the actual Exception thrown.
     */
    public TempletreeException(final String message, final Exception exception) {
        super(message);

        if (System.getProperty("os.name").startsWith("Windows")) {
            hostName = System.getenv("COMPUTERNAME");
        } else {
            hostName = System.getenv("HOSTNAME");
        }
        String printmessage = "Templetree Exception : " + message + " occurred, code " + hostName + "-" + new Date().getTime() / 1000;
        LOGGER.error(printmessage, " with exception : {} ", exception);
        this.message = printmessage;
        this.code = "";
        this.reason = exception;
    }

    public String getCode() {
        return code;
    }

    public Throwable getReason() {
        return reason;
    }

    public String getHostName() {
        return hostName;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
