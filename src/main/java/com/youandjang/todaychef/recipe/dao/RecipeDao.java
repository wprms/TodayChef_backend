package com.youandjang.todaychef.recipe.dao;

import java.util.List;

import com.youandjang.todaychef.recipe.vo.RecipeCommentDto;
import com.youandjang.todaychef.recipe.vo.RecipeCommentNotificationDto;
import com.youandjang.todaychef.recipe.vo.RecipeDto;

public interface RecipeDao {
	
	int recipeUpload(RecipeDto recipeInfo);
	
	List<RecipeDto> recipeList();

	List<RecipeDto> myRecipeList(String userId);
	
	RecipeDto recipeDetail(String id);
	
	int increaseViewCount(String id);
	
	List<RecipeCommentDto> recipeCommentList(String recipeId);
	
	int recipeCommentCreate(RecipeCommentDto commentInfo);
	
	List<RecipeCommentNotificationDto> recipeCommentNotifications(String userSysId);

	int recipeUpdate(RecipeDto recipeInfo);

	int recipeDelete(String id, String userId);
	
}
