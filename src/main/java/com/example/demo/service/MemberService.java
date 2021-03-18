package com.example.demo.service;

import java.util.List;
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

	// static 시작
	public static String getAuthLevelName(Member member) {
		switch (member.getAuthLevel()) {
		case 7:
			return "관리자";
		case 3:
			return "일반회원";
		default:
			return "유형정보없음";
		}
	}

	public static String getAuthLevelNameColor(Member member) {
		switch (member.getAuthLevel()) {
		case 7:
			return "red";
		case 3:
			return "gray";
		default:
			return "";
		}
	}
	// static 끝

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

		return new ResultData("S-1", "회원탈퇴 완료");
	}

	// 관리자 권한 정의
	public boolean authCheck(Member member) {
		return member.getAuthLevel() == 7;
	}

	public Member getMemberByAuthKey(String authKey) {

		return md.getMemberByAuthKey(authKey);
	}

	public List<Member> getMembers(Map<String, Object> param, String type, String keyword, int page, int pageCnt, int authLevel) {
		page = (page - 1) * pageCnt;
		
		param.put("type", type);
		param.put("keyword", keyword);
		param.put("page", page);
		param.put("pageCnt", pageCnt);
		param.put("authLevel", authLevel);
		return md.getMembers(param);
	}
}
