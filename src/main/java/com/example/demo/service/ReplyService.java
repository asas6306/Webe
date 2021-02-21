package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReplyDao;

@Service
public class ReplyService {
	@Autowired
	ReplyDao rd;
	
//	public ResultData addComment(int aid, int uid, String body) {
//		if(ad.getArticleById(aid)==null)
//			return new ResultData("F-2", "해당 게시물이 존재하지 않습니다.");
//		
//		ad.addComment(aid, uid, body);
//		return new ResultData("S-1", "댓글이 등록되었습니다.");
//	}
}
