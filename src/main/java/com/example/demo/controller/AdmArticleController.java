package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.example.demo.dto.Article;
import com.example.demo.dto.Board;
import com.example.demo.dto.GenFile;
import com.example.demo.dto.Member;
import com.example.demo.service.ArticleService;
import com.example.demo.service.GenFileService;
import com.example.demo.util.ResultData;

@Controller
public class AdmArticleController extends _BaseController {
	@Autowired
	private ArticleService as;
	@Autowired
	private GenFileService fs;

	@RequestMapping("/adm/article/list")
	public String list(HttpServletRequest req, String type, String keyword, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "0") int boardCode) {

		Board board = as.getBoard(boardCode);
		req.setAttribute("boardCode", boardCode);
		System.out.println("boardCode : " + boardCode);

		if (boardCode != 0 && board == null)
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
		
		for(Article a : articles) {
			GenFile genFile = fs.getGenFile("article", a.getAid(), "common", "attachment", 1);
			
			if(genFile != null)
				a.setExtra__thumbImg(genFile.getForPrintUrl());			
		}
		
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
	public ResultData doAdd(@RequestParam Map<String, Object> param, HttpServletRequest req,
			MultipartRequest multipartRequest) {

		if (!param.containsKey("title"))
			return new ResultData("F-1", "제목을 입력해주세요.");
		if (!param.containsKey("body"))
			return new ResultData("F-1", "내용을 입력해주세요.");
		if (!param.containsKey("boardCode"))
			return new ResultData("F-1", "게시판을 선택해주세요.");

		Member m = (Member) req.getAttribute("m");
		param.put("uid", m.getUid());

		ResultData addArticleRd = as.add(param);
		int newArticleId = (int) addArticleRd.getBody().get("aid");

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		for (String fileInputName : fileMap.keySet()) {
			MultipartFile multipartFile = fileMap.get(fileInputName);

			if (multipartFile.isEmpty() == false)
				fs.save(multipartFile, newArticleId);
		}

		return addArticleRd;
	}
	
	@RequestMapping("/adm/article/detail")
	@ResponseBody
	public ResultData detail(int aid) {
		Article a = as.getArticleById(aid);
		
		GenFile genFile = fs.getGenFile("article", a.getAid(), "common", "attachment", 1);

		a.setExtra__thumbImg(genFile.getForPrintUrl());
		
		return new ResultData("S-1", "성공", "aritlce", a);
		}
}
