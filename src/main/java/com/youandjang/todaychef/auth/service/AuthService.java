package com.youandjang.todaychef.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youandjang.todaychef.auth.JsonWebTokenIssuer;
import com.youandjang.todaychef.auth.dao.AuthDao;
import com.youandjang.todaychef.auth.vo.AuthInfoDto;
import com.youandjang.todaychef.auth.vo.OAuthToken;
import com.youandjang.todaychef.util.MessageUtils;

@Service
public class AuthService {

	@Autowired
	AuthDao authDao;
	private final String GRANT_TYPE_BEARER = "Bearer";
	private final JsonWebTokenIssuer jwtIssuer;
	@Autowired
	MessageUtils messageUtils;

	public AuthService(JsonWebTokenIssuer jwtIssuer) {
		this.jwtIssuer = jwtIssuer;
	}

	private OAuthToken createJsonWebTokenDto(String userId) throws Exception {
		return OAuthToken.builder()
				.token_type(GRANT_TYPE_BEARER)
				.access_token(jwtIssuer.createAccessToken(userId))
				.refresh_token(jwtIssuer.createRefreshToken(userId))
				.build();
	}

	public OAuthToken login(String userId) throws Exception {
		return createJsonWebTokenDto(userId);
	}
	
	public AuthInfoDto authInfoFind(String userId) throws Exception {
		AuthInfoDto userEntity = authDao.authInfoFind(userId);
		return userEntity;
	}
	
	public OAuthToken createToken(String userId) throws Exception {
		return createJsonWebTokenDto(userId);
	}
}
