package com.test.system.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequiredArgsConstructor	
public class AuthController {

	@GetMapping("/login")
	public String login(Model model) {

		return "page/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect:/";
	}
	
	
}//class
