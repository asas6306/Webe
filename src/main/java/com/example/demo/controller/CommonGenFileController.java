package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.util.ResultData;

@Controller
public class CommonGenFileController {
	@RequestMapping("/common/genFile/doUpload")
	@ResponseBody
	public ResultData doUpload() {
		return new ResultData("S-1", "업로드 성공", "genFileIdsStr", "1,2");
	}
}
