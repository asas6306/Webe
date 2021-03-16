package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.GenFile;

@Mapper
public interface GenFileDao {

	public void saveMeta(Map<String, Object> param);

	public GenFile getGenFile(@Param(value="relTypeCode") String relTypeCode, @Param(value="relId") int relId, @Param(value="typeCode") String typeCode, @Param(value="type2Code") String type2Code, @Param(value="fileNo") int fileNo);

	public List<GenFile> getGenFiles(@Param(value="relTypeCode") String relTypeCode, @Param(value="relId") int relId, @Param(value="typeCode") String typeCode, @Param(value="type2Code") String type2Code);
		
	public void changeRelId(@Param(value="fid") int fid, @Param(value="relId") int relId);

	public void deleteFile(@Param(value="fid") int fid);

	public GenFile getGenFileById(@Param(value="fid") int fid);

	public List<GenFile> getGenFilesRelTypeCodeAndRelIdsAndTypeCodeAndType2Code(@Param("relTypeCode") String relTypeCode,
			@Param("relIds") List<Integer> relIds, @Param("typeCode") String typeCode,
			@Param("type2Code") String type2Code);
}
