package com.P2PApp.DAO;

import org.apache.ibatis.annotations.Mapper;

import com.P2PApp.DTO.LoginDTO;
import com.P2PApp.DTO.UserDTO;

@Mapper
public interface UserDAO {
	
	int insertUserInfo(UserDTO userDTO);
	int insertLoginInfo(LoginDTO loginDTO);
}
