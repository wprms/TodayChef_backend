package com.youandjang.todaychef.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youandjang.todaychef.auth.vo.ScreenMasterDto;
import com.youandjang.todaychef.common.service.CommonService;
import com.youandjang.todaychef.error.constants.ScreenCodes;
import com.youandjang.todaychef.error.exception.TodayChefAuthException;
import com.youandjang.todaychef.member.vo.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthCheckUtil {
   @Autowired
   MessageUtils messegeUtils;
   @Autowired
   CommonService commonService;
   
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   
   public String authCheck(Enum<ScreenCodes> scrnCode, HttpServletRequest req) throws Exception{

      List<ScreenMasterDto> authScrns = new ArrayList<ScreenMasterDto>();
        String userId = TakeIdUtil.takeIdUtility(req);

      boolean authFlg = false;
      String authCode = "";
      
      MemberDto dto = new MemberDto();
      
      dto = commonService.findMemberId(userId);
      authCode = dto.getAuth();
      
      authScrns = (List<ScreenMasterDto>) commonService.findAccessibleScreen(authCode);

      if (authScrns == null) {
         String errorMsg = messegeUtils.getMessage("TodayChef_STB04");
         logger.error(errorMsg);
          throw new TodayChefAuthException(errorMsg, "B04");
      }

      for (ScreenMasterDto scrnDto : authScrns) {
         if (scrnCode.toString().equals(scrnDto.getScreenCode())) {
            authFlg = true;
            break;
         }
      }
      
      if (!authFlg) {
         String errorMsg = messegeUtils.getMessage("TodayChef_STB04");
         logger.error(errorMsg);
          throw new TodayChefAuthException(errorMsg, "B04");
      }
      
      return userId;
   }
}