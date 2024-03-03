package com.youandjang.todaychef.error.exception;

public class TodayChefAuthException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
	public TodayChefAuthException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	
	public TodayChefAuthException(String message, Throwable cause) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
