package com.youandjang.todaychef.member.dao;

import org.springframework.stereotype.Repository;

import com.youandjang.todaychef.config.HelpSqlSessionTemplate;
import com.youandjang.todaychef.member.vo.MemberDto;

@Repository
public class JoinDaoImpl extends HelpSqlSessionTemplate implements JoinDao {

	@Override
	public int checkDuplicateUserId(String id) throws Exception {
		return getSqlSessionTemplate().selectOne("memberMapper.checkDuplicateUserId", id);
	}

	@Override
	public int regist(MemberDto member) throws Exception {
		return getSqlSessionTemplate().insert("memberMapper.regist", member);
	}

	@Override
	public String findMaxId() throws Exception {
		return getSqlSessionTemplate().selectOne("memberMapper.findMaxId");
	}
	
}