package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.LikeDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.Like;
import com.example.demo.dto.Member;
import com.example.demo.util.ResultData;
import com.example.demo.util.Util;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao ad;
	@Autowired
	private MemberService ms;
	@Autowired
	private LikeDao ld;

	public List<Article> getArticles(String type, String keyword, int page, int pageCnt, int boardCode) {
		page = (page - 1) * pageCnt;

		return ad.getArticles(type, keyword, page, pageCnt, boardCode);
	}

	public Article getArticleById(int aid) {

		return ad.getArticleById(aid);
	}

	public ResultData add(Map<String, Object> param) {
		ad.add(param);

		int aid = Util.getAsInt(param.get("aid"), 0);

		return new ResultData("S-1", "게시물이 등록되었습니다.", "aid", aid);
	}

	public ResultData update(int aid, String title, String body, int uid) {
		if (ad.getArticleById(aid) == null)
			return new ResultData("F-2", "해당 게시물이 존재하지 않습니다.", "aid", aid);
		if (this.authorityCheck(aid, uid) == null)
			return new ResultData("F-3", "해당 게시물 수정 권한이 없습니다.");

		ad.update(aid, title, body);

		return new ResultData("S-1", "게시물이 수정되었습니다.");
	}

	public ResultData delete(int aid, int uid) {
		if (ad.getArticleById(aid) == null)
			return new ResultData("F-2", "해당 게시물이 존재하지 않습니다.", "aid", aid);
		if (this.authorityCheck((int) aid, uid) == null)
			return new ResultData("F-3", "해당 게시물 삭제 권한이 없습니다.");
		ad.delete(aid);

		return new ResultData("S-1", "게시물이 삭제되었습니다.");
	}

	public ResultData authorityCheck(int aid, int uid) {
		Article a = ad.getArticleById(aid);
		if (ms.authorityCheck(uid))
			return new ResultData("S-1", "관리자 권한 수행가능");
		else if (a.getUid() == uid)
			return new ResultData("S-2", "해당 기능 수행가능");
		else
			return null;
	}

	public void hitCnt(int aid) {
		ad.hitCnt(aid);
	}

	public ResultData like(int aid, int uid) {
		if (ad.getArticleById(aid) == null)
			return new ResultData("F-2", "해당 게시물이 존재하지 않습니다.", "aid", aid);

		Article a = ad.getArticleById(aid);
		ArrayList<Like> al = ld.getList(aid);

		for (Like l : al) {
			if (l.getUid() == uid) {
				ld.cancelLike(aid, uid);
				ad.like(aid, "down");
				return new ResultData("S-2", "좋아요가 취소되었습니다.");
			}
		}
		ld.doLike(aid, uid);
		ad.like(aid, "up");
		return new ResultData("S-1", "해당 게시물을 좋아요했습니다.");
	}

	public String a1() {
		return "a1";
	}

	public String a2() {
		return ad.a2();
	}

}
