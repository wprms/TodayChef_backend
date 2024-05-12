package com.youandjang.todaychef.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.youandjang.todaychef.common.vo.TodayChefResponse;
import com.youandjang.todaychef.util.GetOrDefaultUtil;
import com.youandjang.todaychef.util.MessageUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FindApiController {
	
	@Autowired
	GetOrDefaultUtil getOrDefault;
	@Autowired
	MessageUtils messageUtils;
	
	private final String inputErrorMessage = "TodayChef_CMB06" ;
	
  @PostMapping("/findId")
  public ResponseEntity<TodayChefResponse> FindId(HttpServletRequest req, @RequestBody Map<String, Object> request) throws Exception{
	  
	  String inputMail = getOrDefault.getOrDefaultToString(request, "inputMail","",inputErrorMessage);
	  System.out.println(inputMail);
	  
	  return new ResponseEntity<TodayChefResponse>(null);
  }
  
//  @PostMapping("/findPW")
//  public ResponseEntity<TodayChefResponse> LogOut(HttpServletRequest req, @RequestBody Map<String, Object> request) throws Exception{
//	  
//	  
//	  
//	  return new ResponseEntity<TodayChefResponse>(null);
//  }
}
