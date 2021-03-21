package com.example.demo.dto;

import com.example.demo.service.MemberService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int uid;
	private String ID;
	@JsonIgnore
	private String PW;
	private int authLevel;
	@JsonIgnore
	private String authKey;
	private String nickname;
	private String regDate;
	private String email;
	private String phoneNo;
	
	private String extra__thumbImg;
	
	public String getAuthLevelName() {
		return MemberService.getAuthLevelName(this);
	}

	public String getAuthLevelNameColor() {
		return MemberService.getAuthLevelNameColor(this);
	}
}
