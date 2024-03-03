package com.youandjang.todaychef.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.youandjang.todaychef.auth.exception.UserLoginException;
import com.youandjang.todaychef.auth.service.AuthService;
import com.youandjang.todaychef.auth.vo.OAuthToken;
import com.youandjang.todaychef.common.vo.TodayChefResponse;
import com.youandjang.todaychef.error.constants.ResultCodes;
import com.youandjang.todaychef.error.constants.ScreenCodes;
import com.youandjang.todaychef.member.service.LoginService;
import com.youandjang.todaychef.member.vo.MemberDto;
import com.youandjang.todaychef.util.AuthCheckUtil;
import com.youandjang.todaychef.util.GetOrDefaultUtil;
import com.youandjang.todaychef.util.MessageUtils;
import com.youandjang.todaychef.util.ObjectUtil;
import com.youandjang.todaychef.util.SecurityUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class LoginApiController {
	
	@Autowired
	AuthCheckUtil authCheck;
	@Autowired
	GetOrDefaultUtil getOrDefault;
	@Autowired
	MessageUtils messageUtils;
	@Autowired
	LoginService loginService;
	@Autowired
	AuthService authService;
	
	private final String inputErrorMessage = "TodayChef_CMB06" ;
	
  @PostMapping("/login")
  public ResponseEntity<TodayChefResponse> Login(HttpServletRequest req, @RequestBody Map<String, Object> request) throws Exception{
	  
	  MemberDto user = new MemberDto();
	  
	  String userId = getOrDefault.getOrDefaultToString(request, "loginId","",inputErrorMessage);
	  String password = getOrDefault.getOrDefaultToString(request, "password","",inputErrorMessage);
	  String messages = messageUtils.getMessage("TodayChef_STI01");
	  
	  SecurityUtil security = new SecurityUtil();
	  String securePassword = security.encryptSHA256(password);
	  
	  try {
		  	user = loginService.findLoginId(userId);
	  }catch(Exception e) {
		   messages = messageUtils.getMessage("TodayChef_STB01", new Object[] {userId});
		   throw new UserLoginException(messages, "STB01");
	  }
	  
	  if(ObjectUtil.isEmpty(userId)) {
		  messages = messageUtils.getMessage("TodayChef_MBB01", new Object[] {userId});
		  throw new UserLoginException(messages, "MBB01");
	  }
	  
	  if(securePassword.equals(user.getUserPassword())) {
		  messages = messageUtils.getMessage("TodayChef_MBB02");
		  throw new UserLoginException(messages, "MBB02");
	  }
	  
	  String targetDate = user.getLastLoginDatetime().toString();
	  boolean firstLoginFlag = false;
	  if(targetDate.equals("0001-01-01 00:00:00.0")) {
		  firstLoginFlag = true;
	  }
	  
	  TodayChefResponse res = new TodayChefResponse();
	  Map<String, Object> result = new HashMap<String,Object>();
	  result.put("stopFlag", user.getStopFlag());
	  result.put("firstLoginFlag", firstLoginFlag);
	  result.put("userName", user.getUserName());
	  
	  OAuthToken token = authService.createToken(user.getUserSysId());
	  loginService.login(token.getRefresh_token(), user.getUserSysId());
	  user = loginService.findLoginId(userId);
	  
	  HttpHeaders headers = new HttpHeaders();
	  headers.add("accessToken", token.getAccess_token());
	  headers.add("lastLoginTime", user.getLastLoginDatetime().toString());
	  
	  res.setResult(result);
	  res.setResultCode(ResultCodes.OK.getCode());
	  res.setResultMessage(messages);
	  
	  return new ResponseEntity<TodayChefResponse>(res, headers, HttpStatus.OK);
  }
  
  @PostMapping("/logout")
  public ResponseEntity<TodayChefResponse> LogOut(HttpServletRequest req) throws Exception{
	  
	  TodayChefResponse res = new TodayChefResponse();
	  Map<String, Object> result = new HashMap<String,Object>();
	  
	  String accessToken = req.getHeader("accessToken");
	  String userId = authCheck.authCheck(ScreenCodes.MB02, req);
	  
	  Map<String, Object> userInfo = new HashMap<String, Object>();
	  
	  userInfo.put("accessToken", accessToken);
	  userInfo.put("userId", userId);
	  
	  //loginService.logoutToken(userInfo);
	  
	  HttpHeaders headers = new HttpHeaders();
	  headers.remove("accessToken");
	  headers.remove("lastLoginTime");
	  
	  String messages = messageUtils.getMessage("");
	  res.setResult(result);
	  res.setResultCode(ResultCodes.OK.getCode());
	  res.setResultMessage(messages);
	  
	  return new ResponseEntity<TodayChefResponse>(null);
  }
}
