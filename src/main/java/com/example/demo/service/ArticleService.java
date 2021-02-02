package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao ad;

	public ResultData add(String title, String body) {
		ad.add(title, body);
		
		return new ResultData("S-1", "게시물이 등록되었습니다.");
	}

	public String a1() {
		return "a1";
	}

	public String a2() {
		return ad.a2();
	}
}
