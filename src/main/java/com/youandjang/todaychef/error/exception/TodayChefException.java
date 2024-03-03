package com.youandjang.todaychef.error.exception;

public class TodayChefException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
	public TodayChefException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	
	public TodayChefException(String message, Throwable cause) {
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
