package com.youandjang.todaychef.member.vo;

import java.sql.Timestamp;

public class MemberDto{
	String userSysId;
	String userLoginId;
	String userPassword;
	String userName;
	String userMail;
	String joinDate;
	String customerId;
	String auth;
	String loginStatusFlag;
	char tempFlag;
	char stopFlag;
	String stopReason;
	Timestamp recUpdateDateTime;
	Timestamp recCreateDatetime;
	Timestamp lastLoginDatetime;
	char logicalDelFlag;
	
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserSysId() {
		return userSysId;
	}
	public void setUserSysId(String userSysId) {
		this.userSysId = userSysId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getLoginStatusFlag() {
		return loginStatusFlag;
	}
	public void setLoginStatusFlag(String loginStatusFlag) {
		this.loginStatusFlag = loginStatusFlag;
	}
	public char getTempFlag() {
		return tempFlag;
	}
	public void setTempFlag(char tempFlag) {
		this.tempFlag = tempFlag;
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
	public Timestamp getLastLoginDatetime() {
		return lastLoginDatetime;
	}
	public void setLastLoginDatetime(Timestamp lastLoginDatetime) {
		this.lastLoginDatetime = lastLoginDatetime;
	}
	public char getLogicalDelFlag() {
		return logicalDelFlag;
	}
	public void setLogicalDelFlag(char logicalDelFlag) {
		this.logicalDelFlag = logicalDelFlag;
	}
	
	
	
}
