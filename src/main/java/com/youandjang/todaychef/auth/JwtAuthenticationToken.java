package com.youandjang.todaychef.auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

	private String jsonwebToken;
	private Object principal;
	private Object credentials;

	public JwtAuthenticationToken(String jsonwebToken) {
		super(null);
		this.jsonwebToken = jsonwebToken;
		this.setAuthenticated(false);
	}

	public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}

	public String getJsonWebToken() {
		return jsonwebToken;
	}

	public Object getPrincipal() {
		return principal;
	}

	public Object getCredentials() {
		return credentials;
	}

}