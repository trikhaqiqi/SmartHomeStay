package com.codingtest.smarthome.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) { super(message); }

    public BusinessException(String message, Object data) { super(message); }

    public BusinessException(String message, Throwable cause) { super(message, cause); }

}
