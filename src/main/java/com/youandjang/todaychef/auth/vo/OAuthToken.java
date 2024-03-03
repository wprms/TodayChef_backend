package com.youandjang.todaychef.auth.vo;

import lombok.Builder;

@Builder
public class OAuthToken {
   private String access_token;
   private String token_type;
   private String refresh_token;
   private String expires_in;
   private String scope;
   private String id_token;
   private String refresh_token_expires_in;
   
   public OAuthToken(String access_token, String token_type, String refresh_token, String expires_in, String scope, String id_token) {
      this.access_token = access_token;
      this.token_type = token_type;
      this.refresh_token = refresh_token;
      this.expires_in = expires_in;
      this.scope = scope;
      this.id_token = id_token;
   }
   
   public OAuthToken(String access_token, String token_type, String refresh_token, String expires_in, String scope, String id_token, String refresh_token_expires_in) {
      this.access_token = access_token;
      this.token_type = token_type;
      this.refresh_token = refresh_token;
      this.expires_in = expires_in;
      this.scope = scope;
      this.id_token = id_token;
      this.refresh_token_expires_in = refresh_token_expires_in;
   }
   
   public OAuthToken() {
      
   }
   
   public String getAccess_token() {
      return access_token;
   }
   public void setAccess_token(String access_token) {
      this.access_token = access_token;
   }
   public String getToken_type() {
      return token_type;
   }
   public void setToken_type(String token_type) {
      this.token_type = token_type;
   }
   public String getRefresh_token() {
      return refresh_token;
   }
   public void setRefresh_token(String refresh_token) {
      this.refresh_token = refresh_token;
   }
   public String getExpires_in() {
      return expires_in;
   }
   public void setExpires_in(String expires_in) {
      this.expires_in = expires_in;
   }
   public String getScope() {
      return scope;
   }
   public void setScope(String scope) {
      this.scope = scope;
   }
   public String getId_token() {
      return id_token;
   }
   public void setId_token(String id_token) {
      this.id_token = id_token;
   }

   public String getRefresh_token_expires_in() {
      return refresh_token_expires_in;
   }

   public void setRefresh_token_expires_in(String refresh_token_expires_in) {
      this.refresh_token_expires_in = refresh_token_expires_in;
   }

}