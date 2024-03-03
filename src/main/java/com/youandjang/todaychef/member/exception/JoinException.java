package com.youandjang.todaychef.member.exception;

public class JoinException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
	public JoinException(String message,String code) {
		super();
		this.message = message;
		this.code = code;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getErrCode() {
		return code;
	}
	
}