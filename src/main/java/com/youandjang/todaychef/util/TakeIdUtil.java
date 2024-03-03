package com.youandjang.todaychef.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.youandjang.todaychef.auth.JsonWebTokenIssuer;
import com.youandjang.todaychef.auth.exception.JwtInvalidException;

import jakarta.servlet.http.HttpServletRequest;

public class TakeIdUtil{
	
 @Autowired
 static MessageUtils messageUtils;
 
 private static JsonWebTokenIssuer jwtIssuer = new JsonWebTokenIssuer();
 
 public static String takeIdUtility(HttpServletRequest request) throws Exception{
	 
	 String accessToken = request.getHeader("accessToken");
	 if(accessToken == null) {
		 throw new JwtInvalidException(null);
	 }
	 String userId = jwtIssuer.decoder(accessToken).get("sub").toString();
	 return userId;
 }
	
}
