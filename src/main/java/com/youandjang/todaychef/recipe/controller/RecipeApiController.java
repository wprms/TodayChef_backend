package com.youandjang.todaychef.recipe.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.youandjang.todaychef.auth.service.AuthService;
import com.youandjang.todaychef.common.vo.TodayChefResponse;
import com.youandjang.todaychef.error.constants.ResultCodes;
import com.youandjang.todaychef.recipe.service.RecipeService;
import com.youandjang.todaychef.recipe.vo.RecipeDto;
import com.youandjang.todaychef.util.AuthCheckUtil;
import com.youandjang.todaychef.util.GetOrDefaultUtil;
import com.youandjang.todaychef.util.MessageUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RecipeApiController {

	@Autowired
	AuthCheckUtil authCheck;
	@Autowired
	GetOrDefaultUtil getOrDefault;
	@Autowired
	MessageUtils messageUtils;
	@Autowired
	RecipeService recipeService;
	@Autowired
	AuthService authService;

	private final String recipeErrorMessage = "TodayChef_MBB05";
	
	@PostMapping("/receipe/upload")
	public ResponseEntity<TodayChefResponse> RecipeUpload(HttpServletRequest req, @RequestBody Map<String, Object> request) throws Exception {

		TodayChefResponse res = new TodayChefResponse();
		Map<String, Object> result = new HashMap<String, Object>();
		
		RecipeDto recipeInfo = new RecipeDto();		
		Map<String, Object> recipeStepsInfo = new HashMap<String, Object>();
		
		recipeInfo.setRecipeTitle(getOrDefault.getOrDefaultToString(request, "title", "", recipeErrorMessage));
		recipeInfo.setRecipeInfo(getOrDefault.getOrDefaultToString(request, "info", "", recipeErrorMessage));
		recipeInfo.setRecipeSteps(recipeStepsInfo);
		recipeInfo.setRecipeInstagramLink(getOrDefault.getOrDefaultToString(request, "instagramLink", "", recipeErrorMessage));
		recipeInfo.setRecipeVideoLink(getOrDefault.getOrDefaultToString(request, "videoLink", "", recipeErrorMessage));
		
		recipeService.recipeUpload(recipeInfo);

		res.setResult(result);
		res.setResultCode(ResultCodes.OK.getCode());
		res.setResultMessage(messageUtils.getMessage("TodayChef_STI01"));

		return new ResponseEntity<TodayChefResponse>(res, HttpStatus.OK);
	}
}
