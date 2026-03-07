package com.youandjang.todaychef.recipe.dao;

import java.util.List;

import com.youandjang.todaychef.recipe.vo.RecipeDto;

public interface RecipeDao {
	
	int recipeUpload(RecipeDto recipeInfo);
	
	List<RecipeDto> recipeList();

	List<RecipeDto> myRecipeList(String userId);
	
	RecipeDto recipeDetail(String id);

	int recipeUpdate(RecipeDto recipeInfo);

	int recipeDelete(String id, String userId);
	
}
