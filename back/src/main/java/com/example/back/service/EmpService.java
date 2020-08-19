package com.example.back.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.mapper.EmpMapper;
import com.example.back.model.BankMember;
import com.example.back.util.MybatisUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmpService {

	@Autowired private EmpMapper mapper;
	
	public List<Map<String, Object>> getEmpList(Map<String, Object> map) {
		log.info("getEmpList 호출 ");
		return mapper.getEmpList(map);
	}
	public PageList<Map<String,Object>> getEmpPageList(Map<String, Object> map) {
		log.info("getEmpPageList 호출 ");
		return mapper.getEmpPageList(map, MybatisUtils.pageBounds(map));
	}
	public List<Map<String, Object>> getJobList() {
		return mapper.getJobList();
	}
	public int empInsert(Map<String, Object> map) {
		log.info("doInsert 호출 ");
		return mapper.empInsert(map);
	}
	public int empUpdate(Map<String, Object> map) {
		log.info("doUpdae 호출 ");
		return mapper.empUpdate(map);
	}
	public int empDelete(Map<String, Object> map) {
		log.info("doDelete 호출 ");
		return mapper.empDelete(map);
	}

	public List<BankMember> getBankList() {
		return mapper.getBankList();
	}


	

}
