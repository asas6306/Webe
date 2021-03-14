package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.LikeDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.Board;
import com.example.demo.dto.Like;
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
	@Autowired
	private GenFileService fs;

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

		workRelIds(param, aid);

		return new ResultData("S-1", "게시물이 등록되었습니다.", "aid", aid);
	}

	public ResultData update(Map<String, Object> param) {
		ad.update(param);
		
		int aid = Util.getAsInt(param.get("aid"), 0);

		workRelIds(param, aid);

		return new ResultData("S-1", "게시물이 수정되었습니다.");
	}

	public ResultData delete(int aid, int uid) {
		if (ad.getArticleById(aid) == null)
			return new ResultData("F-2", "해당 게시물이 존재하지 않습니다.", "aid", aid);
		if (this.authorityCheck((int) aid, uid) == null)
			return new ResultData("F-3", "해당 게시물 삭제 권한이 없습니다.");
		ad.delete(aid);

		fs.deleteFiles("article", aid);

		return new ResultData("S-1", "게시물이 삭제되었습니다.");
	}

	public ResultData authorityCheck(int aid, int uid) {
		Article a = ad.getArticleById(aid);
		if (ms.authorityCheck(uid))
			return new ResultData("S-1", "관리자 권한 수행가능");
		else if (a.getUid() == uid)
			return new ResultData("S-2", "해당 기능 수행가능");
		else
			return new ResultData("F-1", "권한없음");
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

	public Board getBoard(int boardCode) {

		return ad.getBoard(boardCode);
	}

	public void workRelIds(Map<String, Object> param, int id) {
		String genFileIdsStr = Util.ifEmpty((String) param.get("genFileIdsStr"), null);
		
		if (genFileIdsStr != null) {
			List<Integer> genFileIds = Util.getListDividedBy(genFileIdsStr, ", ");
			// 파일이 먼저 생성된 후에, 관련 데이터가 생성되는 경우에는, file의 relId가 일단 0으로 저장된다.
			// 그것을 뒤늦게라도 이렇게 고처야 한다.
			
			for (int genFileId : genFileIds) {
				fs.changeRelId(genFileId, id);
			}
		}
	}
}
