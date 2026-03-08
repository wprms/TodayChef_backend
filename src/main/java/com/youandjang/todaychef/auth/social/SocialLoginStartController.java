package com.youandjang.todaychef.auth.social;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class SocialLoginStartController {

	private static final Set<String> SUPPORTED = Set.of("google", "naver", "kakao", "line", "yahoo");

	private final ClientRegistrationRepository clientRegistrationRepository;

	@Value("${todaychef.social.frontend-login-url:http://localhost:3000/login}")
	private String frontendLoginUrl;

	public SocialLoginStartController(ClientRegistrationRepository clientRegistrationRepository) {
		this.clientRegistrationRepository = clientRegistrationRepository;
	}

	@GetMapping("/social/start/{provider}")
	public void start(@PathVariable String provider, HttpServletResponse response) throws IOException {
		String normalized = provider == null ? "" : provider.toLowerCase();
		if (!SUPPORTED.contains(normalized)) {
			redirectWithError(response, "未対応のソーシャルプロバイダです。");
			return;
		}

		ClientRegistration registration = clientRegistrationRepository.findByRegistrationId(normalized);
		if (registration == null) {
			redirectWithError(response, "ソーシャルログイン設定が見つかりません。");
			return;
		}

		String clientId = registration.getClientId();
		String clientSecret = registration.getClientSecret();
		if (!StringUtils.hasText(clientId) || "disabled".equalsIgnoreCase(clientId) || !StringUtils.hasText(clientSecret)
				|| "disabled".equalsIgnoreCase(clientSecret)) {
			redirectWithError(response, "ソーシャルログインのクライアント設定が未登録です。");
			return;
		}

		response.sendRedirect("/todaychef/oauth2/authorization/" + normalized);
	}

	private void redirectWithError(HttpServletResponse response, String message) throws IOException {
		response.sendRedirect(frontendLoginUrl + "?socialError=" + encode(message));
	}

	private String encode(String value) {
		return URLEncoder.encode(value, StandardCharsets.UTF_8);
	}
}
