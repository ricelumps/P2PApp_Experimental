package com.P2PApp.DAO;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.P2PApp.DTO.FreeboardDTO;

@Mapper
public interface FreeboardDAO {

	ArrayList<FreeboardDTO> selectByPage(int page, int postPerPage); 
	FreeboardDTO selectById(int boardID);
	int countPost();
	int insertPost(FreeboardDTO freeboardDTO);
	int deleteById(int boardID);
	int updatePost(FreeboardDTO freeboardDTO);
}
