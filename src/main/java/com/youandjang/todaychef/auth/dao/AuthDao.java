package com.youandjang.todaychef.auth.dao;

import com.youandjang.todaychef.auth.vo.AuthInfoDto;

public interface AuthDao {
	public AuthInfoDto authInfoFind(String userId) throws Exception;
}
