package com.example.back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.service.DeptService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptRestController {

	@Autowired private DeptService service;
	
	@GetMapping("/list")
	public List<Map<String, Object>> getList() {
		return service.getDeptList();
	}
	@GetMapping("/ins")
	public int doInsert(@RequestParam Map<String, Object> map) {
		log.info("Param : "+map.toString());
		int result = service.deptInsert(map); 
		log.info("result : "+result);
		return result;
	}
	@GetMapping("/upd")
	public int doUpdate(@RequestParam Map<String, Object> map) {
		log.info("Param : "+map.toString());
		int result = service.deptUpdate(map);
		log.info("result : "+result);
		return result;
	}
	@GetMapping("/del")
	public int doDelete(@RequestParam Map<String, Object> map) {
		log.info("Param : "+map.toString());
		int result = service.deptDelete(map);
		log.info("result : "+result);
		return result;
	}
	
}

