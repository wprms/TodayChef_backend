package com.youandjang.todaychef.member.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.youandjang.todaychef.member.dao.JoinDao;
import com.youandjang.todaychef.member.exception.JoinException;
import com.youandjang.todaychef.member.vo.MemberDto;
import com.youandjang.todaychef.util.MessageUtils;

@Service
public class JoinService {
	
	@Autowired
	public JoinDao mapper;	
	@Autowired
	MessageUtils messageUtils;
	
	public boolean isUserIdDuplicate(String id) throws Exception{
		return mapper.checkDuplicateUserId(id)>0;	
	}	
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void Join(MemberDto member, String password) throws Exception{
		
		int cnt = regist(member);
		if(cnt==0) {
			String messages = messageUtils.getMessage("TodayChef_MBB08");
			throw new JoinException(messages,"MBB08");
		}			
	}
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public int regist(MemberDto member) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		
		String cpIdFromDB = mapper.findMaxId();
		String userId  = "";
		if(cpIdFromDB != null) {
		int cpSequence = Integer.parseInt(cpIdFromDB.substring(1));
		cpSequence++;
		String cpSequenceToString = String.valueOf(cpSequence);
		int calDigitNum =  cpSequenceToString.length();
		if(calDigitNum < 0) {
			String messages = messageUtils.getMessage("TodayChef_MBB06");
			throw new JoinException(messages, "MBB06");
		}
		userId = cpSequenceToString;
		} else {
			userId = "001";
		}
		
		
		member.setUserSysId(userId);
		member.setJoinDate(strToday);
		
		int cnt = mapper.regist(member);
		
		return cnt;
	}		
	
	public String findMaxId() throws Exception {
		return mapper.findMaxId();
	}
	
}