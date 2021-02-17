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
		int lengthCnt = (int) (Math.random() * 3) + 8;
		String email1 = "";

		while (email1.length() <= lengthCnt) {
			int asciiCode = (int) (Math.random() * 122);

			if ((asciiCode >= 48 && asciiCode <= 57) || (asciiCode >= 65 && asciiCode <= 90)
					|| (asciiCode >= 97 && asciiCode <= 122)) {
				email1 = email1 + (char) asciiCode;
			}
		}

		String email2 = "";
		int emailIndex = (int) (Math.random() * 2);
		if (emailIndex == 0) {
			email2 = "naver.com";
		} else if (emailIndex == 1) {
			email2 = "gmail.com";
		}
		String email = email1 + "@" + email2;
		param.put("email", email);

		int phone1 = (int) (Math.random() * 10000);
		int phone2 = (int) (Math.random() * 10000);
		String phone3 = String.valueOf(phone1);
		String phone4 = String.valueOf(phone2);

		if (phone3.length() != 4) {
			int num = 4 - phone3.length();

			for (int i = 0; i < num; i++) {
				phone3 = "0" + phone3;
			}
		}

		if (phone4.length() != 4) {
			int num = 4 - phone4.length();

			for (int i = 0; i < num; i++) {
				phone4 = "0" + phone4;
			}
		}

		String phoneNo = "010-" + phone3 + "-" + phone4;
		param.put("phoneNo", phoneNo);

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

	public boolean authorityCheck(int uid) {
		return uid == 1;
	}
}
