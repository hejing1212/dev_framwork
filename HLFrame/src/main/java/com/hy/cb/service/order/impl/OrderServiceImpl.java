package com.hy.cb.service.order.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.order.OrderDao;
import com.hy.cb.entity.order.OrderEntity;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;

@Service("orderService")
public class OrderServiceImpl extends BasicServiceImpl<OrderEntity> implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
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

}
