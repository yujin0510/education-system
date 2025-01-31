package com.test.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.system.model.MemberDTO;
import com.test.system.service.MemberService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/signup")
	public String signup(Model model) {

		return "page/signup";
	}
	
	@PostMapping("/signupok")
	public String signupok(@ModelAttribute MemberDTO dto) {
		
		System.out.println("dto: " + dto);
		
		memberService.signup(dto);
		
		return "redirect:/login";
	}
	

}
