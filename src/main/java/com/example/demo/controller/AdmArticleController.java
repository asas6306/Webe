package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.ArticleService;
import com.example.demo.util.ResultData;

@Controller
public class AdmArticleController {
	@Autowired
	private ArticleService as;

	@RequestMapping("/adm/article/list")
	@ResponseBody
	public void list() {
		
	}
}
