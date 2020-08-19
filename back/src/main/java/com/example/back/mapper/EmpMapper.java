package com.example.back.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.back.model.BankMember;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Mapper
public interface EmpMapper {
	
	public List<Map<String, Object>> getEmpList(Map<String, Object> pMap);
	
	public int empInsert(Map<String, Object> pMap);
	
	public int empUpdate(Map<String, Object> pMap);
	
	public int empDelete(Map<String, Object> pMap);
	
	public List<BankMember> getBankList();

	public List<Map<String, Object>> getJobList();

	public PageList<Map<String,Object>> getEmpPageList(Map<String, Object> map, PageBounds pageBounds);
}
