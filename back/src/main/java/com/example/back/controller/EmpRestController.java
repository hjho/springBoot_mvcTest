package com.example.back.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.model.BankMember;
import com.example.back.service.EmpService;
import com.example.back.util.MybatisUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/emp")
@Slf4j
public class EmpRestController {

	@Autowired private EmpService service;
	
	@PostMapping("/list")
	public List<Map<String, Object>> getEmpList(@RequestBody Map<String, Object> map) {
		log.info("getEmpList_map: "+map.toString());
		return service.getEmpList(map);
	}
	
	@PostMapping("/pageEmpList")
	public ModelMap getEmpPageList(@RequestBody Map<String, Object> map) {
		log.info("getEmpPageList_map: "+map.toString());
		ModelMap model = new ModelMap();
		MybatisUtils.putModelListPage(service.getEmpPageList(map), model);
		return model;
	}
	
	@GetMapping("/jobList")
	public List<Map<String, Object>> getJobList() {
		return service.getJobList();
	} 
	
	@GetMapping("/list2")
	public List<BankMember> getBankList() {
		return service.getBankList();
	} 
	
	@PostMapping("/ins")
	public ModelMap empInsert(@RequestBody Map<String, Object> map) { //, ModelMap을 파라미터로 받으면 에러남.
		log.info("doInsert_map: "+map.toString());
		int result = service.empInsert(map);
		ModelMap model = new ModelMap();
		model.put("result", result);
		if(result > 0) model.put("msg", "사원 등록 성공");
		else model.put("msg", "사원 등록 실패");
		return model;
	}
	@PostMapping("/upd")
	public ModelMap empUpdate(@RequestHeader HttpHeaders httpHeaders
			                 ,@RequestBody Map<String, Object> httpbody) {
		log.info("httpHeaders: "+httpHeaders.toString());
		log.info("httpbody: "+httpbody.toString());
		int result = service.empUpdate(httpbody);
		ModelMap model = new ModelMap();
		log.info("upd_result : "+result);
		model.put("result", result);
		if(result > 0) model.put("msg", "사원 수정 성공");
		else model.put("msg", "사원 수정 실패");
		return model;
	}
	@PostMapping("/del")
	public ModelMap empDelete(@RequestHeader HttpHeaders httpHeaders
			                 ,@RequestBody Map<String, Object> httpbody) {
		log.info("httpHeaders: "+httpHeaders.toString());
		log.info("httpbody: "+httpbody.toString());
		int result = service.empDelete(httpbody);
		ModelMap model = new ModelMap();
		log.info("del_result : "+result);
		model.put("result", result);
		if(result > 0) model.put("msg", "사원 삭제 성공");
		else model.put("msg", "사원 삭제 실패");
		return model;
	}
	/**************************************************************************
	 * key(Long, String) 테스트용으로 만든 Method DataBase에 접근하여 현재시간을 가져온다.
	 * @param key
	 * @param map
	 * @return
	 */
	@GetMapping("/long/{key}")
	public String getTime(@PathVariable Long key) 
	{
		log.info("@PathVariable: "+key);
		return "";
	}
	@GetMapping("/str/{key}")
	public String getTime(@PathVariable String key) 
	{
		log.info("@PathVariable: "+key);
		return "";
	}
}

