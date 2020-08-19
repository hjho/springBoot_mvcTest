package com.example.back.util;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Component
public class RestResponseUtil {

	/* ==================================================================================
	 * 	Rest Server 에서 결과 Response 리턴시 모델에 데이터 넣기
	 */

	/*
	 * 일반 Object 성공
	 */
	public static ModelMap putSuccess(ModelMap model, Object data, String message) {
		model.put("result", true);
		if(message != null) model.put("message", message);
		if(data != null) model.put("data", data);
		return model;
	}
	public static ModelMap putSuccess(Object data, String message) {
		ModelMap model = new ModelMap();
		putSuccess(model, data, message);
		return model;
	}
	public static ModelMap putSuccess(Object data) {
		return putSuccess(data, null);
	}
	public static ModelMap putSuccess() {
		return putSuccess(null);
	}
	public static ModelMap putSuccess(ModelMap model, Object data) {
		return putSuccess(model, data, null);
	}

	public static String putJsonSuccess(ModelMap model, Object data, String message) {
		putSuccess(model, data, message);
		return "jsonView";
	}
	public static String putJsonSuccess(ModelMap model, Object data) {
		return putJsonSuccess(model, data, null);
	}

	/*
	 * 목록 PageList<Object> 성공
	 */
	public static ModelMap putListSuccess(ModelMap model, PageList<?> dataList, String message) {
		putSuccess(model, dataList, message);
		model.put("paginator", dataList.getPaginator());
		return model;
	}	
	public static ModelMap putListSuccess(PageList<?> dataList, String message) {
		ModelMap model = putSuccess(dataList, message);
		model.put("paginator", dataList.getPaginator());
		return model;
	}	
	public static ModelMap putListSuccess(PageList<?> dataList) {
		return putListSuccess(dataList, null);
	}
	public static ModelMap putListSuccess(ModelMap model, PageList<?> dataList) {
		return putListSuccess(model, dataList, null);
	}
	public static ModelMap putListSuccess(List<?> dataList) {
		return putSuccess(dataList, null);	// 페이징이 아닌 일반 리스트인 경우.
	}

	/*
	 * 실패
	 */
	public static ModelMap _putFailModel(ModelMap model, String message, String detail, Object data) {
		model.put("result", false);
		model.put("message", message);
		if(detail != null) model.put("detail", detail);
		if(data != null) model.put("data", data);
		return model;
	}
	public static ModelMap _putFail(String message, String detail, Object data) {
		ModelMap model = new ModelMap();
		_putFailModel(model, message, detail, data);
		return model;
	}

	public static ModelMap putFail(ModelMap model, String message, String detail, Object data) {
		return _putFailModel(model, message, detail, data);
	}
	public static ModelMap putFail(ModelMap model, String message, Object data) {
		return _putFailModel(model, message, null, data);
	}
	public static ModelMap putFail(ModelMap model, String message, String detail) {
		return _putFailModel(model, message, detail, null);
	}
	public static ModelMap putFail(ModelMap model, String message) {
		return _putFailModel(model, message, null, null);
	}

	public static ModelMap putFail(String message, String detail, Object data) {
		return _putFail(message, detail, data);
	}
	public static ModelMap putFail(String message, Object data) {
		return _putFail(message, null, data);
	}
	public static ModelMap putFail(String message, String detail) {
		return _putFail(message, detail, null);
	}
	public static ModelMap putFail(String message) {
		return _putFail(message, null, null);
	}

	public static String putJsonFail(ModelMap model, String message, String detail, Object data) {
		_putFailModel(model, message, detail, data);
		return "jsonView";
	}
	public static String putJsonFail(ModelMap model, String message, String detail) {
		return putJsonFail(model, message, detail, null);
	}
	public static String putJsonFail(ModelMap model, String message) {
		return putJsonFail(model, message, null, null);
	}

	/*
	 * 결과
	 */

	public static ModelMap putOkNok(boolean ok, String errMessage) {
		return ok ? putSuccess() : putFail(errMessage);
	}

	public static ModelMap putOkNok(boolean ok) {
		return putOkNok(ok, "처리실패");
	}
	
	/* ==================================================================================
	 * 	Rest Client 에서 결과 Response 리턴시 모델의 데이터 확인
	 */

	/**
	 * 성공여부 확인
	 * @param model 받은 결과
	 * @return true/false
	 */
	public static boolean isSuccess(Map<String, Object> model) {
		return (Boolean)model.get("result");
	}
	
	public static String getMessage(Map<String, Object> model) {
		return (String)model.get("message");
	}
	
	public static Object getData(Map<String, Object> model) {
		return model.get("data");
	}

}
