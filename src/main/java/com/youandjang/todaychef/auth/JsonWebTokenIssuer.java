package com.youandjang.todaychef.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youandjang.todaychef.auth.exception.JwtInvalidException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;

@Component
@PropertySource(value = "classpath:security.properties", encoding = "UTF-8")
public class JsonWebTokenIssuer {
   private final int ONE_SECONDS = 1000;
   private final int ONE_MINUTE = 60 * ONE_SECONDS;
   private final String KEY_ROLES = "roles";
   private static String secretKey;
   private static String refreshSecretKey;
   private static int expireMin;
   private static int refreshExpireMin;
   
   @Value("${oauth.secret}")
   public void setSecretKey(String tmp) {
      secretKey = tmp;
   }

   @Value("${oauth.refreshsecret}")
   public void setRefreshSecretKey(String tmp) {
      refreshSecretKey = tmp;
   }

   @Value("${oauth.expire-min}")
   public void setExpireMin(int tmp) {
      expireMin = tmp;
   }

   @Value("${oauth.refresh-expire-min}")
   public void setRefreshExpireMin(int tmp) {
      refreshExpireMin = tmp;
   }
   
   //CreateToken
   private String createToken(String userId, int expireMin) {
      String realSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
      Claims claims = Jwts.claims().setSubject(userId);
      Date now = new Date();
      return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + ONE_MINUTE * expireMin))
            .signWith(SignatureAlgorithm.HS256, realSecretKey)
            .compact();
   }

   private String createRefreshToken(String userId, int expireMin) {
      String realRefreshKey = Base64.getEncoder().encodeToString(refreshSecretKey.getBytes());
      Claims claims = Jwts.claims().setSubject(userId);
      Date now = new Date();
      return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + ONE_MINUTE * expireMin))
            .signWith(SignatureAlgorithm.HS256, realRefreshKey)
            .compact();
   }

   //AccessTokenCreate
   public String createAccessToken(String userId) {
      return createToken(userId, expireMin);
   }

   //RefreshTokenCreate
   public String createRefreshToken(String userId) {
      return createRefreshToken(userId, refreshExpireMin);
   }

   //AccessToken Header Set
   public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
      response.setHeader("accessToken", accessToken);
   }

   //RefreshTokenCreate
   public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
      response.setHeader("refreshToken", refreshToken);
   }

   // Token Check Without Expire Check
   public boolean validateToken(String jwtToken) {
      String realSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
      try {
         Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(realSecretKey).build().parseClaimsJws(jwtToken);
         return !claims.getBody().getExpiration().before(new Date());
      } catch (ExpiredJwtException expiredJwtException) {
         return false;
      } catch (MalformedJwtException malformedJwtException) {
         throw new JwtInvalidException("malformed token", malformedJwtException);
      } catch (IllegalArgumentException illegalArgumentException) {
         throw new JwtInvalidException("using illegal argument like null", illegalArgumentException);
      }
   }

   public boolean validateRefreshToken(String refreshToken) {
      String realRefreshKey = Base64.getEncoder().encodeToString(refreshSecretKey.getBytes());
      Jws<Claims> claims;
      try {
         claims = Jwts.parserBuilder().setSigningKey(realRefreshKey).build().parseClaimsJws(refreshToken);
         return !claims.getBody().getExpiration().before(new Date());
      } catch (ExpiredJwtException expiredJwtException) {
         return false;
      } catch (MalformedJwtException malformedJwtException) {
         throw new JwtInvalidException("malformed token", malformedJwtException);
      } catch (IllegalArgumentException illegalArgumentException) {
         throw new JwtInvalidException("using illegal argument like null", illegalArgumentException);
      }
   }

   //RefreshToken Check UserId
   public String refreshUserIdCheck(String refreshToken) {
      String realRefreshKey = Base64.getEncoder().encodeToString(refreshSecretKey.getBytes());
      Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(realRefreshKey).build().parseClaimsJws(refreshToken);
      return claims.getBody().getSubject().toString();
   }

   public Map<String, Object> decoder(String accessToken) throws JsonParseException, JsonMappingException, IOException {
      ObjectMapper mapper = new ObjectMapper();
      String[] splitToken = accessToken.split("\\.");
      Decoder decoder = Base64.getDecoder();
      byte[] decodedBytes = decoder.decode(splitToken[1]);
      String decodedString = null;
      Map<String, Object> map = null;
      try {
         decodedString = new String(decodedBytes, "UTF-8");
         map = mapper.readValue(decodedBytes, new TypeReference<Map<String, Object>>() {
         });
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      return map;
   }

   //RefreshToken Check
   public Claims parseClaimsFromRefreshToken(String jsonWebToken) {
      String realSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
      Claims claims;
      try {
         claims = Jwts.parserBuilder().setSigningKey(realSecretKey).build()
               .parseClaimsJws(jsonWebToken)
               .getBody();
      } catch (MalformedJwtException malformedJwtException) {
         throw new JwtInvalidException("malformed token", malformedJwtException);
      } catch (IllegalArgumentException illegalArgumentException) {
         throw new JwtInvalidException("using illegal argument like null", illegalArgumentException);
      }
      return claims;
   }


}
