package com.youandjang.todaychef.recipe.dao;

import com.youandjang.todaychef.recipe.vo.RecipeDto;

public interface RecipeDao {
	
	int recipeUpload(RecipeDto recipeInfo);
	
}
