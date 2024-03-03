package com.youandjang.todaychef.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtInvalidException extends AuthenticationException {

	    private static final long serialVersionUID = 1L;

		public JwtInvalidException(String msg) {
	        super(msg);
	    }

	    public JwtInvalidException(String msg, Throwable cause) {
	        super(msg, cause);
	    }
	}
