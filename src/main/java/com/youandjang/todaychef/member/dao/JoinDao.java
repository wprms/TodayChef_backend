package com.youandjang.todaychef.member.dao;

import com.youandjang.todaychef.member.vo.MemberDto;

public interface JoinDao {
	
	public int checkDuplicateUserId(String id) throws Exception;
	public int regist(MemberDto member) throws Exception;
	public String findMaxId() throws Exception;
	
}