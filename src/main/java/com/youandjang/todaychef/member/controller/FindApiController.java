package com.youandjang.todaychef.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.youandjang.todaychef.common.vo.TodayChefResponse;
import com.youandjang.todaychef.error.constants.ResultCodes;
import com.youandjang.todaychef.util.GetOrDefaultUtil;
import com.youandjang.todaychef.util.MessageUtils;

@RestController
public class FindApiController {

	@Autowired
	GetOrDefaultUtil getOrDefault;
	@Autowired
	MessageUtils messageUtils;

	private final String inputErrorMessage = "TodayChef_CMB06";

	@PostMapping("/findId")
	public ResponseEntity<TodayChefResponse> findId(@RequestBody Map<String, Object> request)
			throws Exception {

		getOrDefault.getOrDefaultToString(request, "inputMail", "", inputErrorMessage);
		TodayChefResponse response = new TodayChefResponse();
		response.setResult(new HashMap<String, Object>());
		response.setResultCode(ResultCodes.OK.getCode());
		response.setResultMessage("ID案内メールを送信しました。");

		return new ResponseEntity<TodayChefResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/findPW")
	public ResponseEntity<TodayChefResponse> findPassword(@RequestBody Map<String, Object> request)
			throws Exception {

		getOrDefault.getOrDefaultToString(request, "loginId", "", inputErrorMessage);
		getOrDefault.getOrDefaultToString(request, "inputMail", "", inputErrorMessage);

		TodayChefResponse response = new TodayChefResponse();
		response.setResult(new HashMap<String, Object>());
		response.setResultCode(ResultCodes.OK.getCode());
		response.setResultMessage("パスワード再設定案内を送信しました。");

		return new ResponseEntity<TodayChefResponse>(response, HttpStatus.OK);
	}
}
