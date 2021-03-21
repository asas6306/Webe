package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/adm/member/getLoginIdDup")
	@ResponseBody
	public ResultData getLoginIdDup(String ID) {
		System.out.println("ID : " + ID);
		if(ID == null)
			return new ResultData("F-1", "ID를 입력해주세요.");
		
		if(Util.allNumberString(ID))
			return new ResultData("F-3", "아이디는 숫자로만 구성될 수 없습니다.");
		
		if(Util.startsWithNumber(ID))
			return new ResultData("F-4", "아이디는 숫자로 시작될 수 없습니다.");
		
		if(Util.isStandardLoginIdCheck(ID) == false)
			return new ResultData("F-5", "아이디는 영문과 숫자의 조합으로 구성되어야 합니다.");
		
		if(ID.length() < 5)
			return new ResultData("F-6", "아이디를 5자 이상으로 입력하세요.");
		if(ID.length() > 15)
			return new ResultData("F-6", "아이디를 15자 이하로 입력하세요.");
		
		Member member = ms.getMember(ID, "ID", false);
		
		if(member != null)
			return new ResultData("F-2", String.format("%s(은)는 이미 사용중인 아이디입니다.", ID));
			
		return new ResultData("S-1", String.format("%s(은)는 사용 가능한 아이디입니다.", ID), "ID", ID);
	}

	@RequestMapping("/adm/member/doLogin")
	@ResponseBody
	public String doLogin(String ID, String PW, String redirectUrl, HttpSession session) {

		Member member = ms.getMember(ID, "ID", true);

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
			Member m = ms.getMember(ID, "ID", false);
			if (m.getID().equals(ID))
				return msgAndBack(req, "중복된 아이디입니다.");
		}
		
		if (param.get("PW") == null) 
			return msgAndBack(req, "비밀번호를 입력해주세요");
		
		String nickname = (String) param.get("nickname");
		nickname = nickname.trim();
		
		if (nickname == null) {
			return msgAndBack(req, "닉네임을 입력해주세요");
		} else {
			Member m = ms.getMember(nickname, "nickname", false);
			if (m.getNickname().equals(nickname))
				return msgAndBack(req, "중복된 닉네임입니다.");
		}
		
		if (param.get("email1") == null) 
			return msgAndBack(req, "이메일을 입력해주세요");
		
		if (param.get("email2") == null) 
			return msgAndBack(req, "이메일을 입력해주세요");
		
		if (param.get("phoneNo") == null) 
			return msgAndBack(req, "전화번호를 입력해주세요");

		param.put("email", param.get("email1") + "@" + param.get("email2"));

		param.remove("PWCheck");
		param.remove("email1");
		param.remove("email2");

		ms.signup(param);

		String redirectUrl = Util.ifEmpty((String)param.get("redirectUrl"), "login");
		
		return Util.msgAndReplace("회원가입이 완료되었습니다.", redirectUrl);
	}
	
	@RequestMapping("/adm/member/list")
	public String list(HttpServletRequest req, @RequestParam Map<String, Object> param, String type, String keyword, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "0") int authLevel) {
		
		req.setAttribute("authLevel", authLevel);

		if (page < 1)
			page = 1;

		if (keyword != null) {
			keyword.trim();
			if (keyword.length() == 0)
				keyword = null;
		}
		int pageCnt = 20;

		if (ms.getMembers(param, type, keyword, page, pageCnt, authLevel).size() == 0)
			return msgAndBack(req, "해당하는 회원이 존재하지 않습니다.");

		List<Member> members = ms.getMembers(param, type, keyword, page, pageCnt, authLevel);

		req.setAttribute("members", members);


		return "/adm/member/list";
	}
	
	@RequestMapping("/adm/member/update")
	public String update(HttpServletRequest req, Integer uid) {
		if(uid == null)
			msgAndBack(req, "수정 할 회원번호를 입력해주세요");
		
		Member member = ms.getMember(String.valueOf(uid), "uid", true);
		req.setAttribute("member", member);
		
		return "adm/member/update";
	}
	
	@RequestMapping("/adm/member/doUpdate")
	public String doUpdate(@RequestParam Map<String, Object> param,  HttpServletRequest req) {
		ms.update(param);
		
		return msgAndReplace(req, "회원정보가 수정되었습니다.", "list");
	}
}
