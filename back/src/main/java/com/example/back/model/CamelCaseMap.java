package com.example.back.model;

import java.util.HashMap;

import org.apache.ibatis.type.Alias;
import org.springframework.jdbc.support.JdbcUtils;


@Alias("CamelMap")
public class CamelCaseMap extends HashMap<Object, Object> {
	
	//경고 제거
	private static final long serialVersionUID = 1L;

	@Override
	public Object put(Object key, Object value) {
		return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String)key), value);
		
	}
	
}
