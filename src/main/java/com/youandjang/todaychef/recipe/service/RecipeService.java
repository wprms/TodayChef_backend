package com.youandjang.todaychef.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youandjang.todaychef.recipe.dao.RecipeDao;
import com.youandjang.todaychef.recipe.vo.RecipeDto;

@Service
public class RecipeService {

	@Autowired
	public RecipeDao mapper;

	public int recipeUpload(RecipeDto recipeInfo) throws Exception {
		return mapper.recipeUpload(recipeInfo);
	}

}
