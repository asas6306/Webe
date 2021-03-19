package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.Member;
import com.example.demo.service.ArticleService;
import com.example.demo.util.ResultData;
import com.example.demo.util.Util;

@Controller
public class UsrArticleController extends _BaseController {
	@Autowired
	private ArticleService as;

	@GetMapping("/usr/article/list")
	@ResponseBody
	public ResultData list(String type, String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") int boardCode) {
		if (page < 1)
			page = 1;

		if (keyword != null) {
			keyword.trim();
			if (keyword.length() == 0)
				keyword = null;
		}
		int pageCnt = 20;

		if (as.getArticles(type, keyword, page, pageCnt, boardCode).size() == 0)
			return new ResultData("F-1", "해당하는 게시물이 존재하지 않습니다.");


		return new ResultData("S-1", "성공", "Articles", as.getArticles(type, keyword, page, pageCnt, boardCode));
	}

	@GetMapping("/usr/article/detail")
	@ResponseBody
	public ResultData detail(int aid) {
		if (as.getArticleById(aid) == null)
			return new ResultData("F-1", "해당 게시물이 존재하지 않습니다.", "aid", aid);

		as.hitCnt(aid);

		return new ResultData("S-1", "성공", "Article", as.getArticleById(aid));
	}

	@PostMapping("/usr/article/add")
	@ResponseBody
	public ResultData add(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		if (!param.containsKey("title"))
			return new ResultData("F-1", "제목을 입력해주세요.");
		if (!param.containsKey("body"))
			return new ResultData("F-1", "내용을 입력해주세요.");
		if (!param.containsKey("boardCode"))
			return new ResultData("F-1", "게시판을 선택해주세요.");

		Member m = (Member) req.getAttribute("m");
		param.put("uid", m.getUid());

		return as.add(param);
	}

	@GetMapping("/usr/article/update")
	@ResponseBody
	public String update() {
		
		return "";
	}
	 
	@PostMapping("/usr/article/doUpdate")
	public String doUpdate(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		Member m = (Member)req.getAttribute("m");
		int aid = Util.getAsInt(param.get("aid"), 0);
		System.out.println("param : " + param);
		if(aid == 0)
			return msgAndBack(req, "게시물번호를 입력해주세요.");
		
		if(Util.isEmpty(param.get("title")))
			return msgAndBack(req, "제목를 입력해주세요.");
		
		if(Util.isEmpty(param.get("body")))
			return msgAndBack(req, "내용를 입력해주세요.");
		
		Article article = as.getArticleById(aid);
		
		if(article == null)
			return msgAndBack(req, "존재하지 않는 게시물입니다.");
		
		ResultData actorCanUpdateRd = as.authCheck(aid, m);
		
		if(actorCanUpdateRd.isFail())
			return msgAndBack(req, actorCanUpdateRd.getMsg());
		
		ResultData doUpdateRd = as.update(param);
		if(doUpdateRd.isSuccess())
			return msgAndReplace(req, "게시물이 수정되었습니다.", "../article/detail?aid=" + aid);
		
		return msgAndBack(req, "게시물 수정에 실패하였습니다.");
	}

	@PostMapping("/usr/article/delete")
	@ResponseBody
	public ResultData delete(Integer aid, HttpServletRequest req) {
		if (aid == null)
			return new ResultData("F-1", "게시물 id를 입력해주세요");
		Member member = ((Member) req.getAttribute("m"));

		return as.delete(aid, member);
	}

	@GetMapping("/usr/article/like")
	@ResponseBody
	public ResultData like(Integer aid, HttpServletRequest req) {
		if (aid == null)
			return new ResultData("F-1", "게시물 id를 입력해주세요");

		return as.like(aid, ((Member) req.getAttribute("m")).getUid());
	}
	
	@GetMapping("/usr/home/a1")
	@ResponseBody
	public String a1() {
		return "a1";
	}

	@GetMapping("/usr/home/a2")
	@ResponseBody
	public String a2() {
		return as.a2();
	}
}
