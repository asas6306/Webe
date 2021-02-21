package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	ReplyService rs;
	
//	@RequestMapping("/usr/article/addComment")
//	@ResponseBody
//	public ResultData addComment(Integer aid, String body, HttpSession session) {
//		if(aid == null)
//			return new ResultData("F-1", "게시물 id를 입력해주세요");
//		if(body == null)
//			return new ResultData("F-1", "댓글 내용을 입력해주세요");
//		
//		Member m = (Member)session.getAttribute("m");
//		int uid = m.getUid();
//		
//		return as.addComment(aid, uid, body);
//		return null;
//	}
}
