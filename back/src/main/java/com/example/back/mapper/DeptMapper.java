package com.example.back.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper {
	
	public List<Map<String, Object>> getDeptList();
	
	public int deptInsert(Map<String, Object> pMap);
	
	public int deptUpdate(Map<String, Object> pMap);
	
	public int deptDelete(Map<String, Object> pMap);
}
