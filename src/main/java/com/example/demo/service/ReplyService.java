package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReplyDao;
import com.example.demo.dto.Reply;
import com.example.demo.util.ResultData;

@Service
public class ReplyService {
	@Autowired
	ReplyDao rd;
	@Autowired
	ArticleService as;

	public ArrayList<Reply> getReplies(String relTypeCode, Integer relId) {
		
		return rd.getReplies(relTypeCode, relId);
	}
	
	public ResultData add(String relTypeCode, Integer relId, int uid, String body) {
		if (relTypeCode.equals("article"))
			if (as.getArticleById(relId) == null)
				return new ResultData("F-2", "해당 게시물이 존재하지 않습니다.");
		
		rd.add(relTypeCode, relId, uid, body);
		
		return new ResultData("S-1", "댓글이 등록되었습니다.");
	}

}
