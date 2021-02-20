package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	// 게시물 입력 요소
	// 식별변호, 제목, 내용, 날짜, 글쓴이(유저 식별번호), 조회수, 좋아요
	int aid;
	String title;
	String body;
	String regDate;
	String updateDate;
	int uid;
	String nickname;
	int hit;
	int like;
	int tag;
}
