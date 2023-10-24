package com.P2PApp.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.stream.DoubleStream;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.P2PApp.DAO.LoginDAO;
import com.P2PApp.DTO.LoginDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class LoginController {

	@Autowired
	private SqlSession sqlsession;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping("/")
	private String login() {
		return "page/login";
	}
	
	@PostMapping("/loginOK")
	private String loginValidate(LoginDTO loginDTO) throws NoSuchAlgorithmException {
		
		log.info("loginDTO : " + loginDTO.toString());
		
		LoginDAO mapper = sqlsession.getMapper(LoginDAO.class);
		
		LoginDTO SelectedValue = mapper.selectById(loginDTO.getUserID());
		
		log.info("VO : {}", SelectedValue);
		
		HashingAlgorithm hashingAlgorithm = new HashingAlgorithm();
		String HashedPassword = hashingAlgorithm.HashValueWithSalt(SelectedValue.getSalt(),loginDTO.getPassword());
		
		if( SelectedValue.getPassword().equals(HashedPassword) ) {
			session.setAttribute("loginStatus", loginDTO.getUserID());
			
			return "redirect:index";
		} else {
			return "page/register";
		}
		
	
	}

	
	@GetMapping("/register")
	public String registerForm() {
		return "page/register";
	}
	
	@GetMapping("/logout")
	public String logout() {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
}
