package com.P2PApp.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.P2PApp.DAO.UserDAO;
import com.P2PApp.DTO.LoginDTO;
import com.P2PApp.DTO.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegisterController {

	@Autowired
	private SqlSession sqlsession;
	
	@PostMapping("registerOK")
	private String RegisterInsert(UserDTO userDTO, String password) throws NoSuchAlgorithmException {
		
		log.info("userDTO : {}", userDTO);
		log.info("password : {}", password);
		
		UserDAO userMapper = sqlsession.getMapper(UserDAO.class);
		
		int flag = userMapper.insertUserInfo(userDTO);
		log.info("flag : {}",flag);
		
		
		int asciiFirst = 33;
		int asciiLast = 126;
		String salt = null;
		
		MessageDigest MDSHA = MessageDigest.getInstance("SHA-256");
		SecureRandom random = new SecureRandom();
		StringBuilder pwdbuilder = new StringBuilder();
		
		
		// password 길이만큼 salt 만들어 해당 StringBuilder에 삽입 ( salt 문자열 만들기 )
		for ( int i = 0; i < password.length(); i++ ) {
			int random_ascii = random.nextInt(asciiLast-asciiFirst+1)+asciiFirst;
			pwdbuilder.append((char)random_ascii);
		}
		
		// salt 저장
		salt = pwdbuilder.toString();
		log.info("salt : {}", salt);
		
		// Salt 들어있는 pwdbuilder에 password 문자열 삽입
		pwdbuilder.append(password);
		log.info("salt + pwd : {}",pwdbuilder);
		
		
		// Bytes로 인코딩된 password 배열을 Digest 객체(SHA-256방식)로 해싱함
		MDSHA.update(pwdbuilder.toString().getBytes());
		
		// 해싱된 password만 삽입 위해 다시 초기화
		pwdbuilder.setLength(0);
		
		// 해싱처리된 Value의 length값만큼 b에 넣어 해당 StringBuilder에 삽입 ( hashing된 password 문자열 만들기 )  
		for ( byte b : MDSHA.digest()) {
			pwdbuilder.append(String.format("%02x", b));
		}
		log.info("hashed pwd : {}", pwdbuilder.toString());
		
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setId(userDTO.getId());
		loginDTO.setSalt(salt);
		loginDTO.setPassword(pwdbuilder.toString());
		
		
		
		return "page/register";
	}
	
}
