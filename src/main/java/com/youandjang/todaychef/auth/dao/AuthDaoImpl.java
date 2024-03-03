package com.youandjang.todaychef.auth.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.youandjang.todaychef.auth.constants.User;
import com.youandjang.todaychef.auth.vo.AuthInfoDto;
import com.youandjang.todaychef.config.HelpSqlSessionTemplate;

@Repository
public class AuthDaoImpl extends HelpSqlSessionTemplate implements AuthDao {

	@Override
	public AuthInfoDto authInfoFind(String userId) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userId", userId);
		
		paramMap.put(User.SQL_TABLE_PARAM.getCode(), User.MEMBER_TABLE_NAME.getCode());
		paramMap.put(User.SQL_COLUMN_PARAM.getCode(), User.MEMBER_COLUMN_NAME.getCode());

		return getSqlSessionTemplate().selectOne("authMapper.authInfoFind", paramMap);
	}
}
