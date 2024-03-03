package com.youandjang.todaychef.auth.vo;

import java.sql.Timestamp;

public class AuthInfoDto {
	private String statusFlag;
	private String todaychefToken;
	private Timestamp lastLoginDatetime;

	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public Timestamp getLastLoginDatetime() {
		return lastLoginDatetime;
	}
	public void setLastLoginDatetime(Timestamp lastLoginDatetime) {
		this.lastLoginDatetime = lastLoginDatetime;
	}
	public String getTodaychefToken() {
		return todaychefToken;
	}
	public void setTodaychefToken(String todaychefToken) {
		this.todaychefToken = todaychefToken;
	}
}