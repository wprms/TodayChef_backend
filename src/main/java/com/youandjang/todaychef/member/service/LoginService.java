package com.youandjang.todaychef.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youandjang.todaychef.member.dao.LoginDao;
import com.youandjang.todaychef.member.vo.MemberDto;

@Service
public class LoginService {

	@Autowired 
	public LoginDao mapper; 
	
	public int login(String refreshToken, String userId) throws Exception {
		return mapper.login(refreshToken, userId);
	}
	
	public int logoutToken(Map<String,Object> userInfo) throws Exception{
		return mapper.logoutToken(userInfo);
	}
	
	
	public MemberDto findLoginId(String userId) throws Exception{
		return mapper.findLoginId(userId);
	}
}
