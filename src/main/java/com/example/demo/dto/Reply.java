package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	private int rid;
	private int uid;
	private String body;
	private String relTypeCode;
	private int relid;
	private String regDate;
	private String updateDate;
	private String nickname;
}
