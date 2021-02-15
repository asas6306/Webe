package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Article;

@Mapper
public interface ArticleDao {
	public String a2();

	public void add(Map<String, Object> param);

	public List<Article> getArticles(@Param(value = "type") String type, @Param(value = "keyword") String keyword);

	public Article getArticleById(@Param(value = "aid") int aid);

	public void update(@Param(value = "aid") int aid, @Param(value = "title") String title, @Param(value = "body") String body);

	public void delete(@Param(value = "aid") int aid);

	public void hitCnt(@Param(value = "aid") int aid);

	public void like(@Param(value = "aid") int aid, @Param(value = "action") String action);
}
