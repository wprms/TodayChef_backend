package com.youandjang.todaychef.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

@Service
public class MessageUtils extends MessageSourceAccessor{
	
	public MessageUtils(MessageSource messageSource) {
		super(messageSource);
	}
	
	public String getMessage(String code) throws NoSuchMessageException{
		Locale dfLocale = Locale.getDefault();
		return super.getMessage(code, dfLocale);
	}
	
	public String getMessage(String code, Object[ ] args) throws NoSuchMessageException {
		Locale dfLocale = Locale.getDefault();
		return super.getMessage(code, args, dfLocale);
	}
}
