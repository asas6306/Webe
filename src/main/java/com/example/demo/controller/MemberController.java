package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import com.example.demo.util.ResultData;

@Controller
public class MemberController {
	@Autowired
	private MemberService ms;

	@RequestMapping("/usr/member/signup")
	@ResponseBody
	public ResultData signup(@RequestParam Map<String, Object> param) {
		if (param.get("ID") == null) {
			return new ResultData("F-1", "ID를 입력해주세요.");
		}
		if (param.get("PW") == null) {
			return new ResultData("F-1", "PW를 입력해주세요.");
		}
		if (param.get("nickname") == null) {
			return new ResultData("F-1", "nickname을 입력해주세요.");
		}

		Member existCheckOfID = ms.existCheck(String.valueOf(param.get("ID")), "ID");
		Member existCheckOfnickname = ms.existCheck(String.valueOf(param.get("nickname")), "nickname");

		if (existCheckOfID != null) {
			return new ResultData("F-2", "중복된 아이디입니다.");
		} else if (existCheckOfnickname != null) {
			return new ResultData("F-2", "중복된 닉네임입니다.");
		}

		return ms.signup(param);
	}
}
