package com.templetree.model;

import com.templetree.exception.TempletreeException;

/**
 * Error Message entity which will be sent in the Response
 *
 * @author Lalith Mannur
 */
public class ErrorMessage {

    private Integer status;
    private String code;
    private String message;
    private String developerMessage;

    public ErrorMessage(Integer status, String code, String message, String developerMessage) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    public ErrorMessage(TempletreeException ex) {
        this.status = ex.getStatus();
        this.code = ex.getCode();
        this.message = ex.getMessage();
        this.developerMessage = ex.getDeveloperMessage();
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
