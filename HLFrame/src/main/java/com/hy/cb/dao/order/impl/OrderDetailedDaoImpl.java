package com.hy.cb.dao.order.impl;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.order.OrderDetailedDao;
import com.hy.cb.entity.order.OrderDetailed;
import com.hy.sys.core.dao.impl.BasicDaoImpl;

@Repository("orderDetailedDao")
public class OrderDetailedDaoImpl extends  BasicDaoImpl<OrderDetailed> implements OrderDetailedDao{

	@Override
	public Class<OrderDetailed> getEntityClass() {
		// TODO Auto-generated method stub
		return OrderDetailed.class;
	}

}
