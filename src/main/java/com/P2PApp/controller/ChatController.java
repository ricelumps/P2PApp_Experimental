package com.P2PApp.Controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {
	
	@Autowired
	private SqlSession sqlsession;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/chat")
	public String chatGET(Model model) {
		
		
		log.info(" @ChatControler, chat GET() ");

		return "/page/chat";
	}
	
	
}
