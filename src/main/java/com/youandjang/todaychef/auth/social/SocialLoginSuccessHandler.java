package com.youandjang.todaychef.auth.social;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.youandjang.todaychef.auth.service.AuthService;
import com.youandjang.todaychef.auth.vo.OAuthToken;
import com.youandjang.todaychef.member.service.LoginService;
import com.youandjang.todaychef.member.vo.MemberDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SocialLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private SocialOAuth2UserParser parser;
	@Autowired
	private SocialMemberService socialMemberService;
	@Autowired
	private AuthService authService;
	@Autowired
	private LoginService loginService;

	@Value("${todaychef.social.frontend-login-url:http://localhost:3000/login}")
	private String frontendLoginUrl;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		try {
			OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
			OAuth2User oauth2User = oauthToken.getPrincipal();
			String provider = oauthToken.getAuthorizedClientRegistrationId();

			SocialUserProfile profile = parser.parse(provider, oauth2User);
			if (!StringUtils.hasText(profile.getSocialUserId())) {
				redirectWithError(response, "ソーシャルIDを取得できません。");
				return;
			}

			MemberDto member = socialMemberService.findOrCreate(profile);
			OAuthToken token = authService.createToken(member.getUserSysId());
			loginService.login(token.getRefresh_token(), member.getUserSysId());
			MemberDto latest = loginService.findByUserSysId(member.getUserSysId());

			String redirectUrl = frontendLoginUrl + "?accessToken="
					+ encode(token.getAccess_token()) + "&refreshToken=" + encode(token.getRefresh_token()) + "&lastLoginTime="
					+ encode(latest.getLastLoginDatetime().toString());
			response.sendRedirect(redirectUrl);
		} catch (Exception e) {
			redirectWithError(response, "ソーシャルログイン処理に失敗しました。");
		}
	}

	private void redirectWithError(HttpServletResponse response, String message) throws IOException {
		response.sendRedirect(frontendLoginUrl + "?socialError=" + encode(message));
	}

	private String encode(String value) {
		return URLEncoder.encode(value, StandardCharsets.UTF_8);
	}
}
