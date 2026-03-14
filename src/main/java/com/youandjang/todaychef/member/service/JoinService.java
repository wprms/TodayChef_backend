package com.youandjang.todaychef.member.service;

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

	public boolean isUserIdDuplicate(String userId) throws Exception {
		return mapper.checkDuplicateUserId(userId) > 0;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void Join(MemberDto member) throws Exception {

		int cnt = regist(member);
		if (cnt == 0) {
			String messages = messageUtils.getMessage("TodayChef_MBB08");
			throw new JoinException(messages, "MBB08");
		}
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public int regist(MemberDto member) throws Exception {

		String cpIdFromDB = mapper.findMaxId();
		String userId = "";
		if (cpIdFromDB != null) {
			int cpSequence = Integer.parseInt(cpIdFromDB.substring(1));
			cpSequence++;
			String cpSequenceToString = String.format("C%07d", cpSequence);
			int calDigitNum = cpSequenceToString.length();
			if (calDigitNum > 8) {
				String messages = messageUtils.getMessage("TodayChef_MBB06");
				throw new JoinException(messages, "MBB06");
			}
			userId = cpSequenceToString;
		} else {
			userId = "C0000000";
		}

		member.setUserSysId(userId);

		int cnt = mapper.regist(member);

		return cnt;
	}

	public String findMaxId() throws Exception {
		return mapper.findMaxId();
	}

}
