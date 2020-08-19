package com.example.back.util;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

import java.util.Map;

import org.springframework.ui.ModelMap;

public class MybatisUtils extends AbstractMybatisUtils {

	/**
	 * 페이징 표준 처리
	 * @param param
	 * 		"page"	: 현재 페이지. 1부터(default)
	 * 		"limit" : 페이지 당 조회 row 수. (default: 15)
	 * 		"orderByColumn" : order-by 절 컬럼(들). 복수경우 comma 구분.
	 * 		"ascDesc" : order-by 절 방향(들). 복수경우 comma 구분. (예, "DESC,ASC")
	 * @return Mybatis 페이징 처리 파라미터
	 */
	public static PageBounds pageBounds(Map<String, Object> param) {
		int page = getIntCase(param.get("pageNo"), 1);
		int limit = getIntCase(param.get("countPage"), 5); //DEF_ROW_COUNT

		if(param.get("page") != null) page = getIntCase(param.get("page"), null);
		if(param.get("limit") != null) limit = getIntCase(param.get("limit"), null);
		String orderByColumn = param.get("ordCol") != null ? param.get("ordCol").toString() : null;
		if(orderByColumn != null && ! orderByColumn.isEmpty()) {
			String direction = param.get("ordOrd") != null ? param.get("ordOrd").toString() : null;
			return pageBounds(page, limit, toUnderline(orderByColumn), direction);
		} else {
			return pageBounds(page, limit);
		}
	}

	public static String toUnderline(String s) {
		String regex = "([a-z])([A-Z]+)";
		String replacement = "$1_$2";
		String ret = s.replaceAll(regex, replacement).toLowerCase();
		System.out.println("ret : "+ret);
		return ret;
	}

	private static Integer getIntCase(Object val, Integer def) {
		if(val == null) {
			return def;
		} else if(val instanceof Integer) {
			return (int)val;
		} else {
			return Integer.parseInt((String)val);
		}
	}

	
	//페이징처리 안하는데 형식만 필요할 때 사용 (무제한, totalCount 없음)
	//20191017
	public static PageBounds pageBounds() {
		return pageBounds(1, Integer.MAX_VALUE);
	}

	//CommonParamMap 이 없음
//	public static PageBounds pageBounds(CommonParamMap param) {
//		return pageBounds(param.getMap());
//	}

//	public static PageBounds pageBounds(PageVO param) {
//		String sidx = param.getOrderByColumn();
//		if(sidx != null && ! sidx.isEmpty()) {
//			Order order = new Order(sidx, param.getAscDesc().equals("desc") ? Direction.DESC : Direction.ASC, null);
//			return pageBounds(Integer.parseInt(param.getPageNo()), param.getCountPage(), order);
//		} else {
//			return pageBounds(Integer.parseInt(param.getPageNo()), param.getCountPage());
//		}
//	}

	public static void putModelListPage(PageList<?> resultList, ModelMap model) {
		Paginator paginator = resultList.getPaginator();
		model.put("paginator", paginator);
		model.put("resultList", resultList);
		//log.debug("list(" + list.size() + ") = " + list.toString());
		//log.debug("list size= " + list.size() + ", paginator= " + paginator.toString());
	}

}
