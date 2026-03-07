package com.youandjang.todaychef.auth.dao;

import org.springframework.stereotype.Repository;

import com.youandjang.todaychef.auth.vo.AuthInfoDto;
import com.youandjang.todaychef.config.HelpSqlSessionTemplate;

@Repository
public class AuthDaoImpl extends HelpSqlSessionTemplate implements AuthDao {

	@Override
	public AuthInfoDto authInfoFind(String userId) throws Exception {
		return getSqlSessionTemplate().selectOne("authMapper.authInfoFind", userId);
	}
}
