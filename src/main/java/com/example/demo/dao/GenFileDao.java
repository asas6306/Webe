package com.example.demo.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GenFileDao {

	public void saveMeta(Map<String, Object> param);

}
