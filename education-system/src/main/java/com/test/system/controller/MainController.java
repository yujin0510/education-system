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
	public String board() {

		return "page/main";
	}
	
	@GetMapping("/my")
	public String my() {

		return "page/my";
	}
}
