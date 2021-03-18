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
	int uid;
	String ID;
	@JsonIgnore
	String PW;
	@JsonIgnore
	int authLevel;
	@JsonIgnore
	String authKey;
	String nickname;
	String regDate;
	String email;
	String phoneNo;
	
	public String getAuthLevelName() {
		return MemberService.getAuthLevelName(this);
	}

	public String getAuthLevelNameColor() {
		return MemberService.getAuthLevelNameColor(this);
	}
}
