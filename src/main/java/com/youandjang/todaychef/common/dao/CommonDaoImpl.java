package com.youandjang.todaychef.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youandjang.todaychef.auth.vo.ScreenMasterDto;
import com.youandjang.todaychef.config.HelpSqlSessionTemplate;
import com.youandjang.todaychef.member.vo.MemberDto;

@Repository
public class CommonDaoImpl extends HelpSqlSessionTemplate implements CommonDao {

	@Override
	public MemberDto findMemberId(String userId) throws Exception {
		return getSqlSessionTemplate().selectOne("commonMapper.findMemberId", userId);
	}

	@Override
	public String getFileIdxAsFileMgt(String sysId) throws Exception {
		List<String> resultList = getSqlSessionTemplate().selectList("commonMapper.getFileIdxAsFileMgt", sysId);
		
		String resultArrayStr = resultList.toString();
		resultArrayStr = resultArrayStr.replace("[", "");
		resultArrayStr = resultArrayStr.replace("]", "");
		return resultArrayStr;
	}

	@Override
	public List<ScreenMasterDto> findAccessibleScreen(String authCode) throws Exception {
		return getSqlSessionTemplate().selectList("commonMapper.findAccessibleScreen", authCode);
	}
}