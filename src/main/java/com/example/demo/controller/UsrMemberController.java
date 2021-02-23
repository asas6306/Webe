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

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService ms;

	@RequestMapping("/usr/member/signup")
	@ResponseBody
	public ResultData signup(@RequestParam Map<String, Object> param) {
		if (param.get("ID") == null)
			return new ResultData("F-1", "ID를 입력해주세요.");
		if (param.get("PW") == null)
			return new ResultData("F-1", "PW를 입력해주세요.");
		if (param.get("nickname") == null)
			return new ResultData("F-1", "nickname을 입력해주세요.");

		if (ms.getMember(String.valueOf(param.get("ID")), "ID") != null)
			return new ResultData("F-2", "중복된 아이디입니다.");
		else if (ms.getMember(String.valueOf(param.get("nickname")), "nickname") != null)
			return new ResultData("F-2", "중복된 닉네임입니다.");

		return ms.signup(param);
	}

	@RequestMapping("/usr/member/signout")
	@ResponseBody
	public ResultData signout(HttpSession session) {
		Member m = (Member) session.getAttribute("m");

		return ms.signout(m.getUid());
	}

	@RequestMapping("/usr/member/login")
	@ResponseBody
	public ResultData login(String ID, String PW, HttpSession session) {
		if (ID == null)
			return new ResultData("F-1", "ID를 입력해주세요.");

		if (PW == null)
			return new ResultData("F-1", "PW를 입력해주세요.");

		Member m = ms.getMember(ID, "ID");

		if (m == null)
			return new ResultData("F-2", "존재하지 않는 회원정보입니다.", "ID", ID);
		else if (!m.getPW().equals(PW))
			return new ResultData("F-2", "비밀번호가 일치하지 않습니다.");
//		if(m == null || !m.getPW().equals(PW))
//			return new ResultData("F-2", "존재하지 않는 회원정보입니다.", "ID", ID);

		session.setAttribute("m", m);

		return new ResultData("S-1", String.format("%s님 환영합니다.", m.getNickname()));
	}

	@RequestMapping("/usr/member/logout")
	@ResponseBody
	public ResultData logout(HttpSession session) {
		session.removeAttribute("m");
		return new ResultData("S-1", "로그아웃 되었습니다.");
	}

	@RequestMapping("/usr/member/update")
	@ResponseBody
	public ResultData update(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		if (param.isEmpty())
			return new ResultData("F-2", "변경 할 회원정보를 입력해주세요.");

		Member m = (Member) req.getAttribute("m");
		param.put("uid", m.getUid());

		return ms.update(param);
	}
}