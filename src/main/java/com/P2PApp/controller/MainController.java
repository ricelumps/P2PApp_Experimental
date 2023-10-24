package com.P2PApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
	

@Slf4j
@Controller
public class MainController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/index")
	private String index() {
		
		if ( session.getAttribute("loginStatus") != null ) {
			return "page/main";
			
		} else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping("/redisTest")
	private String redisTest(Model model) {
		
		String status = (String) session.getAttribute("loginStatus");
		
		model.addAttribute("loginStatus", status);
		log.info("status : {}", status);
		
		return "test/redisTest";
	}
	
	@RequestMapping("/test")
	private String TestPage() {
		return "layouts/default_Layout";
	}
	
		
	
}