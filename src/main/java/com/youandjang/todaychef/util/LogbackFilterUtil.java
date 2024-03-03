package com.youandjang.todaychef.util;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogbackFilterUtil extends Filter<ILoggingEvent>{
	@Override
	public FilterReply decide(ILoggingEvent event) {
			
        if (event.getMessage().contains("password")) {
            return FilterReply.DENY;
        }else{
            return FilterReply.ACCEPT;
        }
		
	}
}