package com.youandjang.todaychef.auth;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.youandjang.todaychef.auth.exception.InvalidTokenException;
import com.youandjang.todaychef.auth.exception.JwtInvalidException;
import com.youandjang.todaychef.auth.exception.UserLoginException;
import com.youandjang.todaychef.auth.service.AuthService;
import com.youandjang.todaychef.auth.vo.AuthInfoDto;
import com.youandjang.todaychef.util.MessageUtils;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@PropertySource(value = "classpath:security.properties", encoding = "UTF-8")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String BEARER_PREFIX = "Bearer ";

	private final AuthenticationManager authenticationManager;
	private final AuthService authService;
	private final MessageUtils messageUtils;
	private final JsonWebTokenIssuer jwtIssuer;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthService authService, MessageUtils messageUtils,
			JsonWebTokenIssuer jwtIssuer) {
		this.authenticationManager = authenticationManager;
		this.authService = authService;
		this.messageUtils = messageUtils;
		this.jwtIssuer = jwtIssuer;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String accessToken = request.getHeader("accessToken");
		String refreshToken = request.getHeader("refreshToken");
		String servletPath = request.getServletPath();
		AuthInfoDto authInfo = new AuthInfoDto();
		if (servletPath.equals("/join/form") || servletPath.equals("/login") || servletPath.startsWith("/join/")) {
			filterChain.doFilter(request, response);
			return;
		}

		if (accessToken != null) {
			if (jwtIssuer.validateToken(accessToken)) {
				try {
					Authentication jwtAuthenticationToken = new JwtAuthenticationToken(accessToken);
					Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					String userId = jwtIssuer.decoder(accessToken).get("sub").toString();
					
					try {
						authInfo = authService.authInfoFind(userId);
					} catch (Exception e) {
						SecurityContextHolder.clearContext();
						String messages = messageUtils.getMessage("TodayChef_STB02");
			            throw new InvalidTokenException(messages, "STB02");
					}
					
					try {
						String lastLoginTimeHeader = request.getHeader("lastLoginTime");
						Timestamp lastLogin = authInfo.getLastLoginDatetime();
						if (StringUtils.hasText(lastLoginTimeHeader) && lastLogin != null) {
							Timestamp lastLoginDatetime = Timestamp.valueOf(lastLoginTimeHeader);
							if (!lastLoginDatetime.equals(lastLogin) && lastLoginDatetime.before(lastLogin)) {
								SecurityContextHolder.clearContext();
								response.setHeader("accessToken", "");
							}
						}
					} catch (Exception e) {
						SecurityContextHolder.clearContext();
						String messages = messageUtils.getMessage("TodayChef_STB02");
			            throw new UserLoginException(messages, "STB02");
					}
				} catch (AuthenticationException authenticationException) {
					SecurityContextHolder.clearContext();
					String messages = messageUtils.getMessage("TodayChef_STB02");
		            throw new UserLoginException(messages, "STB02");
					}
				} else if (!jwtIssuer.validateToken(accessToken)) {
				//accessTokenからPayloadのsub（UserId）を抽出する。
				String userId = jwtIssuer.decoder(accessToken).get("sub").toString();
				try {
					authInfo = authService.authInfoFind(userId);
				} catch (Exception e) {
					SecurityContextHolder.clearContext();
					String messages = messageUtils.getMessage("TodayChef_STB02");
		            throw new UserLoginException(messages, "STB02");
				}
				
				String savedRefreshToken = authInfo.getTodaychefToken();
				if (StringUtils.hasText(savedRefreshToken) && StringUtils.hasText(refreshToken)) {
					// REISSUE AND Check
					boolean validateRefreshToken = jwtIssuer.validateRefreshToken(refreshToken);
					boolean isStoredToken = Objects.equals(savedRefreshToken, refreshToken);
					boolean isRefreshOwner = Objects.equals(jwtIssuer.refreshUserIdCheck(refreshToken), userId);
					if (validateRefreshToken && isStoredToken && isRefreshOwner) {
						// Refresh Token Check
						String newAccessToken = jwtIssuer.createAccessToken(userId);
						jwtIssuer.setHeaderAccessToken(response, newAccessToken);
						try {
							Authentication jwtAuthenticationToken = new JwtAuthenticationToken(newAccessToken);
							Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);
							SecurityContextHolder.getContext().setAuthentication(authentication);
						} catch (AuthenticationException authenticationException) {
							SecurityContextHolder.clearContext();
						} catch (ExpiredJwtException expiredJwtException) {
							throw new JwtInvalidException("expired token", expiredJwtException);
						}
					} else {
						SecurityContextHolder.clearContext();
					}
				}else {
					SecurityContextHolder.clearContext();
				}
			}
		} 
		filterChain.doFilter(request, response);
	}
}
