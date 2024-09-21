package com.youandjang.todaychef.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.youandjang.todaychef.auth.JwtAuthenticationFilter;
import com.youandjang.todaychef.auth.JwtAuthenticationProvider;
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
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManagerBuilder.getOrBuild(),
				authService, messageUtils);
		
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
                "/logout" // 로그아웃 경로 허용
            ).permitAll()
            .anyRequest().authenticated())
        .logout(logout -> logout
            .logoutUrl("/logout") // 로그아웃 요청 URL
            .invalidateHttpSession(true) // 세션 무효화
            .clearAuthentication(true) // 인증 정보 제거
            .logoutSuccessUrl("/login") // 로그아웃 성공 시 리다이렉트할 경로 설정
        )
        .csrf(csrf -> csrf.ignoringRequestMatchers("/**")); // 모든 경로에 대해 CSRF 비활성화

    return http.build();
}


}