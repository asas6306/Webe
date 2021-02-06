package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.ResultData;
import com.example.demo.util.Util;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao ad;
	
	public List<Article> getArticles(String type, String keyword){
		
		return ad.getArticles(type, keyword);
	}
	
	public Article getArticleById(int aid) {
		
		return ad.getArticleById(aid);
	}
	
	public ResultData add(Map<String, Object> param) {
		ad.add(param);
		
		int aid = Util.getAsInt(param.get("id"), 0);
		
		return new ResultData("S-1", "게시물이 등록되었습니다.");
	}
	
	public ResultData update(int aid, String title, String body) {
		ad.update(aid, title, body);
		
		return new ResultData("S-1", "게시물이 수정되었습니다.");
	}
	
	public ResultData delete(int aid) {
		ad.delete(aid);
		
		return new ResultData("S-1", "게시물이 삭제되었습니다.");
	}
	
	

	public String a1() {
		return "a1";
	}

	public String a2() {
		return ad.a2();
	}
}
