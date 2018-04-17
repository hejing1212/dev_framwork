package com.hy.cb.dao.order.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.order.OrderDao;
import com.hy.cb.entity.order.OrderEntity;
import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Repository("orderDao")
public class OrderDaoImpl extends  BasicDaoImpl<OrderEntity> implements OrderDao{

	@Override
	public Class<OrderEntity> getEntityClass() {
		return OrderEntity.class;
	}

	/**
	 *查询订单列表
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<OrderEntity> getPageList(Map<String, Object> params, OrderEntity entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM or_order   ");
		sql.append(" WHERE 1=1 ");
         
		if(StringTools.mapGetKeyIsEmpty(params, "supplier_no"))  {
			sql.append(" AND ( supplier_no = ?)");
			values.add(params.get("supplier_no").toString().trim());
			 
		}
		if(StringTools.mapGetKeyIsEmpty(params, "purchase_no"))  {
			sql.append(" AND ( purchase_no = ?)");
			values.add(params.get("purchase_no").toString().trim());
			 
		}
		
		if(StringTools.mapGetKeyIsEmpty(params, "shop_no"))  {
			sql.append(" AND ( shop_no = ?)");
			values.add(params.get("shop_no").toString().trim());
			 
		}
		
		sql.append(" ORDER BY create_time DESC");
		return (PageInfo<OrderEntity>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), OrderEntity.class);
	}
}
