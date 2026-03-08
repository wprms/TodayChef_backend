package com.youandjang.todaychef.auth.social;

import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SocialOAuth2UserParser {

	@SuppressWarnings("unchecked")
	public SocialUserProfile parse(String provider, OAuth2User user) {
		Map<String, Object> attributes = user.getAttributes();
		String normalizedProvider = provider == null ? "" : provider.toLowerCase();
		String socialUserId = "";
		String email = "";

		if ("naver".equals(normalizedProvider)) {
			Object responseObj = attributes.get("response");
			if (responseObj instanceof Map) {
				Map<String, Object> response = (Map<String, Object>) responseObj;
				socialUserId = toStringValue(response.get("id"));
				email = toStringValue(response.get("email"));
			}
		} else if ("kakao".equals(normalizedProvider)) {
			socialUserId = toStringValue(attributes.get("id"));
			Object accountObj = attributes.get("kakao_account");
			if (accountObj instanceof Map) {
				Map<String, Object> account = (Map<String, Object>) accountObj;
				email = toStringValue(account.get("email"));
			}
		} else if ("line".equals(normalizedProvider)) {
			socialUserId = toStringValue(attributes.get("sub"));
			if (!StringUtils.hasText(socialUserId)) {
				socialUserId = toStringValue(attributes.get("userId"));
			}
			email = toStringValue(attributes.get("email"));
		} else if ("yahoo".equals(normalizedProvider) || "google".equals(normalizedProvider)) {
			socialUserId = toStringValue(attributes.get("sub"));
			email = toStringValue(attributes.get("email"));
		} else {
			socialUserId = toStringValue(attributes.get("sub"));
			if (!StringUtils.hasText(socialUserId)) {
				socialUserId = toStringValue(attributes.get("id"));
			}
			email = toStringValue(attributes.get("email"));
		}

		return new SocialUserProfile(normalizedProvider, socialUserId, email);
	}

	private String toStringValue(Object value) {
		return value == null ? "" : String.valueOf(value);
	}
}
