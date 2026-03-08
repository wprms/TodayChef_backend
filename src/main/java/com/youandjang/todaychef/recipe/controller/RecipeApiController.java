package com.youandjang.todaychef.recipe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youandjang.todaychef.auth.JsonWebTokenIssuer;
import com.youandjang.todaychef.common.vo.TodayChefResponse;
import com.youandjang.todaychef.error.constants.ResultCodes;
import com.youandjang.todaychef.recipe.service.RecipeService;
import com.youandjang.todaychef.recipe.vo.RecipeDto;
import com.youandjang.todaychef.util.GetOrDefaultUtil;
import com.youandjang.todaychef.util.MessageUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RecipeApiController {

	@Autowired
	GetOrDefaultUtil getOrDefault;
	@Autowired
	MessageUtils messageUtils;
	@Autowired
	RecipeService recipeService;
	@Autowired
	JsonWebTokenIssuer jwtIssuer;

	private final ObjectMapper objectMapper = new ObjectMapper();

	private final String recipeErrorMessage = "TodayChef_MBB05";
	
	@PostMapping({"/recipe/upload", "/receipe/upload"})
	public ResponseEntity<TodayChefResponse> RecipeUpload(HttpServletRequest req, @RequestBody Map<String, Object> request) throws Exception {

		TodayChefResponse res = new TodayChefResponse();
		Map<String, Object> result = new HashMap<String, Object>();
		
		RecipeDto recipeInfo = new RecipeDto();
		String accessToken = req.getHeader("accessToken");
		String userId = jwtIssuer.decoder(accessToken).get("sub").toString();
		Map<String, Object> recipePayload = new HashMap<String, Object>();
		recipePayload.put("info", getOrDefault.getOrDefaultToString(request, "info", "", recipeErrorMessage));
		recipePayload.put("steps", request.get("steps"));
		
		recipeInfo.setUserSysId(userId);
		recipeInfo.setRecipeTitle(getOrDefault.getOrDefaultToString(request, "title", "", recipeErrorMessage));
		recipeInfo.setRecipeInfo(getOrDefault.getOrDefaultToString(request, "info", "", recipeErrorMessage));
		recipeInfo.setRecipeSteps(objectMapper.writeValueAsString(recipePayload));
		recipeInfo.setRecipeThumbnailImage(getOrDefault.getOrDefaultToStringNullable(request, "thumbnailImage", ""));
		recipeInfo.setRecipeInstagramLink(getOrDefault.getOrDefaultToStringNullable(request, "instagramLink", ""));
		recipeInfo.setRecipeVideoLink(getOrDefault.getOrDefaultToStringNullable(request, "videoLink", ""));
		
		recipeService.recipeUpload(recipeInfo);

		res.setResult(result);
		res.setResultCode(ResultCodes.OK.getCode());
		res.setResultMessage(messageUtils.getMessage("TodayChef_STI01"));

		return new ResponseEntity<TodayChefResponse>(res, HttpStatus.OK);
	}

	@GetMapping({"/recipe/list", "/recipe/all", "/receipe/list"})
	public ResponseEntity<TodayChefResponse> RecipeList() throws Exception {
		TodayChefResponse res = new TodayChefResponse();
		List<RecipeDto> recipes = recipeService.recipeList();

		res.setResult(recipes);
		res.setResultCode(ResultCodes.OK.getCode());
		res.setResultMessage(messageUtils.getMessage("TodayChef_STI01"));

		return new ResponseEntity<TodayChefResponse>(res, HttpStatus.OK);
	}

	@GetMapping({"/recipe/my", "/receipe/my"})
	public ResponseEntity<TodayChefResponse> MyRecipeList(HttpServletRequest req) throws Exception {
		TodayChefResponse res = new TodayChefResponse();
		String accessToken = req.getHeader("accessToken");
		String userId = jwtIssuer.decoder(accessToken).get("sub").toString();
		List<RecipeDto> recipes = recipeService.myRecipeList(userId);

		res.setResult(recipes);
		res.setResultCode(ResultCodes.OK.getCode());
		res.setResultMessage(messageUtils.getMessage("TodayChef_STI01"));
		return new ResponseEntity<TodayChefResponse>(res, HttpStatus.OK);
	}

	@GetMapping({"/recipe/detail/{id}", "/recipe/{id}", "/recipe/view/{id}", "/receipe/{id}"})
	public ResponseEntity<TodayChefResponse> RecipeDetail(@PathVariable("id") String id) throws Exception {
		TodayChefResponse res = new TodayChefResponse();
		RecipeDto recipe = recipeService.recipeDetail(id);

		res.setResult(recipe);
		res.setResultCode(ResultCodes.OK.getCode());
		res.setResultMessage(messageUtils.getMessage("TodayChef_STI01"));

		return new ResponseEntity<TodayChefResponse>(res, HttpStatus.OK);
	}

	@PutMapping({"/recipe/{id}", "/receipe/{id}"})
	public ResponseEntity<TodayChefResponse> RecipeUpdate(HttpServletRequest req, @PathVariable("id") String id,
			@RequestBody Map<String, Object> request) throws Exception {
		TodayChefResponse res = new TodayChefResponse();
		String accessToken = req.getHeader("accessToken");
		String userId = jwtIssuer.decoder(accessToken).get("sub").toString();
		Map<String, Object> recipePayload = new HashMap<String, Object>();
		recipePayload.put("info", getOrDefault.getOrDefaultToString(request, "info", "", recipeErrorMessage));
		recipePayload.put("steps", request.get("steps"));

		RecipeDto recipeInfo = new RecipeDto();
		recipeInfo.setId(id);
		recipeInfo.setUserSysId(userId);
		recipeInfo.setRecipeTitle(getOrDefault.getOrDefaultToString(request, "title", "", recipeErrorMessage));
		recipeInfo.setRecipeInfo(getOrDefault.getOrDefaultToString(request, "info", "", recipeErrorMessage));
		recipeInfo.setRecipeSteps(objectMapper.writeValueAsString(recipePayload));
		recipeInfo.setRecipeThumbnailImage(getOrDefault.getOrDefaultToStringNullable(request, "thumbnailImage", ""));
		recipeInfo.setRecipeInstagramLink(getOrDefault.getOrDefaultToStringNullable(request, "instagramLink", ""));
		recipeInfo.setRecipeVideoLink(getOrDefault.getOrDefaultToStringNullable(request, "videoLink", ""));

		int updated = recipeService.recipeUpdate(recipeInfo);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("updated", updated);
		res.setResult(result);
		res.setResultCode(ResultCodes.OK.getCode());
		res.setResultMessage(messageUtils.getMessage("TodayChef_STI01"));
		return new ResponseEntity<TodayChefResponse>(res, HttpStatus.OK);
	}

	@DeleteMapping({"/recipe/{id}", "/receipe/{id}"})
	public ResponseEntity<TodayChefResponse> RecipeDelete(HttpServletRequest req, @PathVariable("id") String id)
			throws Exception {
		TodayChefResponse res = new TodayChefResponse();
		String accessToken = req.getHeader("accessToken");
		String userId = jwtIssuer.decoder(accessToken).get("sub").toString();

		int deleted = recipeService.recipeDelete(id, userId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("deleted", deleted);
		res.setResult(result);
		res.setResultCode(ResultCodes.OK.getCode());
		res.setResultMessage(messageUtils.getMessage("TodayChef_STI01"));
		return new ResponseEntity<TodayChefResponse>(res, HttpStatus.OK);
	}
}
