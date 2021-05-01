package com.example.demo.controller;

import java.util.HashMap;
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
import com.example.demo.util.Util;

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

		req.setAttribute("articles", articles);

		int articlesCnt = as.getArticlesCnt(type, keyword, boardCode);
		req.setAttribute("articlesCnt", articlesCnt);

		return "adm/article/list";
	}

	@RequestMapping("/adm/article/add")
	public String add(HttpServletRequest req, int boardCode) {
		req.setAttribute("boardCode", boardCode);
		return "adm/article/add";
	}

	@RequestMapping("/adm/article/doAdd")
	public String doAdd(@RequestParam Map<String, Object> param, HttpServletRequest req,
			MultipartRequest multipartRequest) {

		if (!param.containsKey("title"))
			return msgAndBack(req, "제목을 입력해주세요");
		if (!param.containsKey("body"))
			return msgAndBack(req, "내용을 입력해주세요.");

		Member m = (Member) req.getAttribute("m");
		param.put("uid", m.getUid());

		ResultData addArticleRd = as.add(param);

		int newArticleId = (int) addArticleRd.getBody().get("aid");

		return msgAndReplace(req, "게시물이 작성되었습니다.", "../article/detail?aid=" + newArticleId);
	}

	@RequestMapping("/adm/article/detail")
	@ResponseBody
	public ResultData detail(Integer aid) {
		if (aid == null)
			new ResultData("F-1", "게시물 번호를 입력해주세요.");

		Article article = as.getArticleById(aid);

		if (article == null)
			new ResultData("F-2", "존재하지 않는 게시물입니다.");

		return new ResultData("S-1", "성공", "aritlce", article);
	}

	@RequestMapping("/adm/article/delete")
	@ResponseBody
	public ResultData delete(Integer aid, HttpServletRequest req) {
		if (aid == null)
			return new ResultData("F-1", "게시물 id를 입력해주세요");
		Member member = (Member) req.getAttribute("m");

		return as.delete(aid, member);
	}

	@RequestMapping("/adm/article/update")
	public String update(Integer aid, HttpServletRequest req) {
		if (aid == null)
			return msgAndBack(req, "게시물 번호를 입력해주세요.");

		Article article = as.getArticleById(aid);

		if (article == null)
			return msgAndBack(req, "존재하지 않는 게시물입니다.");

		List<GenFile> files = fs.getGenFiles("article", article.getAid(), "common", "attachment");

		Map<String, GenFile> filesMap = new HashMap<>();

		for (GenFile file : files)
			filesMap.put(file.getFileNo() + "", file);

		article.getExtraNotNull().put("file__common__attachment", filesMap);
		req.setAttribute("article", article);

		return "adm/article/update";
	}

	@RequestMapping("/adm/article/doUpdate")
	public String doUpdate(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		Member member = (Member) req.getAttribute("m");
		int aid = Util.getAsInt(param.get("aid"), 0);

		if (aid == 0)
			return msgAndBack(req, "게시물번호를 입력해주세요.");

		if (Util.isEmpty(param.get("title")))
			return msgAndBack(req, "제목를 입력해주세요.");

		if (Util.isEmpty(param.get("body")))
			return msgAndBack(req, "내용를 입력해주세요.");

		Article article = as.getArticleById(aid);

		if (article == null)
			return msgAndBack(req, "존재하지 않는 게시물입니다.");

		ResultData actorCanUpdateRd = as.authCheck(aid, member);

		if (actorCanUpdateRd.isFail())
			return msgAndBack(req, actorCanUpdateRd.getMsg());

		ResultData doUpdateRd = as.update(param);

		if (doUpdateRd.isSuccess())
			return msgAndReplace(req, "게시물이 수정되었습니다.", "../article/detail?aid=" + aid);

		return msgAndBack(req, "게시물 수정에 실패하였습니다.");
	}
}
