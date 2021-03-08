package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.Board;
import com.example.demo.dto.Member;
import com.example.demo.service.ArticleService;
import com.example.demo.util.ResultData;

@Controller
public class AdmArticleController extends _BaseController {
	@Autowired
	private ArticleService as;

	@RequestMapping("/adm/article/list")
	public String list(HttpServletRequest req, String type, String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") int boardCode) {

		Board b = as.getBoard(boardCode);
		
		if(boardCode != 0 && b == null)
			return msgAndBack(req, "존재하지 않는 게시판입니다.");
		
		if (page < 1)
			page = 1;

		if (keyword != null) {
			keyword.trim();
			if (keyword.length() == 0)
				keyword = null;
		}
		int pageCnt = 20;

		if (as.getArticles(type, keyword, page, pageCnt, boardCode).size() == 0)
			return msgAndBack(req, "해당하는 게시물이 존재하지 않습니다.");

		List<Article> articles = as.getArticles(type, keyword, page, pageCnt, boardCode);
		req.setAttribute("articles", articles);
		
		return "adm/article/list";
	}
	
	@RequestMapping("/adm/article/add")
	public String add(HttpServletRequest req, int boardCode) {
		req.setAttribute("boardCode", boardCode);
		
		return "adm/article/add";
	}
	
	@RequestMapping("/adm/article/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String, Object> param, HttpServletRequest req) {
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
}
