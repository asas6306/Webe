package com.example.demo.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	private MemberService ms;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 기타 유용한 정보를 request에 담는다.
		Map<String, Object> param = Util.getParamMap(request);
		String paramJson = Util.toJsonStr(param);

		String requestUrl = request.getRequestURI();
		String queryString = request.getQueryString();

		if (queryString != null && queryString.length() > 0) {
			requestUrl += "?" + queryString;
		}

		String encodedRequestUrl = Util.getUrlEncoded(requestUrl);

		request.setAttribute("requestUrl", requestUrl);
		request.setAttribute("encodedRequestUrl", encodedRequestUrl);

		request.setAttribute("afterLoginUrl", requestUrl);
		request.setAttribute("encodedAfterLoginUrl", encodedRequestUrl);

		request.setAttribute("paramMap", param);
		request.setAttribute("paramJson", paramJson);

		// 로그인 여부에 관련된 정보를 request에 담는다.
		Member loginedMember = null;
		int uid = 0;
		boolean isLogined = false;
		boolean isAdmin = false;

		// String.valueOf(x) → 값이 null인 경우 해당 값에 'null'로 저장...
		String authKey = request.getParameter("authKey");

		if (authKey != null && authKey.length() > 0) {
			loginedMember = ms.getMember(authKey, "authKey", true);

			if (loginedMember == null) {
				request.setAttribute("authKeyState", "invalid");
			} else {
				request.setAttribute("authKey", "valid");
			}
		} else {
			HttpSession session = request.getSession();
			request.setAttribute("authKeyState", "none");

			if (session.getAttribute("m") != null) {
				loginedMember = ((Member) session.getAttribute("m"));
			}
		}

		if (loginedMember != null) {
			isLogined = true;
			isAdmin = ms.authCheck(loginedMember);
		}

		request.setAttribute("uid", uid);
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("m", loginedMember);

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}