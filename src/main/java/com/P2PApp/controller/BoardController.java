package com.P2PApp.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.P2PApp.DAO.FreeboardDAO;
import com.P2PApp.DTO.FreeboardDTO;
import com.P2PApp.DTO.FreeboardList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlsession;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/board/Free")
	private String ViewFreeboard(Model model, String Page) {
		
		// 현재 페이지 정의
		int currentPage;
		// 현재 페이지 int형으로 변환
		try {
			currentPage = Integer.parseInt(Page);
		} catch (Exception e) {
			currentPage = 1;
		}
		
		// 다음 페이지, 이전 페이지 정의
		int nextPage = currentPage + 1;
		int previousPage = currentPage - 1;
		
		if( previousPage == 0 ) previousPage = 1;
		
		
		FreeboardDAO BoardMapper = sqlsession.getMapper(FreeboardDAO.class);
		
		// 전체 게시물 수 집계
		int totalPost = BoardMapper.countPost();
		
		// 페이지당 게시물 수 정의
		int postPerPage = 20;
		
		// 게시판에 대한 계산을 List에서 정의, List 객체 선언
		FreeboardList freeboardList = new FreeboardList(postPerPage, totalPost, currentPage);
		
		// 게시물들 배열 객체로 받아옴.
		ArrayList<FreeboardDTO> posts = BoardMapper.selectByPage( (currentPage-1) * postPerPage , postPerPage );

		
		model.addAttribute("FreeAssets",posts);
		model.addAttribute("freeboardList", freeboardList);		
		model.addAttribute("NextPage",nextPage);
		model.addAttribute("PrevPage",previousPage);
	
		
		return "page/freeboard";
	}
	
	
	@GetMapping("/board/Free/{boardID}")
	private String ViewPost(@PathVariable int boardID, Model model) {
		
		log.info("index : {}", boardID);
		FreeboardDAO postMapper = sqlsession.getMapper(FreeboardDAO.class);
		FreeboardDTO postObj = postMapper.selectById(boardID);
		

		model.addAttribute("post",postObj);
		model.addAttribute("userID",(String)session.getAttribute("userID"));
		
		return "/page/postview";
	}
	
	
	@GetMapping("/board/Free/write")
	private String writePost() {
	
		return "page/postwrite";
	}
	
	@PostMapping("board/Free/writeOK")
	private String writeOK(FreeboardDTO boardDTO) {
		
		
		String userID = (String)session.getAttribute("userID");
		boardDTO.setAuthor(userID);
		
		FreeboardDAO boardDAO = sqlsession.getMapper(FreeboardDAO.class);
		int insertFlag = boardDAO.insertPost(boardDTO);
		log.info("flag : {}", insertFlag);
		
		return "redirect:/board/Free";
	}
	
	@PostMapping("/deleteOK")
	private String deleteOK(int boardID) {
		
		log.info("boardID : {} ", boardID);
		FreeboardDAO boardMapper = sqlsession.getMapper(FreeboardDAO.class);
		
		FreeboardDTO postINFO = boardMapper.selectById(boardID);
		if ( (String)session.getAttribute("userID") != postINFO.getAuthor() ) {
			return "redirect:/board/Free/" + boardID;
		}
		
		int deleteFlag = boardMapper.deleteById(boardID);
		
		return "redirect:/board/Free";
	}
	
	@GetMapping("/board/Free/edit/{boardID}")
	private String editPost(Model model, @PathVariable int boardID) {
		
		FreeboardDAO boardMapper = sqlsession.getMapper(FreeboardDAO.class);
		FreeboardDTO DTO = boardMapper.selectById(boardID);
		
		model.addAttribute("DTO",DTO);
		
		return "/page/postupdate";
	}
	
	
	@PostMapping("/updateOK")
	private String updateOK(FreeboardDTO freeboardDTO) {
		log.info("DTO : {}", freeboardDTO);
		
		FreeboardDAO boardMapper = sqlsession.getMapper(FreeboardDAO.class);
		int flag = boardMapper.updatePost(freeboardDTO);
		log.info("update flag : {}", flag);
		
		return "redirect:/board/Free/" + freeboardDTO.getBoardID();
	}
	
	
}
