package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.LikeDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.Board;
import com.example.demo.dto.GenFile;
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
	@Autowired
	private GenFileService fs;

	public List<Article> getArticles(String type, String keyword, int page, int pageCnt, int boardCode) {
		page = (page - 1) * pageCnt;

		List<Article> articles = ad.getArticles(type, keyword, page, pageCnt, boardCode);
		List<Integer> aids = articles.stream().map(article -> article.getAid()).collect(Collectors.toList());
		Map<Integer, Map<String, GenFile>> filesMap = fs.getFilesMapKeyRelIdAndFileNo("article", aids, "common",
				"attachment");

		for (Article article : articles) {
			Map<String, GenFile> mapByFileNo = filesMap.get(article.getAid());

			if (mapByFileNo != null)
				article.getExtraNotNull().put("file__common__attachment", mapByFileNo);
		}

		return articles;
	}

	public Article getArticleById(int aid) {

		return ad.getArticleById(aid);
	}

	public ResultData add(Map<String, Object> param) {
		ad.add(param);

		int aid = Util.getAsInt(param.get("aid"), 0);

		fs.workRelIds(param, aid);

		return new ResultData("S-1", "게시물이 등록되었습니다.", "aid", aid);
	}

	public ResultData update(Map<String, Object> param) {
		ad.update(param);

		int aid = Util.getAsInt(param.get("aid"), 0);

		return new ResultData("S-1", "게시물이 수정되었습니다.");
	}

	public ResultData delete(int aid, Member member) {
		if (ad.getArticleById(aid) == null)
			return new ResultData("F-2", "해당 게시물이 존재하지 않습니다.", "aid", aid);
		if (this.authCheck((int) aid, member) == null)
			return new ResultData("F-3", "해당 게시물 삭제 권한이 없습니다.");
		ad.delete(aid);

		fs.deleteGenFiles("article", aid);

		return new ResultData("S-1", "게시물이 삭제되었습니다.");
	}

	public ResultData authCheck(int aid, Member member) {
		Article a = ad.getArticleById(aid);
		if (ms.authCheck(member))
			return new ResultData("S-1", "관리자 권한 수행가능");
		else if (a.getUid() == member.getUid())
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

	public int getArticlesCnt(String type, String keyword, int boardCode) {
		
		return ad.getArticlesCnt(type, keyword, boardCode);
	}
}
