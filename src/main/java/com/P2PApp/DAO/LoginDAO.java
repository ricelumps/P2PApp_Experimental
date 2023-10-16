package com.P2PApp.DAO;

import org.apache.ibatis.annotations.Mapper;

import com.P2PApp.DTO.LoginDTO;

@Mapper
public interface LoginDAO {
	
	LoginDTO selectById(String userID);

}