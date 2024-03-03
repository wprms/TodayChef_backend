package com.youandjang.todaychef.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youandjang.todaychef.common.vo.TodayChefResponse;
import com.youandjang.todaychef.error.constants.ResultCodes;
import com.youandjang.todaychef.member.exception.JoinException;
import com.youandjang.todaychef.member.service.JoinService;
import com.youandjang.todaychef.member.vo.MemberDto;
import com.youandjang.todaychef.util.GetOrDefaultUtil;
import com.youandjang.todaychef.util.MessageUtils;
import com.youandjang.todaychef.util.SecurityUtil;

@RestController
@RequestMapping("join")
public class JoinApiController {
		
		@Autowired
		JoinService joinService;
		@Autowired
		MessageUtils messageUtils;
		@Autowired
		GetOrDefaultUtil getOrDefault;
		
		private final String joinErrorMessage = "TodayChef_MBB05";
				
		@PostMapping("/signup")
		public ResponseEntity<TodayChefResponse> Join(@RequestBody Map<String, Object> request) throws Exception{

			TodayChefResponse res = new TodayChefResponse();
			Map<String, Object> result = new HashMap<String, Object>();
			
			MemberDto member = new MemberDto();
			SecurityUtil security = new SecurityUtil();
			
			String loginId = getOrDefault.getOrDefaultToString(request, "loginId", "", joinErrorMessage);
			String password = getOrDefault.getOrDefaultToString(request, "password", "", joinErrorMessage);
			String userMail = getOrDefault.getOrDefaultToString(request, "mail", "", joinErrorMessage);
			String securePassword= security.encryptSHA256(password);
			
			
			boolean isDuplication = joinService.isUserIdDuplicate(loginId);
			if(isDuplication) {
				String messages = messageUtils.getMessage("TodayChef_MBB04");
				throw new JoinException(messages, "MBB04");
			}
			
			
			String tfIdFromDB = joinService.findMaxId();			
			String sysId = "";
			
			if (tfIdFromDB != null) {
			int tfSequence = Integer.parseInt(tfIdFromDB);
			tfSequence++;
			String tfSequenceToString = String.valueOf(tfSequence);
			int calDigitNum = tfSequenceToString.length();				
			if (calDigitNum < 0) { 
				throw new RuntimeException("Exceeding the maximum number of members.");
			}
			sysId = tfSequenceToString;
			
			}else {
				sysId = "001";
			}
			
			member.setUserSysId(sysId);
			member.setUserLoginId(loginId);
			member.setUserPassword(securePassword);
			member.setUserMail(userMail);		
			member.setStopReason(getOrDefault.getOrDefaultToStringNullable(request, "stopReason", ""));
			member.setCustomerId(getOrDefault.getOrDefaultToStringNullable(request, "customerId", ""));
			
			joinService.Join(member, securePassword);
			
			res.setResult(result);
			res.setResultCode(ResultCodes.OK.getCode());
			res.setResultMessage(messageUtils.getMessage("TodayChef_STI01"));		
			
			return new ResponseEntity<TodayChefResponse>(res, HttpStatus.OK);
		}

	}