package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Article;

@Mapper
public interface ArticleDao {
	public String a2();

	public void add(Map<String, Object>);

	public List<Article> getArticles(@Param(value = "searchKeywordType") String searchKeywordType, @Param(value = "searchKeyword") String searchKeyword);

	public Article getArticleById(@Param(value = "aid") int aid);

	public void update(@Param(value = "aid") int aid, @Param(value = "title") String title, @Param(value = "body") String body);

	public void delete(@Param(value = "aid") int aid);
}
