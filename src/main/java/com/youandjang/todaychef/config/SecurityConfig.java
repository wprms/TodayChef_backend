package com.youandjang.todaychef.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.youandjang.todaychef.auth.JwtAuthenticationFilter;
import com.youandjang.todaychef.auth.JwtAuthenticationProvider;
import com.youandjang.todaychef.auth.JsonWebTokenIssuer;
import com.youandjang.todaychef.auth.social.SocialLoginFailureHandler;
import com.youandjang.todaychef.auth.social.SocialLoginSuccessHandler;
import com.youandjang.todaychef.auth.service.AuthService;
import com.youandjang.todaychef.util.MessageUtils;



@Configuration
public class SecurityConfig {
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@Autowired
	private AuthService authService;

	@Autowired
	private MessageUtils messageUtils;
	@Autowired
	private SocialLoginSuccessHandler socialLoginSuccessHandler;
	@Autowired
	private SocialLoginFailureHandler socialLoginFailureHandler;

	public SecurityConfig(
			AuthenticationManagerBuilder authenticationManagerBuilder,
			JwtAuthenticationProvider jsonWebTokenProvider) {
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.authenticationManagerBuilder.authenticationProvider(jsonWebTokenProvider);
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http, JsonWebTokenIssuer jwtIssuer) throws Exception {
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManagerBuilder.getOrBuild(),
				authService, messageUtils, jwtIssuer);
		
		http
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(
                "/forget/id",
                "/forget/password",
                "/resources/**",
                "/error",
                "/join/**",
                "/login",
                "/social/**",
                "/oauth2/**",
                "/login/oauth2/**"
            ).permitAll()
            .requestMatchers(
                HttpMethod.GET,
                "/recipe/list",
                "/recipe/all",
                "/recipe/detail/**",
                "/recipe/view/**",
                "/recipe/*",
                "/recipe/*/comments",
                "/receipe/list",
                "/receipe/*",
                "/receipe/*/comments"
            ).permitAll()
            .anyRequest().authenticated());
        http.oauth2Login(oauth2 -> oauth2
			.successHandler(socialLoginSuccessHandler)
			.failureHandler(socialLoginFailureHandler));
        http.csrf(csrf -> csrf.disable())
        	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

    return http.build();
}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
