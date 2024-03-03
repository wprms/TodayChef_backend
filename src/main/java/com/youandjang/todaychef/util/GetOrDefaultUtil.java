package com.youandjang.todaychef.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youandjang.todaychef.error.exception.TodayChefException;

@Service
public class GetOrDefaultUtil{
	
	@Autowired
	   MessageUtils messageUtils;
	   
	   public String getOrDefaultToString(Map<String, Object> request, String key, Object defualt) throws Exception   {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))){
	         String messages = messageUtils.getMessage("TodayChef_CMB06");
	           throw new TodayChefException(messages, "CMB06");
	      } else {
	         return request.getOrDefault(key, defualt).toString();
	      }
	   }
	   
	   public String getOrDefaultToString(Map<String, Object> request, String key, Object defualt, String msg) throws Exception   {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))){
	         String messages = messageUtils.getMessage(msg);
	           throw new TodayChefException(messages, substringCode(msg));
	      } else {
	         return request.getOrDefault(key, defualt).toString();
	      }
	   }

	  public String getOrDefaultToString(Map<String, Object> request, String key, Object defualt, String msg, boolean numeric) throws Exception   {
	      if (ObjectUtil.isEmpty(request.getOrDefault(key, defualt))) {
	         String messages = messageUtils.getMessage(msg);
	         throw new TodayChefException(messages, substringCode(msg));
	      } else {
	         if (!StringUtils.isNumeric(request.getOrDefault(key, defualt).toString())) {
	            String messages = messageUtils.getMessage(msg);
	            throw new TodayChefException(messages, substringCode(msg));
	         } else {
	            return request.getOrDefault(key, defualt).toString();
	         }
	      }
	   }
	   
	   public String getOrDefaultToStringNotNull(Map<String, Object> request, String key, Object defualt)  throws Exception {
	      if(request.getOrDefault(key, defualt).equals(null)){
	         String messages = messageUtils.getMessage("TodayChef_CMB06");
	         throw new TodayChefException(messages, "CMB06");
	      } else {
	         return request.getOrDefault(key, defualt).toString();
	      }
	   }

	   public String getOrDefaultToStringNotNull(Map<String, Object> request, String key, Object defualt, String msg)  throws Exception {
	      if(request.getOrDefault(key, defualt).equals(null)){
	         String messages = messageUtils.getMessage(msg);
	           throw new TodayChefException(messages, substringCode(msg));
	      } else {
	         return request.getOrDefault(key, defualt).toString();
	      }
	   }
	   
	   public String getOrDefaultToStringNullable(Map<String, Object> request, String key, Object defualt) {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))){
	         return defualt.toString();
	      } else {
	         return request.getOrDefault(key, defualt).toString();
	      }
	   }
	   
	   public long getOrDefaultToLong(Map<String, Object> request, String key, Object defualt) throws Exception  {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))) {
	         String messages = messageUtils.getMessage("TodayChef_CMB06");
	            throw new TodayChefException(messages, "CMB06");
	      } else {
	         try {
	            return Long.parseLong(request.getOrDefault(key, defualt).toString());
	         } catch(Exception e) {
	            String messages = messageUtils.getMessage("TodayChef_CMB06");
	               throw new TodayChefException(messages, "CMB06");
	         }
	      }
	   }
	   
	   public long getOrDefaultToLong(Map<String, Object> request, String key, Object defualt, String msg) throws Exception  {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))) {
	         String messages = messageUtils.getMessage(msg);
	           throw new TodayChefException(messages, substringCode(msg));
	      }else {
	         try {
	            return Long.parseLong(request.getOrDefault(key, defualt).toString());
	         } catch(Exception e){
	            String messages = messageUtils.getMessage(msg);
	              throw new TodayChefException(messages, substringCode(msg));
	         }
	      }
	   }
	   
	   public int getOrDefaultToInteger(Map<String, Object> request, String key, Object defualt) throws Exception  {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))) {
	         String messages = messageUtils.getMessage("TodayChef_CMB06");
	            throw new TodayChefException(messages, "CMB06");
	      } else {
	         try {
	            return Integer.parseInt(request.getOrDefault(key, defualt).toString());
	         } catch(Exception e){
	            String messages = messageUtils.getMessage("TodayChef_CMB07");
	               throw new TodayChefException(messages, "B07");
	         }
	      }
	   }
	   
	   public int getOrDefaultToInteger(Map<String, Object> request, String key, Object defualt, String msg) throws Exception  {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))) {
	         String messages = messageUtils.getMessage(msg);
	           throw new TodayChefException(messages, substringCode(msg));
	      } else {
	         try {
	            return Integer.parseInt(request.getOrDefault(key, defualt).toString());
	         } catch(Exception e){
	            String messages = messageUtils.getMessage(msg);
	              throw new TodayChefException(messages, substringCode(msg));
	         }
	      }
	   }
	   
	   public Long getOrDefaultToLongClass(Map<String, Object> request, String key, Object defualt) throws Exception {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))) {
	         return null;
	      } else {
	         try {
	            return Long.parseLong(request.getOrDefault(key, defualt).toString());
	         } catch(Exception e){
	            String messages = messageUtils.getMessage("TodayChef_CMB06");
	               throw new TodayChefException(messages, "CMB06");
	         }
	      }
	   }
	   
	   public Long getOrDefaultToLongClass(Map<String, Object> request, String key, Object defualt, String msg) throws Exception {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))) {
	         String messages = messageUtils.getMessage(msg);
	           throw new TodayChefException(messages, substringCode(msg));
	      } else {
	         try {
	            return Long.parseLong(request.getOrDefault(key, defualt).toString());
	         } catch(Exception e){
	            String messages = messageUtils.getMessage(msg);
	              throw new TodayChefException(messages, substringCode(msg));
	         }
	      }
	   }
	   
	   public char getOrDefaultToChar(Map<String, Object> request, String key, Object defualt)throws Exception  {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))) {
	         String messages = messageUtils.getMessage("TodayChef_CMB06");
	            throw new TodayChefException(messages, "CMB06");
	      } else {
	         String temp = request.getOrDefault(key, defualt).toString();
	         if(temp.length() != 1) {
	            String messages = messageUtils.getMessage("TodayChef_CMB06");
	               throw new TodayChefException(messages, "CMB06");
	         } else {
	            return temp.charAt(0);
	         }
	      }
	   }
	   
	   public char getOrDefaultToChar(Map<String, Object> request, String key, Object defualt, String msg)throws Exception  {
	      if(ObjectUtil.isEmpty(request.getOrDefault(key, defualt))) {
	         String messages = messageUtils.getMessage(msg);
	           throw new TodayChefException(messages, substringCode(msg));
	      } else {
	         String temp = request.getOrDefault(key, defualt).toString();
	         if(temp.length() != 1) {
	            String messages = messageUtils.getMessage(msg);
	              throw new TodayChefException(messages, substringCode(msg));
	         } else {
	            return temp.charAt(0);
	         }
	      }
	   }   
	   
	   public String substringCode (String msg) {
	      String result = msg.substring(msg.length()-5, msg.length());
	      return result;
	   }
	
	
}
