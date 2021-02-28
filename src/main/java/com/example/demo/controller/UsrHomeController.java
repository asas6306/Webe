package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class UsrHomeController {
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String main() {
		return "유저 홈";
	}
}
