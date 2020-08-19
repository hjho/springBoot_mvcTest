package com.example.back.util;

import java.util.ArrayList;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.Order.Direction;

public abstract class AbstractMybatisUtils {

	protected static final int DEF_ROW_COUNT = 15;			// default

	/**
	 * 	(*1) Unique 하지 않는 키로 order by 시 첫페이지에서 조회된 결과 일부가 두번째 페이지에서도 조회되어
	 * 		중복 표시되는 일이 생긴다.
	 * 		일반적인 처리를 위해 첫번째 오는 칼럼을 추가로 order by 주기로 한다.
	 * 		대부분은 첫 컬럼이 PK인 경우가 대부분이기 때문이다.
	 * @param offset
	 * @param limit
	 * @param order
	 * @return
	 */
	public static PageBounds pageBounds(Integer page, Integer limit, Order order) {
		if(page == null || page == 0) page = 1;			// default
		if(limit == null || limit == 0) limit = DEF_ROW_COUNT;	// default

		Order orderApd = new Order("1", Direction.ASC, null); //(*1)
		return order != null ? new PageBounds(page, limit, order, orderApd) : new PageBounds(page, limit, orderApd); 
	}

	public static PageBounds pageBounds(Integer page, Integer limit) {
		return pageBounds(page, limit, (Order)null); 
	}
	
	public static PageBounds pageBounds(Integer page, Integer limit, List<Order> orders) {
		if(page == null || page == 0) page = 1;			// default
		if(limit == null || limit == 0) limit = DEF_ROW_COUNT;	// default

		Order orderApd = new Order("1", Direction.ASC, null); //(*1)
		if(orders != null && ! orders.isEmpty()) {
			orders.add(orderApd);
			return new PageBounds(page, limit, orders);
		} else {
			return new PageBounds(page, limit, orderApd);
		}
	}

	/**
	 * 페이징 표준 처리. Order By 파라미터 포함된 경우 
	 * @param page 현재 페이지 (1부터, default)
	 * @param limit 한 페이지 당 조회 row 수
	 * @param orderByColumn order-by 절 칼럼 (복수 경우 comma 구분) (예, "A,B,..)
	 * @param ascDesc order-by 절 방향 (복수 경우 comma 구분) (예, "desc,asc,..)
	 * @return
	 */
	public static PageBounds pageBounds(Integer page, Integer limit, String orderByColumn, String ascDesc) {
		List<Order> orders = new ArrayList<>();
		String columns[] = orderByColumn.split("[, ]+");
		String directs[] = ascDesc.split("[, ]+");
		System.out.println("orderByColumn : "+orderByColumn+", ascDesc : "+ascDesc);
		for(int i = 0; i < columns.length; i++) {
			System.out.print(i+". columns : "+columns[i]+", directs : "+directs[i]+"\n");
			Direction direct = i < directs.length && directs[i].equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
			Order order = new Order(columns[i], direct, null);
			orders.add(order);
		}
		return pageBounds(page, limit, orders);
	}

}
