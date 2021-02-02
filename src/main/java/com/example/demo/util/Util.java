package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.DD. hh:mm:ss");

		return sdf.format(date);
	}

	public static Map<String, Object> indexOf(Object... args) {
		Map<String, Object> map = new LinkedHashMap<>();
		int length = args.length;
		
		if(length%2 != 0) {
			throw new IllegalArgumentException("인자의 개수를 짝수로 입력해주세요.");
		}
		
		for(int i =0; i<length; i+=2) {
			map.put(String.valueOf(args[i]), args[i+1]);
		}
		
		return map;
	}
}
