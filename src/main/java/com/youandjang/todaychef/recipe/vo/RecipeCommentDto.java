package com.youandjang.todaychef.recipe.vo;

import java.sql.Timestamp;

public class RecipeCommentDto {
	String commentId;
	String recipeId;
	String userSysId;
	String writerId;
	String commentText;
	Timestamp recCreateDatetime;

	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}
	public String getUserSysId() {
		return userSysId;
	}
	public void setUserSysId(String userSysId) {
		this.userSysId = userSysId;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public Timestamp getRecCreateDatetime() {
		return recCreateDatetime;
	}
	public void setRecCreateDatetime(Timestamp recCreateDatetime) {
		this.recCreateDatetime = recCreateDatetime;
	}
}
