package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.ArticleService;
import com.example.demo.util.ResultData;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService as;

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public ResultData list(String type, String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") int board) {
		if (page < 1)
			page = 1;

		if (keyword != null) {
			keyword.trim();
			if (keyword.length() == 0)
				keyword = null;
		}
		int pageCnt = 20;

		if (as.getArticles(type, keyword, page, pageCnt, board) == null)
			return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.");


		return new ResultData("S-1", "성공", "Articles", as.getArticles(type, keyword, page, pageCnt, board));
	}

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public ResultData detail(int aid) {
		if (as.getArticleById(aid) == null)
			return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.", "aid", aid);

		as.hitCnt(aid);

		return new ResultData("S-1", "성공", "Article", as.getArticleById(aid));
	}

	@RequestMapping("/usr/article/add")
	@ResponseBody
	public ResultData add(Map<String, Object> param, HttpSession session) {
		if (!param.containsKey("title"))
			return new ResultData("F-1", "제목을 입력해주세요.");
		if (!param.containsKey("body"))
			return new ResultData("F-1", "내용을 입력해주세요.");
		if (!param.containsKey("boardTag"))
			return new ResultData("F-1", "게시판을 선택해주세요.");

		Member m = (Member) session.getAttribute("m");
		param.put("uid", m.getUid());

		return as.add(param);
	}

	@RequestMapping("/usr/article/update")
	@ResponseBody
	public ResultData update(Integer aid, String title, String body, HttpSession session) {
		if (aid == null)
			return new ResultData("F-1", "게시물 id를 입력해주세요");
		else if (title == null && body == null)
			return new ResultData("F-1", "수정할 내용을 입력하세요.");
		int uid = ((Member) session.getAttribute("m")).getUid();

		return as.update(aid, title, body, uid);
	}

	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public ResultData delete(Integer aid, HttpSession session) {
		if (aid == null)
			return new ResultData("F-1", "게시물 id를 입력해주세요");
		int uid = ((Member) session.getAttribute("m")).getUid();

		return as.delete(aid, uid);
	}

	@RequestMapping("/usr/article/like")
	@ResponseBody
	public ResultData like(Integer aid, HttpSession session) {
		if (aid == null)
			return new ResultData("F-1", "게시물 id를 입력해주세요");

		return as.like(aid, ((Member) session.getAttribute("m")).getUid());
	}
	
	@RequestMapping("/usr/home/a1")
	@ResponseBody
	public String a1() {
		return "a1";
	}

	@RequestMapping("/usr/home/a2")
	@ResponseBody
	public String a2() {
		return as.a2();
	}
}
