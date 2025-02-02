package com.test.system.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	@GetMapping("/")
	public String board() {

		return "page/main";
	}
	
	@GetMapping("/my")
	public String my(Model model) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
		GrantedAuthority auth = iterator.next();
		String role = auth.getAuthority();
		
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		model.addAttribute("authentication", SecurityContextHolder.getContext().getAuthentication());

		return "page/my";
	}
}
