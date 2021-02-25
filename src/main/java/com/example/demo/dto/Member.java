package com.example.demo.dto;

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
	String authKey;
	String nickname;
	String regDate;
	String email;
	String phoneNo;
}
