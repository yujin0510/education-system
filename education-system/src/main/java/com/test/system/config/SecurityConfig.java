package com.test.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers((headerConfig) -> headerConfig.frameOptions((frameOptionConfig -> frameOptionConfig.disable())));

		http.authorizeHttpRequests((auth) -> auth.requestMatchers("/**").permitAll().anyRequest().authenticated());

		http.csrf(csrf -> csrf.ignoringRequestMatchers("/**"));

		http.csrf(auth -> auth.disable());
		return http.build();

	}
}
