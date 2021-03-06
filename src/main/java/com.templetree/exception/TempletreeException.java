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

    private Integer status;
    private String code;
    private String developerMessage;

    public TempletreeException(Integer status, String code, String message, String developerMessage ) {
        super(message);
        this.status = status;
        this.code = code;
        this.developerMessage = developerMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
