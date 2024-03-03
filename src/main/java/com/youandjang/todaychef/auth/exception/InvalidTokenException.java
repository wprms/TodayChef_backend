package com.youandjang.todaychef.auth.exception;

public class InvalidTokenException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
    public InvalidTokenException(String message, String code) {
        super();
        this.message = message;
        this.code = code;
    }
    
    public InvalidTokenException(String message, Throwable cause) {
        super();
        this.message = message;
    }
    
    
    @Override
    public String getMessage() {
    	return message;
    }
    
    public String getErrCode() {
    	return code;
    }
    
    
}