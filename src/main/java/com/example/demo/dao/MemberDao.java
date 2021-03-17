package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Member;
import com.example.demo.util.ResultData;

@Mapper
public interface MemberDao {

	public void signup(Map<String, Object> param);

	public Member getMember(@Param(value = "item") String item, @Param(value = "itemIndex") String itemIndex);

	public void update(Map<String, Object> param);

	public void signout(@Param(value = "uid") int uid);

	public Member getMemberByAuthKey(@Param(value="authKey") String authKey);

	public List<Member> getMembers(@Param(value = "type") String type, @Param(value = "keyword") String keyword,
			@Param(value = "page") int page, @Param(value = "pageCnt") int pageCnt, @Param(value = "authLevel") int authLevel);


}
