package com.youandjang.todaychef.auth.social;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SocialLoginFailureHandler implements AuthenticationFailureHandler {

	@Value("${todaychef.social.frontend-login-url:http://localhost:3000/login}")
	private String frontendLoginUrl;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String error = exception == null ? "ソーシャルログインに失敗しました。" : exception.getMessage();
		response.sendRedirect(frontendLoginUrl + "?socialError=" + encode(error));
	}

	private String encode(String value) {
		return URLEncoder.encode(value, StandardCharsets.UTF_8);
	}
}
