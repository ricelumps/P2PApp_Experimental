package com.P2PApp.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashingAlgorithm {

	public HashMap<String, String> HashValue(String Value) throws NoSuchAlgorithmException {
		
		HashMap<String, String> HashedValue = new HashMap<String, String>();
		
		
		int asciiFirst = 33;
		int asciiLast = 126;
		String salt = null;
		
		MessageDigest MDSHA = MessageDigest.getInstance("SHA-256");
		SecureRandom random = new SecureRandom();
		StringBuilder pwdbuilder = new StringBuilder();
		
		
		// password 길이만큼 salt 만들어 해당 StringBuilder에 삽입 ( salt 문자열 만들기 )
		for ( int i = 0; i < Value.length(); i++ ) {
			int random_ascii = random.nextInt(asciiLast-asciiFirst+1)+asciiFirst;
			pwdbuilder.append((char)random_ascii);
		}
		
		// salt 저장
		salt = pwdbuilder.toString();
		log.info("salt : {}", salt);
		
		// Salt 들어있는 pwdbuilder에 password 문자열 삽입
		pwdbuilder.append(Value);
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

		HashedValue.put("salt", salt);
		HashedValue.put("password", pwdbuilder.toString());
		
		return HashedValue;
	}
	
	public String HashValueWithSalt(String salt, String Value) throws NoSuchAlgorithmException {
		
		String HashedValue = null;
		
		int asciiFirst = 33;
		int asciiLast = 126;
		
		MessageDigest MDSHA = MessageDigest.getInstance("SHA-256");
		SecureRandom random = new SecureRandom();
		StringBuilder pwdbuilder = new StringBuilder();
		
		pwdbuilder.append(salt.toCharArray());
	
		// Salt 들어있는 pwdbuilder에 password 문자열 삽입
		pwdbuilder.append(Value);
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

		HashedValue = pwdbuilder.toString();
		
		return HashedValue;
	}
}
