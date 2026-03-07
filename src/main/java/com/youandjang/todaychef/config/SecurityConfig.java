package com.youandjang.todaychef.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import com.youandjang.todaychef.auth.service.AuthService;
import com.youandjang.todaychef.util.MessageUtils;



@Configuration
public class SecurityConfig {
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@Autowired
	private AuthService authService;

	@Autowired
	private MessageUtils messageUtils;

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
                "/login"
            ).permitAll()
            .anyRequest().authenticated());
        http.csrf(csrf -> csrf.disable())
        	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
