package com.example.front.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmpPageService {
	
	
	@Autowired private RestTemplate restTemplate;
	private String url = "http://localhost:8800/";
	
	/**
	 * restTemplate.getForObject  (url, responseType, uriVariables); //인자를 파라미터로 넘기기엔 코드가 길어짐.
	 * restTemplate.postForObject (url, request     , responseType   , uriVariables); //효율성이 제일 높다.
	 * restTemplate.put           (url, request     , uriVariables);  //리턴 타입이 없음.
	 * restTemplate.exchange      (url, method      , requestEntity  , responseType, uriVariables);
	 */
	
	public ModelMap getEmpList(Map<String, Object> pMap) {
		return restTemplate.postForObject(url+"emp/pageEmpList", pMap, ModelMap.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getEmpOne(Map<String, Object> pMap) {
		return restTemplate.postForObject(url+"emp/list", pMap, List.class);
	}
	
	@SuppressWarnings("unchecked") //검증되지 않은 연산자 관련 경고 억제
	public List<Map<String, Object>> getDeptList() {
		return restTemplate.getForObject(url+"dept/list", List.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getJobList() {
		return restTemplate.getForObject(url+"emp/jobList", List.class);
	}

	public ModelMap empInsert(Map<String, Object> pMap) {
		return restTemplate.postForObject(url+"emp/ins", pMap, ModelMap.class);
	}

	public ModelMap empUpdate(Map<String, Object> pMap) {
		return restTemplate.postForObject(url+"emp/upd", pMap, ModelMap.class);
	}

	public ModelMap empDelete(Map<String, Object> pMap) {
		return restTemplate.postForObject(url+"emp/del", pMap, ModelMap.class);
	}
	
	public ModelMap exchange(Map<String, Object> pMap) {
		//HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<ModelMap> model = restTemplate.exchange(url+"emp/{type}", HttpMethod.POST, new HttpEntity<Map<String,Object>>(pMap), ModelMap.class, pMap);
		log.info("exchange {} ", model);
		return model.getBody();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getEmpPageList(Map<String, Object> pMap) {
		return restTemplate.postForObject(url+"emp/pageList", pMap, List.class);
	}

}
