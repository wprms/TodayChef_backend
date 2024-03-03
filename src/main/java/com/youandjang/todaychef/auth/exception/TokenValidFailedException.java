package com.youandjang.todaychef.auth.exception;

public class TokenValidFailedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public TokenValidFailedException() {
        super("Failed to generate Token.");
    }

    public TokenValidFailedException(String message) {
        super(message);
    }
}