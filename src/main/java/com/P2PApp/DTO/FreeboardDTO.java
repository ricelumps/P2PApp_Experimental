package com.P2PApp.DTO;

import java.util.ArrayList;
import java.util.Date;

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
public class FreeboardDTO {
	
	private int boardID;
	private String title;
	private String text;
	private String author;
	private int heart;
	private Date date;
	private int viewCount;
	
}
