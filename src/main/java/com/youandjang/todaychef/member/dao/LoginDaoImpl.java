package com.youandjang.todaychef.member.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youandjang.todaychef.config.HelpSqlSessionTemplate;
import com.youandjang.todaychef.member.vo.MemberDto;


@Repository
public class LoginDaoImpl extends HelpSqlSessionTemplate implements LoginDao {

	@Override
	public int login(String refreshToken, String userId) throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("refreshToken", refreshToken);
		param.put("userId", userId);
		return getSqlSessionTemplate().update("memberMapper.login", param);
	}

	@Override
	public MemberDto findLoginId(String userId) throws Exception {
		return getSqlSessionTemplate().selectOne("memberMapper.findLoginId", userId);
	}

	@Override
	public int logoutToken(Map<String, Object> userInfo) throws Exception {
		return getSqlSessionTemplate().update("memberMapper.logoutToken", userInfo);
	}

}
