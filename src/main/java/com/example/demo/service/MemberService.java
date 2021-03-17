package com.example.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dto.Member;
import com.example.demo.util.ResultData;
import com.example.demo.util.Util;

@Service
public class MemberService {
	@Autowired
	private MemberDao md;

	public ResultData signup(Map<String, Object> param) {
		md.signup(param);

		int uid = Util.getAsInt(param.get("uid"), 0);

		return new ResultData("S-1", String.format("%s님의 회원가입이 완료되었습니다.", param.get("nickname")));
	}

	public Member getMember(String item, String itemIndex) {
		
		return md.getMember(item, itemIndex);
	}

	public ResultData update(Map<String, Object> param) {
		md.update(param);

		return new ResultData("S-1", String.format("%s님의 회원정보가 수정되었습니다.", param.get("nickname")));
	}


	public ResultData signout(int uid) {
		md.signout(uid);
		
		return  new ResultData("S-1", "회원탈퇴 완료");
	}

	// 관리자 권한 정의
	public boolean authorityCheck(int uid) {
		return uid == 1;
	}

	public Member getMemberByAuthKey(String authKey) {
		
		return md.getMemberByAuthKey(authKey);
	}
}
