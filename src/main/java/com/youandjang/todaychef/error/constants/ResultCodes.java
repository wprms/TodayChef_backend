package com.youandjang.todaychef.error.constants;

public enum ResultCodes {
	   OK("STI01"),
	   INTERNAL_SERVER_ERROR("STB01"),
	   UNAUTHORIZED("STB02"),
	   FORBIDDEN("STB04"),
	   NOT_FOUND("STB05"),
	   BAD_URL("STB06");
	   
	   private String code;

	   ResultCodes(String code) {
	      this.code = code;
	   }

	   public String getCode() {
	      return code;
	   }
	}
