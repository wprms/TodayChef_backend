package com.youandjang.todaychef.recipe.vo;

import java.sql.Timestamp;

public class RecipeDto {
	String id;
	String userSysId;
	String recipeTitle;
	String recipeInfo;
	String recipeIngredients;
	String recipeSteps;
	int viewCount;
	String recipeThumbnailImage;
	String recipeInstagramLink;
	String recipeVideoLink;
	String recipeVideoFile;
	String recCreateId;
	String recUpdateId;
	Timestamp recUpdateDateTime;
	Timestamp recCreateDatetime;
	char logicalDelFlag;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
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
	public String getRecipeIngredients() {
		return recipeIngredients;
	}
	public void setRecipeIngredients(String recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}
	public String getRecipeSteps() {
		return recipeSteps;
	}
	public void setRecipeSteps(String recipeSteps) {
		this.recipeSteps = recipeSteps;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getRecipeThumbnailImage() {
		return recipeThumbnailImage;
	}
	public void setRecipeThumbnailImage(String recipeThumbnailImage) {
		this.recipeThumbnailImage = recipeThumbnailImage;
	}
	public String getRecipeInstagramLink() {
		return recipeInstagramLink;
	}
	public void setRecipeInstagramLink(String recipeInstagramLink) {
		this.recipeInstagramLink = recipeInstagramLink;
	}
	public String getRecipeVideoLink() {
		return recipeVideoLink;
	}
	public void setRecipeVideoLink(String recipeVideoLink) {
		this.recipeVideoLink = recipeVideoLink;
	}
	public String getRecipeVideoFile() {
		return recipeVideoFile;
	}
	public void setRecipeVideoFile(String recipeVideoFile) {
		this.recipeVideoFile = recipeVideoFile;
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
