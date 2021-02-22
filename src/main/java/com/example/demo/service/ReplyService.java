package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReplyDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.Reply;
import com.example.demo.util.ResultData;

@Service
public class ReplyService {
	@Autowired
	ReplyDao rd;
	@Autowired
	ArticleService as;
	@Autowired
	MemberService ms;

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

	public ResultData delete(int rid, int uid) {
		Reply r = rd.getReplyById(rid);
		if (r == null)
			return new ResultData("F-2", "해당 댓글이 존재하지 않습니다.", "rid", rid);

		if (this.authorityCheck(rid, uid) == null)
			return new ResultData("F-3", "해당 댓글이 삭제권한이 없습니다.");

		rd.delete(rid);

		return new ResultData("S-1", "해당 댓글이 삭제되었습니다..", "rid", rid);
	}

	// 통합? 어디로?
	public ResultData authorityCheck(int rid, int uid) {
		Reply r = rd.getReplyById(rid);
		if (ms.authorityCheck(uid))
			return new ResultData("S-1", "관리자 권한 수행가능");
		else if (r.getUid() == uid)
			return new ResultData("S-2", "해당 기능 수행가능");
		else
			return null;
	}

	public ResultData update(int rid, String body, int uid) {
		Reply r = rd.getReplyById(rid);
		if (r == null)
			return new ResultData("F-2", "해당 댓글이 존재하지 않습니다.", "rid", rid);

		if (this.authorityCheck(rid, uid) == null)
			return new ResultData("F-3", "해당 댓글이 삭제권한이 없습니다.");

		rd.update(rid, body);

		return new ResultData("S-1", "해당 댓글이 수정되었습니다..", "rid", rid);
	}

}
