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
public class AdmMemberController {
	@Autowired
	private MemberService ms;
	
	@RequestMapping("/adm/member/login")
	public String login() {
		return "/adm/member/login";
	}
	
	@RequestMapping("/adm/member/doLogin")
	@ResponseBody
	public String doLogin(String ID, String PW, HttpSession session) {
		
		Member m = ms.getMember(ID, "ID");
		
		if (m == null)
			return Util.msgAndBack("존재하지 않는 관리자입니다.");
		else if (!m.getPW().equals(PW))
			return Util.msgAndBack("비밀번호가 일치하지 않습니다.");
//		if(m == null || !m.getPW().equals(PW))
//			return new ResultData("F-2", "존재하지 않는 회원정보입니다.", "ID", ID);
		
		if(!ms.authorityCheck(m.getUid()))
			return Util.msgAndBack("접근 권한이 없는 계정입니다.");
		
		session.setAttribute("m", m);
		
		String msg = String.format("%s님 환영합니다.", m.getNickname());
		
		return Util.msgAndReplace(msg, "../home/main");
	}
	
	@RequestMapping("/adm/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session) {
		session.removeAttribute("m");
		
		String msg = "로그아웃 되었습니다.";
		
		return Util.msgAndReplace(msg, "../member/login");
	}
}	
