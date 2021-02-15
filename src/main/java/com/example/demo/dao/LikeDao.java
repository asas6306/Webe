package com.example.demo.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Like;

@Mapper
public interface LikeDao {

	ArrayList<Like> getList(@Param(value = "aid") int aid);

	void cancelLike(@Param(value = "aid") int aid, @Param(value = "uid") int uid);

	void doLike(@Param(value = "aid") int aid, @Param(value = "uid") int uid);

}
