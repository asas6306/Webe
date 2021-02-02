package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleDao {
	public String a2();

	public void add(@Param(value = "title") String title, @Param(value = "body") String body);
}
