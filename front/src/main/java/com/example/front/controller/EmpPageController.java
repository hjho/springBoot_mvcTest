package com.example.front.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.front.service.EmpPageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/emp")
@Slf4j
public class EmpPageController {

	@Autowired private EmpPageService service;
	
	@RequestMapping("/mainPage")
	public String mainPage(@RequestParam Map<String, Object> pMap, Model model) {
		log.info("param_mainPage : "+pMap.toString());
		model.addAttribute("data",service.getEmpList(pMap));
		return "front/mainPage";
	}
	
	@RequestMapping("/insertPage")
	public String insertPage(@RequestParam Map<String, Object> pMap, Model model) {
		log.info("param_insertPage : "+pMap.toString());
		model.addAttribute("deptList", service.getDeptList());
		model.addAttribute("jobList", service.getJobList());
		return "front/insertPage";
	}
	
	@PostMapping("/updatePage")
	public String updatePage(@RequestParam Map<String, Object> pMap, Model model) {
		log.info("param_updatePage : "+pMap.toString());
		model.addAttribute("empMap", service.getEmpOne(pMap).get(0));
		model.addAttribute("deptList", service.getDeptList());
		model.addAttribute("jobList", service.getJobList());
		return "front/updatePage";
	}
	@PostMapping("/empInsert")
	@ResponseBody
	public ModelMap empInsert(@RequestParam Map<String, Object> pMap) {
		log.info("param_empInsert : "+pMap.toString());
		ModelMap model = service.empInsert(pMap);
		log.info("model : "+model.toString());
		return model;
	}
	@PostMapping("/empUpdate")
	@ResponseBody
	public ModelMap empUpdate(@RequestParam Map<String, Object> pMap) {
		log.info("param_empUpdate : "+pMap.toString());
		ModelMap model = service.empUpdate(pMap);
		log.info("model : "+model.toString());
		return model;
	}
	@PostMapping("/empDelete")
	@ResponseBody
	public ModelMap empDelete(@RequestParam Map<String, Object> pMap) {
		log.info("param_empDelete : "+pMap.toString());
		ModelMap model = service.empDelete(pMap);
		log.info("model : "+model.toString());
		return model;
	}
	@PostMapping("/var")
	@ResponseBody
	public ModelMap exchange(@RequestParam Map<String, Object> pMap) {
		log.info("param_var : "+pMap.toString());
		ModelMap model = service.exchange(pMap);
		return model;
	}
	
}
