package com.P2PApp.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
	
	@RequestMapping("/main")
	private String index(@AuthenticationPrincipal User user) {
		
		/* Spring Security 적용 전 셋팅 
		 * if ( session.getAttribute("userID") != null ) { return "page/main";
		 * 
		 * } else { return "redirect:/"; }
		 */
		
		session.setAttribute("userID", user.getUsername());
		return "page/main";
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