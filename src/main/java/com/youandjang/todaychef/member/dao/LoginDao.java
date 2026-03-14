package com.youandjang.todaychef.member.dao;

import java.util.Map;

import com.youandjang.todaychef.member.vo.MemberDto;

public interface LoginDao {
	int login(String refreshToken, String userId) throws Exception;

	public int logoutToken(Map<String, Object> userInfo) throws Exception;

	MemberDto findLoginId(String userId) throws Exception;

	MemberDto findBySocialAccount(String provider, String socialUserId) throws Exception;

	MemberDto findByUserSysId(String userSysId) throws Exception;
}
