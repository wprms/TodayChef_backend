package com.youandjang.todaychef.recipe.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youandjang.todaychef.config.HelpSqlSessionTemplate;
import com.youandjang.todaychef.recipe.vo.RecipeCommentDto;
import com.youandjang.todaychef.recipe.vo.RecipeCommentNotificationDto;
import com.youandjang.todaychef.recipe.vo.RecipeDto;

@Repository
public class RecipeDaoImpl extends HelpSqlSessionTemplate implements RecipeDao {

	@Override
	public int recipeUpload(RecipeDto recipeInfo) {
		return getSqlSessionTemplate().insert("recipeMapper.recipeUpload", recipeInfo);
	}

	@Override
	public List<RecipeDto> recipeList() {
		return getSqlSessionTemplate().selectList("recipeMapper.recipeList");
	}

	@Override
	public List<RecipeDto> myRecipeList(String userId) {
		return getSqlSessionTemplate().selectList("recipeMapper.myRecipeList", userId);
	}

	@Override
	public RecipeDto recipeDetail(String id) {
		return getSqlSessionTemplate().selectOne("recipeMapper.recipeDetail", id);
	}
	
	@Override
	public int increaseViewCount(String id) {
		return getSqlSessionTemplate().update("recipeMapper.increaseViewCount", id);
	}
	
	@Override
	public List<RecipeCommentDto> recipeCommentList(String recipeId) {
		return getSqlSessionTemplate().selectList("recipeMapper.recipeCommentList", recipeId);
	}
	
	@Override
	public int recipeCommentCreate(RecipeCommentDto commentInfo) {
		return getSqlSessionTemplate().insert("recipeMapper.recipeCommentCreate", commentInfo);
	}
	
	@Override
	public List<RecipeCommentNotificationDto> recipeCommentNotifications(String userSysId) {
		return getSqlSessionTemplate().selectList("recipeMapper.recipeCommentNotifications", userSysId);
	}

	@Override
	public int recipeUpdate(RecipeDto recipeInfo) {
		return getSqlSessionTemplate().update("recipeMapper.recipeUpdate", recipeInfo);
	}

	@Override
	public int recipeDelete(String id, String userId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		param.put("userId", userId);
		return getSqlSessionTemplate().update("recipeMapper.recipeDelete", param);
	}

}
