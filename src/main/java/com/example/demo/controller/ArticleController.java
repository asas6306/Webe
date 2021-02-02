package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.ResultData;
import com.example.demo.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService as;
	
	
	@RequestMapping("/usr/article/add")
	@ResponseBody
	public ResultData add(String title, String body) {
		if(title==null) {
			return new ResultData("F-1", "제목을 입력해주세요.");
		}
		if(body==null) {
			return new ResultData("F-2", "제목을 입력해주세요.");
		}
		
		return as.add(title, body);
	}
	
	@RequestMapping("/usr/home/a1")
	@ResponseBody
	public String a1() {
		return as.a1();
	}
	
	@RequestMapping("/usr/home/a2")
	@ResponseBody
	public String a2() {
		return as.a2();
	}
}
