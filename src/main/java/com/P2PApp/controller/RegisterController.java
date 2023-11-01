package com.P2PApp.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.P2PApp.DAO.RegisterDAO;
import com.P2PApp.DTO.LoginDTO;
import com.P2PApp.DTO.RegisterDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegisterController {

	@Autowired
	private SqlSession sqlsession;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
	private String Register() {
		
		return "page/register";
	}
	
	@PostMapping("/registerOK")
	private String RegisterInsert(RegisterDTO registerDTO, String password) throws NoSuchAlgorithmException {
		
		log.info("userDTO : {}", registerDTO);
		log.info("password : {}", password);
		
		if (registerDTO.getUserID().trim() == "" || registerDTO.getPassword().trim() == ""
				|| registerDTO.getEmail().trim() == "" || registerDTO.getName().trim() == ""
				|| registerDTO.getPhone().trim() == "") {
			return "redirect:/register";
		}		
		
		
//		HashingAlgorithm hashingAlgorithm = new HashingAlgorithm();
//		Map<String, String> hashMap = hashingAlgorithm.HashValue(password);
//		log.info("hashmap : {}", hashMap);
//
//		LoginDTO loginDTO = new LoginDTO();
//		loginDTO.setUserID(registerDTO.getUserID());
//		loginDTO.setSalt(hashMap.get("salt"));
//		loginDTO.setPassword(hashMap.get("password"));
//		
//		RegisterDAO userMapper = sqlsession.getMapper(RegisterDAO.class);
//		
//		int flag = userMapper.insertUserInfo(registerDTO.getUserID());
//		int loginFlag = userMapper.insertLoginInfo(loginDTO);
//		
//		log.info("password flag : {} ", loginFlag);
//		
		passwordEncoder.encode(password);
		log.info(passwordEncoder.toString());
		
		return "redirect:/";
	}
	
}
