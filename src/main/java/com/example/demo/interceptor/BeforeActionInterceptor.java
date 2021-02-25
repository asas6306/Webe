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

		// 로그인 여부에 관련된 정보를 request에 담는다.
		Member loginedMember = null;
		int uid = 0;
		boolean isLogined = false;
		boolean isAdmin = false;
		
		String authKey = String.valueOf(request.getParameter("authKey"));
		
		if (authKey != null && authKey.length() > 0) {
			loginedMember = ms.getMemberByAuthKey(authKey);
			
			if(loginedMember == null) {
				request.setAttribute("authKeyState", "invalid");
			} else {
				request.setAttribute("authKey", "valid");
			}
		} else {
			HttpSession session = request.getSession();
			request.setAttribute("authKeyState", "none");
			
			if (session.getAttribute("m") != null) {
				uid = ((Member) session.getAttribute("m")).getUid();
				loginedMember = ms.getMember(String.valueOf(uid), "uid");
			}
		}

		if(loginedMember != null) {
			isLogined = true;
			isAdmin = ms.authorityCheck(uid);			
		}
		
		request.setAttribute("uid", uid);
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("m", loginedMember);

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}