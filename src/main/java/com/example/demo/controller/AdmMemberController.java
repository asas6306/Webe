package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import com.example.demo.util.ResultData;
import com.example.demo.util.Util;

@Controller
public class AdmMemberController extends _BaseController{
	@Autowired
	private MemberService ms;

	@RequestMapping("/adm/member/login")
	public String login() {
		
		return "/adm/member/login";
	}

	@RequestMapping("/adm/member/doLogin")
	@ResponseBody
	public String doLogin(String ID, String PW, String redirectUrl, HttpSession session) {

		Member m = ms.getMember(ID, "ID");

		if (m == null)
			return Util.msgAndBack("존재하지 않는 관리자입니다.");
		else if (!m.getPW().equals(PW))
			return Util.msgAndBack("비밀번호가 일치하지 않습니다.");
//		if(m == null || !m.getPW().equals(PW))
//			return new ResultData("F-2", "존재하지 않는 회원정보입니다.", "ID", ID);

		if (!ms.authorityCheck(m.getUid()))
			return Util.msgAndBack("접근 권한이 없는 계정입니다.");

		session.setAttribute("m", m);
		
		String msg = String.format("%s님 환영합니다.", m.getNickname());
		redirectUrl = Util.ifEmpty(redirectUrl, "../home/main");
		
		return Util.msgAndReplace(msg, redirectUrl);
	}

	@RequestMapping("/adm/member/logout")
	@ResponseBody
	public String logout(HttpSession session) {
		session.removeAttribute("m");

		String msg = "로그아웃 되었습니다.";

		return Util.msgAndReplace(msg, "../member/login");
	}
	
	
	@RequestMapping("/adm/member/signup")
	public String signup() {
		
		return "/adm/member/signup";
	}
	
	@RequestMapping("/adm/member/doSignup")
	public String doSignup(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		String email = param.get("email1") + "@" + param.get("email2");
		param.put("PW", param.get("PW1"));
		param.put("email", email);
		
		param.remove("PW1");
		param.remove("PW2");
		param.remove("email1");
		param.remove("email2");
		
		
		ms.signup(param);
		
		return msgAndReplace(req, "회원가입이 완료되었습니다.", "login");
	}
}
