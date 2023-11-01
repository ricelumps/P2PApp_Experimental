package com.P2PApp.Config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SHA256PasswordEncoder implements PasswordEncoder {

	@Autowired
	private SqlSession SqlSession;
	
	@Override
    public String encode(CharSequence rawPassword) {
		HashMap<String, String> HashedValue = new HashMap<String, String>();
		
		
		int asciiFirst = 33;
		int asciiLast = 126;
		String salt = null;		
		MessageDigest MDSHA = null;
		try {
			MDSHA = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		StringBuilder pwdbuilder = new StringBuilder();

		
		// Bytes로 인코딩된 password 배열을 Digest 객체(SHA-256방식)로 해싱함
		MDSHA.update(pwdbuilder.toString().getBytes());
		
		// 해싱된 password만 삽입 위해 다시 초기화
		pwdbuilder.setLength(0);
		
		// 해싱처리된 Value의 length값만큼 b에 넣어 해당 StringBuilder에 삽입 ( hashing된 password 문자열 만들기 )  
		for ( byte b : MDSHA.digest()) {
			pwdbuilder.append(String.format("%02x", b));
		}
		log.info("hashed pwd : {}", pwdbuilder.toString());

		HashedValue.put("salt", salt);
		HashedValue.put("password", pwdbuilder.toString());
		
		return pwdbuilder.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }
	
}
