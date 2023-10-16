package com.P2PApp.DAO;

import org.apache.ibatis.annotations.Mapper;

import com.P2PApp.DTO.LoginDTO;

@Mapper
public interface RegisterDAO {
	
	int insertUserInfo(String userID);
	int insertLoginInfo(LoginDTO loginDTO);
}
