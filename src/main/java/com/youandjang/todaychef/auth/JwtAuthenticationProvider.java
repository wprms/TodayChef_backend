package com.youandjang.todaychef.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.youandjang.todaychef.auth.exception.JwtInvalidException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final byte[] secretKeyByte;

	public JwtAuthenticationProvider(@Value("${oauth.secret}") String secretKey) {
		this.secretKeyByte = secretKey.getBytes();
	}

	private Collection<? extends GrantedAuthority> createGrantedAuthorities(Claims claims) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		return grantedAuthorities;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Claims claims;
		try {
			claims = Jwts.parserBuilder().setSigningKey(secretKeyByte).build()
					.parseClaimsJws(((JwtAuthenticationToken) authentication).getJsonWebToken()).getBody();
		} catch (ExpiredJwtException expiredJwtException) {
			throw new JwtInvalidException("expired token", expiredJwtException);
		} catch (MalformedJwtException malformedJwtException) {
			throw new JwtInvalidException("malformed token", malformedJwtException);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new JwtInvalidException("using illegal argument like null", illegalArgumentException);
		}
		return new JwtAuthenticationToken(claims.getSubject(), "", createGrantedAuthorities(claims));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}
}