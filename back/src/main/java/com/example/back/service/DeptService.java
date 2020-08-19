package com.example.back.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.mapper.DeptMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeptService {

	@Autowired private DeptMapper mapper;
	
	public List<Map<String, Object>> getDeptList() {
		log.info("getList 호출 ");
		return mapper.getDeptList();
	}
	public int deptInsert(Map<String, Object> map) {
		log.info("doInsert 호출 ");
		return mapper.deptInsert(map);
	}
	public int deptUpdate(Map<String, Object> map) {
		log.info("doUpdae 호출 ");
		return mapper.deptUpdate(map);
	}
	public int deptDelete(Map<String, Object> map) {
		log.info("doDelete 호출 ");
		return mapper.deptDelete(map);
	}
   
}
