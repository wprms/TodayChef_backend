package com.youandjang.todaychef.member.vo;

import java.sql.Timestamp;

public class MemberDto{
	String userSysId;
	String lastGeneFlag;
	String userAuth;
	String todaychefToken;
	String userLoginId;
	String userPassword;
	String userMail;
	String loginStatusFlag;
	Timestamp lastLoginDatetime;
	char stopFlag;
	String stopReason;
	char tempFlag;
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
	public String getLastGeneFlag() {
		return lastGeneFlag;
	}
	public void setLastGeneFlag(String lastGeneFlag) {
		this.lastGeneFlag = lastGeneFlag;
	}
	public String getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}
	public String getTodaychefToken() {
		return todaychefToken;
	}
	public void setTodaychefToken(String todaychefToken) {
		this.todaychefToken = todaychefToken;
	}
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getLoginStatusFlag() {
		return loginStatusFlag;
	}
	public void setLoginStatusFlag(String loginStatusFlag) {
		this.loginStatusFlag = loginStatusFlag;
	}
	public Timestamp getLastLoginDatetime() {
		return lastLoginDatetime;
	}
	public void setLastLoginDatetime(Timestamp lastLoginDatetime) {
		this.lastLoginDatetime = lastLoginDatetime;
	}
	public char getStopFlag() {
		return stopFlag;
	}
	public void setStopFlag(char stopFlag) {
		this.stopFlag = stopFlag;
	}
	public String getStopReason() {
		return stopReason;
	}
	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}
	public char getTempFlag() {
		return tempFlag;
	}
	public void setTempFlag(char tempFlag) {
		this.tempFlag = tempFlag;
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
