package com.example.demo.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Reply;

@Mapper
public interface ReplyDao {

	ArrayList<Reply> getReplies(@Param(value="relTypeCode") String relTypeCode, @Param(value="relId") Integer relId);
	
	void add(@Param(value="relTypeCode") String relTypeCode, @Param(value="relId") Integer relId, @Param(value="uid") int uid, @Param(value="body") String body);


}
