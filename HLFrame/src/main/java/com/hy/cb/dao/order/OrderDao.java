package com.hy.cb.dao.order;

import java.util.List;
import java.util.Map;

import com.hy.cb.entity.order.OrderEntity;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface OrderDao extends BasicDao<OrderEntity> {

	/**
	 * 查询订单列表
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<OrderEntity> getPageList(Map<String, Object> params, OrderEntity entity, int pageNo, int pageSize);

	/**
	 * 获取所有订单数量 
	 * @param params
	 * @param entity
	 * @return
	 */
	public List<OrderEntity> getOrderListNum(Map<String, Object> params);
 

}
