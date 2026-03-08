package com.youandjang.todaychef.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youandjang.todaychef.recipe.dao.RecipeDao;
import com.youandjang.todaychef.recipe.vo.RecipeDto;

@Service
public class RecipeService {

	@Autowired
	public RecipeDao mapper;
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	public int recipeUpload(RecipeDto recipeInfo) throws Exception {
		return mapper.recipeUpload(recipeInfo);
	}
	
	public List<RecipeDto> recipeList() throws Exception {
		List<RecipeDto> recipeList = mapper.recipeList();
		for (RecipeDto recipeDto : recipeList) {
			normalizeRecipePayload(recipeDto);
		}
		return recipeList;
	}

	public List<RecipeDto> myRecipeList(String userId) throws Exception {
		List<RecipeDto> recipeList = mapper.myRecipeList(userId);
		for (RecipeDto recipeDto : recipeList) {
			normalizeRecipePayload(recipeDto);
		}
		return recipeList;
	}
	
	public RecipeDto recipeDetail(String id) throws Exception {
		RecipeDto recipeDto = mapper.recipeDetail(id);
		if (recipeDto != null) {
			normalizeRecipePayload(recipeDto);
		}
		return recipeDto;
	}

	public int recipeUpdate(RecipeDto recipeInfo) throws Exception {
		return mapper.recipeUpdate(recipeInfo);
	}

	public int recipeDelete(String id, String userId) throws Exception {
		return mapper.recipeDelete(id, userId);
	}

	private void normalizeRecipePayload(RecipeDto recipeDto) {
		String rawSteps = recipeDto.getRecipeSteps();
		if (rawSteps == null) {
			recipeDto.setRecipeInfo("");
			recipeDto.setRecipeSteps("[]");
			return;
		}

		try {
			JsonNode parsed = objectMapper.readTree(rawSteps);
			if (parsed.isObject()) {
				JsonNode info = parsed.get("info");
				JsonNode steps = parsed.get("steps");
				if (info != null && !info.isNull()) {
					recipeDto.setRecipeInfo(info.asText());
				} else if (recipeDto.getRecipeInfo() == null) {
					recipeDto.setRecipeInfo("");
				}
				recipeDto.setRecipeSteps(steps != null ? objectMapper.writeValueAsString(steps) : "[]");
				return;
			}
			if (parsed.isArray()) {
				if (recipeDto.getRecipeInfo() == null) {
					recipeDto.setRecipeInfo("");
				}
				recipeDto.setRecipeSteps(objectMapper.writeValueAsString(parsed));
				return;
			}
		} catch (Exception e) {
			// Keep backward compatibility for legacy payload.
		}

		if (recipeDto.getRecipeInfo() == null) {
			recipeDto.setRecipeInfo("");
		}
		recipeDto.setRecipeSteps("[]");
	}

}
