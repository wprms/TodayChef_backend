package com.youandjang.todaychef.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class MessageConfig {
	
	@Bean
	public LocaleResolver defaultLocaleResolver() {
	    AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
	    localeResolver.setDefaultLocale(Locale.JAPANESE);
	    return localeResolver;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
	    Locale.setDefault(Locale.JAPANESE); // 提供しない言語に対する処理。
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:/messages/messages");
	    messageSource.setDefaultEncoding(Encoding.DEFAULT_CHARSET.toString());
	    messageSource.setDefaultLocale(Locale.getDefault());
	    messageSource.setCacheSeconds(600);
	    return messageSource; 
	} 

	@Bean
	public MessageSourceAccessor messageSourceAccessor (@Autowired ReloadableResourceBundleMessageSource messageSource) { 
	    return new MessageSourceAccessor(messageSource);
	}
}