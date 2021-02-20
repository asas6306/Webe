package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private int cid;
	private int uid;
	private int aid;
	private String nickname;
	private String regDate;
	private String updateDate;
	private String body;
	private int commentReply;
}
