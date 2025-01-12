package com.test.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MemberController {
	
	@GetMapping("/board")
	public String board() {
		return "page/board";
	}
	

}
