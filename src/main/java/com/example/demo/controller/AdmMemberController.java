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
public class AdmMemberController extends _BaseController {
	@Autowired
	private MemberService ms;

	@RequestMapping("/adm/member/login")
	public String login() {

		return "/adm/member/login";
	}

	@RequestMapping("/adm/member/doLogin")
	@ResponseBody
	public String doLogin(String ID, String PW, String redirectUrl, HttpSession session) {

		Member member = ms.getMember(ID, "ID");

		if (member == null)
			return Util.msgAndBack("존재하지 않는 관리자입니다.");
		else if (!member.getPW().equals(PW))
			return Util.msgAndBack("비밀번호가 일치하지 않습니다.");
//		if(m == null || !m.getPW().equals(PW))
//			return new ResultData("F-2", "존재하지 않는 회원정보입니다.", "ID", ID);

		if (!ms.authCheck(member))
			return Util.msgAndBack("접근 권한이 없는 계정입니다.");

		session.setAttribute("m", member);

		String msg = String.format("%s님 환영합니다.", member.getNickname());
		redirectUrl = Util.ifEmpty(redirectUrl, "../home/main");

		return Util.msgAndReplace(msg, redirectUrl);
	}

	@RequestMapping("/adm/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session) {
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
		String ID = (String) param.get("ID");
		ID = ID.trim();
		
		if (ID == null) {
			return msgAndBack(req, "아이디를 입력해주세요");
		} else {
			Member m = ms.getMember(ID, "ID");
			if (m.getID().equals(ID))
				return msgAndBack(req, "중복된 아이디입니다.");
		}
		
		if (param.get("PW1") == null) 
			return msgAndBack(req, "비밀번호를 입력해주세요");
		
		String nickname = (String) param.get("nickname");
		nickname = nickname.trim();
		
		if (nickname == null) {
			return msgAndBack(req, "닉네임을 입력해주세요");
		} else {
			Member m = ms.getMember(nickname, "nickname");
			if (m.getNickname().equals(nickname))
				return msgAndBack(req, "중복된 닉네임입니다.");
		}
		
		if (param.get("email1") == null) 
			return msgAndBack(req, "이메일을 입력해주세요");
		
		if (param.get("email2") == null) 
			return msgAndBack(req, "이메일을 입력해주세요");
		
		if (param.get("phoneNo") == null) 
			return msgAndBack(req, "전화번호를 입력해주세요");

		param.put("PW", param.get("PW1"));
		param.put("email", param.get("email1") + "@" + param.get("email2"));

		param.remove("PW1");
		param.remove("PW2");
		param.remove("email1");
		param.remove("email2");

		ms.signup(param);

		String redirectUrl = Util.ifEmpty((String)param.get("redirectUrl"), "login");
		
		return Util.msgAndReplace("회원가입이 완료되었습니다.", redirectUrl);
	}
}
