package com.hy.cb.service.order.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.order.OrderDao;
import com.hy.cb.dao.order.OrderDetailedDao;
import com.hy.cb.entity.order.OrderDetailed;
import com.hy.cb.entity.order.OrderEntity;
import com.hy.cb.service.order.OrderService;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;

@Service("orderService")
public class OrderServiceImpl extends BasicServiceImpl<OrderEntity> implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderDetailedDao orderDetailedDao;
	/**
	 * 查询订单列表
	 */
	@Override
	public PageInfo<OrderEntity> getPageList(Map<String, Object> params, OrderEntity entity, int pageNo, int pageSize) {
		return orderDao.getPageList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<OrderEntity> getBasicDao() {
		return this.orderDao;
	}
	/**
	 * 批量添加订单明细记录
	 * @param entities
	 */
	@Override
	public void saveBatch(Set<OrderDetailed> entities) {
		orderDetailedDao.saveBatch(entities);
	}

}
