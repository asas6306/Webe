package com.example.demo.dto;

import java.util.Map;

import com.example.demo.util.Util;

import lombok.Data;

@Data
public class ResultData {
	String resultCode;
	String msg;
	Map<String, Object> body;
	
	public ResultData(String resultCode, String msg, Object... args) {
		this.resultCode = resultCode;
		this.msg = msg;
		this.body = Util.indexOf(args);
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}

	@Override
	public String toString() {
		return "ResultData [resultCode=" + resultCode + ", msg=" + msg + ", body=" + body + ", isSuccess()="
				+ isSuccess() + ", isFail()=" + isFail() + "]";
	}

	
}
