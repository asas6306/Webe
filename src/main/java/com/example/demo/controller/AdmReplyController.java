package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.ReplyService;
import com.example.demo.util.ResultData;

@Controller
public class AdmReplyController {
	@Autowired
	ReplyService rs;
	
//	@RequestMapping("/usr/reply/list")
//	@ResponseBody
//	public ResultData list(String relTypeCode, Integer relId) {
//		if(relTypeCode == null)
//			return new ResultData("F-1", "relTypeCode를 입력해주세요");
//		if(relId == null)
//			return new ResultData("F-1", "relId를 입력해주세요");
//		
//		return new ResultData("S-1", "성공", "replies", rs.getReplies(relTypeCode, relId));
//	}
//	
//	@RequestMapping("/usr/reply/add")
//	@ResponseBody
//	public ResultData add(String relTypeCode, Integer relId, String body, HttpServletRequest req) {
//		if(relTypeCode == null)
//			return new ResultData("F-1", "relTypeCode를 입력해주세요");
//		if(relId == null)
//			return new ResultData("F-1", "relId를 입력해주세요");
//		if(body == null)
//			return new ResultData("F-1", "댓글 내용을 입력해주세요");
//		
//		Member m = (Member)req.getAttribute("m");
//		int uid = m.getUid();
//		
//		return rs.add(relTypeCode, relId, uid, body);
//	}
//	
//	@RequestMapping("/usr/reply/delete")
//	@ResponseBody
//	public ResultData delete(Integer rid, HttpServletRequest req) {
//		if(rid == null)
//			return new ResultData("F-1", "삭제할 댓글 id를 입력해주세요");
//		
//		Member m = (Member)req.getAttribute("m");
//		int uid = m.getUid();
//		
//		return rs.delete(rid, uid);
//	}
//	
//	@RequestMapping("/usr/reply/update")
//	@ResponseBody
//	public ResultData update(Integer rid, String body, HttpServletRequest req) {
//		if(rid == null)
//			return new ResultData("F-1", "수정할 댓글 id를 입력해주세요");
//		if(body == null)
//			return new ResultData("F-1", "수정할 댓글 내용을 입력해주세요");
//		
//		Member m = (Member)req.getAttribute("m");
//		int uid = m.getUid();
//		
//		return rs.update(rid, body, uid);
//	}
}
