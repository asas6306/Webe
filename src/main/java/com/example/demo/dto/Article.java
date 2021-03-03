package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	int aid;
	String title;
	String body;
	String regDate;
	String updateDate;
	int uid;
	String nickname;
	int hit;
	int like;
	int boardCode;
	String boardName;
}
