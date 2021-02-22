package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	private MemberService ms;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("good?");

		HttpSession session = request.getSession();

		// 로그인 여부에 관련된 정보를 request에 담는다.
		boolean isLogined = false;
		boolean isAdmin = false;
		int uid = 0;
		Member loginedMember = null;
		System.out.println("isAdmin : " + isAdmin);
		
		if (session.getAttribute("m") != null) {
			uid = ((Member) session.getAttribute("m")).getUid();
			isLogined = true;
			loginedMember = ms.getMember(String.valueOf(uid), "uid");
			isAdmin = ms.authorityCheck(uid);
		}
		System.out.println("isAdmin : " + isAdmin);
		request.setAttribute("uid", uid);
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("m", loginedMember);
		
		isAdmin = Boolean.valueOf((Boolean)request.getAttribute("isAdmin"));
		System.out.println("isAdmin : " + isAdmin);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}