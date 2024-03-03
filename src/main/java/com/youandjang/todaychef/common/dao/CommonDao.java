package com.youandjang.todaychef.common.dao;

import java.util.List;

import com.youandjang.todaychef.auth.vo.ScreenMasterDto;
import com.youandjang.todaychef.member.vo.MemberDto;

public interface CommonDao {
	MemberDto findMemberId(String userId) throws Exception;
	List<ScreenMasterDto> findAccessibleScreen(String authCode)  throws Exception;
	String getFileIdxAsFileMgt(String sysId) throws Exception;
}