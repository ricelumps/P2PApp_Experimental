package com.P2PApp.Config;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.P2PApp.Controller.HashingAlgorithm;
import com.P2PApp.DAO.FreeboardDAO;
import com.P2PApp.DAO.LoginDAO;
import com.P2PApp.DTO.LoginDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
	
		LoginDAO loginMapper = sqlSession.getMapper(LoginDAO.class);
		LoginDTO INFO = loginMapper.selectByUsername(userID);
		log.info("MyUserDetailService ON");
		
		if ( INFO == null ) {
			log.info("뭐 업대요");
			throw new UsernameNotFoundException("없는 회원입니다.");
		}
		
		log.info("INFO : {}", INFO);
		
		
		return User.builder()
				.username(INFO.getUserID())
				.password(INFO.getPassword())
				.roles(INFO.getRole())
				.build();
	}

}
