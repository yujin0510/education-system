package com.test.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers((headerConfig) -> headerConfig.frameOptions((frameOptionConfig -> frameOptionConfig.disable())));

		http.authorizeHttpRequests((auth) -> auth
										.requestMatchers("/", "/login", "/signup", "/signupok").permitAll()
										.requestMatchers("/board", "/board/view/**").permitAll()
										.anyRequest().authenticated()
				);

		
		//http.csrf(csrf -> csrf.ignoringRequestMatchers("/**"));

		
		//http.csrf(auth -> auth.disable());
		
		http.formLogin(auth -> auth
									.loginPage("/login")
									.defaultSuccessUrl("/")
									.loginProcessingUrl("/loginok").permitAll());
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
