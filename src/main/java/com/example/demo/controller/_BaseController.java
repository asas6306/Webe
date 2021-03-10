package com.example.demo.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class _BaseController {
	protected String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("historyBack", true);
		req.setAttribute("msg", msg);
		
		return "common/redirect";
	}
	
	protected String msgAndReplace(HttpServletRequest req, String msg, String redirectUrl) {
		req.setAttribute("redirectUrl", redirectUrl);
		req.setAttribute("msg", msg);
		
		return "common/redirect";
	}
}
