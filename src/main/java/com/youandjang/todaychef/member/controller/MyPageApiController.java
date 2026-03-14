package com.youandjang.todaychef.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youandjang.todaychef.auth.JsonWebTokenIssuer;
import com.youandjang.todaychef.common.service.CommonService;
import com.youandjang.todaychef.common.vo.TodayChefResponse;
import com.youandjang.todaychef.error.constants.ResultCodes;
import com.youandjang.todaychef.member.vo.MemberDto;
import com.youandjang.todaychef.util.MessageUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MyPageApiController {

	@Autowired
	CommonService commonService;
	@Autowired
	JsonWebTokenIssuer jwtIssuer;
	@Autowired
	MessageUtils messageUtils;

	@GetMapping("/member/me")
	public ResponseEntity<TodayChefResponse> myPageInfo(HttpServletRequest req) throws Exception {
		TodayChefResponse res = new TodayChefResponse();
		String accessToken = req.getHeader("accessToken");
		String userId = jwtIssuer.decoder(accessToken).get("sub").toString();
		MemberDto member = commonService.findMemberId(userId);

		Map<String, Object> result = new HashMap<String, Object>();
		if (member != null) {
			result.put("userSysId", member.getUserSysId());
			result.put("userLoginId", member.getUserLoginId());
			result.put("userMail", member.getUserMail());
			result.put("userAuth", member.getUserAuth());
			result.put("stopFlag", String.valueOf(member.getStopFlag()));
			result.put("lastLoginDatetime", member.getLastLoginDatetime());
		}

		res.setResult(result);
		res.setResultCode(ResultCodes.OK.getCode());
		res.setResultMessage(messageUtils.getMessage("TodayChef_STI01"));
		return new ResponseEntity<TodayChefResponse>(res, HttpStatus.OK);
	}
}
