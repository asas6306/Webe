package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.ResultData;
import com.example.demo.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService as;

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> list(String type, String keyword) {
		if (keyword != null) {
			keyword.trim();
			if (keyword.length() == 0) {
				keyword = null;
			}
		}
		
		return as.getArticles(type, keyword);
	}

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article detail(int aid) {
		
		return as.getArticleById(aid);
	}

	@RequestMapping("/usr/article/add")
	@ResponseBody
	public ResultData add(Map<String, Object> param) {
		if (param.get("title") == null) {
			return new ResultData("F-1", "제목을 입력해주세요.");
		}
		if (param.get("body") == null) {
			return new ResultData("F-2", "내용을 입력해주세요.");
		}

		return as.add(param);
	}

	@RequestMapping("/usr/article/update")
	@ResponseBody
	public ResultData update(Integer aid, String title, String body) {
		if(aid == null) {
			return new ResultData("F-1", "게시물 id를 입력해주세요");
		}
		
		return as.update(aid, title, body);
	}

	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public ResultData delete(Integer aid) {
		if(aid == null) {
			return new ResultData("F-1", "게시물 id를 입력해주세요");
		}
		
		return as.delete(aid);
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
