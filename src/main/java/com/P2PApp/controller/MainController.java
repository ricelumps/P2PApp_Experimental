package com.P2PApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/index")
	private String index() {
		return "page/main";
	}
	

	@RequestMapping("/")
	private String login() {
		return "page/login";
	}
	
}