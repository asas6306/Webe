package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.ReplyService;
import com.example.demo.util.ResultData;

@Controller
public class ReplyController {
	@Autowired
	ReplyService rs;
	
	@RequestMapping("/usr/article/addComment")
	@ResponseBody
	public ResultData addComment(Integer aid, String body, HttpSession session) {
		if(aid == null)
			return new ResultData("F-1", "게시물 id를 입력해주세요");
		if(body == null)
			return new ResultData("F-1", "댓글 내용을 입력해주세요");
		
		Member m = (Member)session.getAttribute("m");
		int uid = m.getUid();
		
//		return as.addComment(aid, uid, body);
		return null;
	}
}
