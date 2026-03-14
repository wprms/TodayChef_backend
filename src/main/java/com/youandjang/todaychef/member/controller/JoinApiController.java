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
	@Autowired
	SecurityUtil securityUtil;

	private final String joinErrorMessage = "TodayChef_MBB05";

	@PostMapping("/signup")
	public ResponseEntity<TodayChefResponse> Join(@RequestBody Map<String, Object> request) throws Exception {

		TodayChefResponse res = new TodayChefResponse();
		Map<String, Object> result = new HashMap<String, Object>();

		MemberDto member = new MemberDto();
		String loginId = getOrDefault.getOrDefaultToString(request, "loginId", "", joinErrorMessage);
		String password = getOrDefault.getOrDefaultToString(request, "password", "", joinErrorMessage);
		String userMail = getOrDefault.getOrDefaultToString(request, "mail", "", joinErrorMessage);
		String securePassword = securityUtil.encryptPassword(password);

		boolean isDuplication = joinService.isUserIdDuplicate(loginId);
		if (isDuplication) {
			String messages = messageUtils.getMessage("TodayChef_MBB04");
			throw new JoinException(messages, "MBB04");
		}

		member.setUserLoginId(loginId);
		member.setUserPassword(securePassword);
		member.setUserMail(userMail);
		member.setStopReason(getOrDefault.getOrDefaultToStringNullable(request, "stopReason", ""));

		joinService.Join(member);

		res.setResult(result);
		res.setResultCode(ResultCodes.OK.getCode());
		res.setResultMessage(messageUtils.getMessage("TodayChef_STI01"));

		return new ResponseEntity<TodayChefResponse>(res, HttpStatus.OK);
	}

}
