package com.youandjang.todaychef.recipe.vo;

import java.sql.Timestamp;
import java.util.Map;

public class RecipeDto {
	String userSysId;
	String recipeTitle;
	String recipeInfo;
	Map<String, Object> recipeSteps;
	String RecipeInstagramLink;
	String RecipeVideoLink;
	String recCreateId;
	String recUpdateId;
	Timestamp recUpdateDateTime;
	Timestamp recCreateDatetime;
	char logicalDelFlag;
	
	public String getUserSysId() {
		return userSysId;
	}
	public void setUserSysId(String userSysId) {
		this.userSysId = userSysId;
	}
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public String getRecipeInfo() {
		return recipeInfo;
	}
	public void setRecipeInfo(String recipeInfo) {
		this.recipeInfo = recipeInfo;
	}
	public Map<String, Object> getRecipeSteps() {
		return recipeSteps;
	}
	public void setRecipeSteps(Map<String, Object> recipeSteps) {
		this.recipeSteps = recipeSteps;
	}
	public String getRecipeInstagramLink() {
		return RecipeInstagramLink;
	}
	public void setRecipeInstagramLink(String recipeInstagramLink) {
		RecipeInstagramLink = recipeInstagramLink;
	}
	public String getRecipeVideoLink() {
		return RecipeVideoLink;
	}
	public void setRecipeVideoLink(String recipeVideoLink) {
		RecipeVideoLink = recipeVideoLink;
	}
	public String getRecCreateId() {
		return recCreateId;
	}
	public void setRecCreateId(String recCreateId) {
		this.recCreateId = recCreateId;
	}
	public String getRecUpdateId() {
		return recUpdateId;
	}
	public void setRecUpdateId(String recUpdateId) {
		this.recUpdateId = recUpdateId;
	}
	public Timestamp getRecUpdateDateTime() {
		return recUpdateDateTime;
	}
	public void setRecUpdateDateTime(Timestamp recUpdateDateTime) {
		this.recUpdateDateTime = recUpdateDateTime;
	}
	public Timestamp getRecCreateDatetime() {
		return recCreateDatetime;
	}
	public void setRecCreateDatetime(Timestamp recCreateDatetime) {
		this.recCreateDatetime = recCreateDatetime;
	}
	public char getLogicalDelFlag() {
		return logicalDelFlag;
	}
	public void setLogicalDelFlag(char logicalDelFlag) {
		this.logicalDelFlag = logicalDelFlag;
	}

}
