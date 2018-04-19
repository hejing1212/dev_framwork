package com.hy.cb.service.order;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hy.cb.entity.order.OrderDetailed;
import com.hy.cb.entity.order.OrderEntity;
import com.hy.sys.core.service.BasicService;

public interface OrderService  extends BasicService<OrderEntity>{

	/**
	 * 批量添加订单明细记录
	 * @param entities
	 */
	public void saveBatch(Set<OrderDetailed> entities);

	/**
	 * 查询各种状态下的订单数量
	 * @param params
	 * @param entity
	 * @return
	 */
	public List<OrderEntity> getOrderListNum(Map<String, Object> params);

}
