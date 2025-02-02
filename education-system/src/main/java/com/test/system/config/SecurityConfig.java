package com.test.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.test.system.service.CustomOAuth2UserService;

import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers((headerConfig) -> headerConfig.frameOptions((frameOptionConfig -> frameOptionConfig.disable())));

		http.authorizeHttpRequests((auth) -> auth
										.requestMatchers("/", "/login", "/signup", "/signupok").permitAll()
										.requestMatchers("/board", "/board/view/**").permitAll()
										.requestMatchers("/", "/login/**", "/oauth2/**").permitAll() 	//"/login/**"로그인 주소 포함 모든 주소 다 ex. /login/admin, /login/member..
										.anyRequest().authenticated()
				);

		
		//http.csrf(csrf -> csrf.ignoringRequestMatchers("/**"));

		
		//http.csrf(auth -> auth.disable());
		
		http.formLogin(auth -> auth
									.loginPage("/login")
									.defaultSuccessUrl("/")
									.loginProcessingUrl("/loginok").permitAll());
		
		//소셜 로그인 설정
		http.oauth2Login(auth -> auth
					.loginPage("/login")
					.userInfoEndpoint(config -> config.userService(customOAuth2UserService))	//config.userService(객체)
		);
		
		return http.build();

	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception {
	 * 
	 * http.logout(auth -> auth .logoutUrl("/logout") .logoutSuccessUrl("/"));
	 * 
	 * return http.build(); }
	 */
}
