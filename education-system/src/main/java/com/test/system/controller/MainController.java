package com.test.system.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.system.model.MemberDTO;
import com.test.system.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MemberService memberservice;
	
	@GetMapping("/")
	public String board(Model model) {
		List<MemberDTO> list = memberservice.findAll();

		model.addAttribute("dto", list);
		
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
