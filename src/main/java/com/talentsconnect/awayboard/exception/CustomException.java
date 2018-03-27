package com.talentsconnect.awayboard.exception;

/**
 * Created by ritesh on 27/3/18.
 */
public class CustomException extends RuntimeException{
    protected String code;
    protected String errorDescription;

    public CustomException() {
        super();
    }

    public CustomException(String code, String errorDescription) {
        super(errorDescription);
        setFields(code, errorDescription);
    }
    public CustomException(String errorDescription) {
        super(errorDescription);
        setFields("default", errorDescription);
    }
    public CustomException(String errorDescription, Throwable cause) {
        super(errorDescription, cause);
        setFields("default", errorDescription);
    }
    public CustomException(String code, String errorDescription, Throwable cause) {
        super(errorDescription, cause);
        setFields(code, errorDescription);
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.message);
        setFields(errorCode.code, errorCode.message);
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.message, cause);
        setFields(errorCode.code, errorCode.message);
    }

    public CustomException(ErrorCode errorCode, String appendMessage) {
        super(errorCode.message + "." + appendMessage);
        setFields(errorCode.code, errorCode.message + "." + appendMessage);
    }

    public CustomException(ErrorCode errorCode, Throwable cause, String appendMessage) {
        super(errorCode.message + "." + appendMessage, cause);
        setFields(errorCode.code, errorCode.message + "." + appendMessage);
    }

    private void setFields(String code, String description){
        this.code = code;
        this.errorDescription = description;
    }

    @Override
    public String toString() {
        return "CustomException [code=" + code + ", errorDescription=" +errorDescription+"]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
