package com.youandjang.todaychef.recipe.dao;


import org.springframework.stereotype.Repository;

import com.youandjang.todaychef.config.HelpSqlSessionTemplate;
import com.youandjang.todaychef.recipe.vo.RecipeDto;

@Repository
public class RecipeDaoImpl extends HelpSqlSessionTemplate implements RecipeDao {

	@Override
	public int recipeUpload(RecipeDto recipeInfo) {
		return getSqlSessionTemplate().insert("recipeMapper.recipeUpload", recipeInfo);
	}

}
