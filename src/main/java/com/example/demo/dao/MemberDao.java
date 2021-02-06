package com.example.demo.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Member;
import com.example.demo.util.ResultData;

@Mapper
public interface MemberDao {

	public void signup(Map<String, Object> param);

	public Member existCheck(@Param(value = "item") String item, @Param(value = "itemIndex") String itemIndex);

}