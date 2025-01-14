package com.test.system.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	@GetMapping("/")
	public String board(Model model) {
		
		HashMap<String, String> user = new HashMap<String, String>();
		user.put("username", "test@test.com");
		user.put("password", "java1234");
		user.put("phone", "01012345678");
		model.addAttribute("userDTO", user);
		
		return "page/main";
	}
}
