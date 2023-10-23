package com.P2PApp.DTO;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class FreeboardList {
	
	private int pageSize = 10;  // 페이징 처리 개수
	private int totalPost = 0; // 전체 게시물 수
	private int totalPage = 0; // 전체 페이지 개수
	private int currentPage = 1; // 현재 페이지 
	private int startNo = 0; 
	private int endNo = 0;
	private int startPage = 0;
	private int endPage = 0;

	public FreeboardList(int pageSize, int totalPost, int currentPage) {
		this.pageSize = pageSize;
		this.totalPost = totalPost;
		this.currentPage = currentPage;
		calculator();
	}
	
	private void calculator() {
		totalPage = (totalPost - 1) / pageSize + 1;
		currentPage = currentPage > totalPage ? totalPage : currentPage;
		startNo = (currentPage - 1) * pageSize + 1;
		endNo = startNo + pageSize - 1;
		endNo = endNo > totalPost ? totalPost : endNo;
		startPage = (currentPage - 1) / 10 * 10 + 1;
		endPage = startPage + 9;
		endPage = endPage > totalPage ? totalPage : endPage;
	}
	
}


