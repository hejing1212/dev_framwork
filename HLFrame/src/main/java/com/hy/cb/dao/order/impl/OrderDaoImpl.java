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
public class OrderDaoImpl extends BasicDaoImpl<OrderEntity> implements OrderDao {

	@Override
	public Class<OrderEntity> getEntityClass() {
		return OrderEntity.class;
	}

	/**
	 * 查询订单列表
	 * 
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<OrderEntity> getPageList(Map<String, Object> params, OrderEntity entity, int pageNo, int pageSize) {

		StringBuffer sqlwhere = new StringBuffer();
		List<Object> values = new ArrayList<Object>();

		String sql = " FROM OrderEntity WHERE 1=1 ";
		String hqlCount = "SELECT count(*) FROM OrderEntity WHERE 1=1";
		// 供应商
		if (StringTools.mapGetKeyIsEmpty(params, "supplierNo")) {
			sqlwhere.append(" AND ( supplier_no = ?)");
			values.add(params.get("supplierNo").toString().trim());
		}
		// 采购商ID
		if (StringTools.mapGetKeyIsEmpty(params, "purchaseNo")) {
			sqlwhere.append(" AND ( purchase_no = ?)");
			values.add(params.get("purchaseNo").toString().trim());
		}
		// 档口ID
		if (StringTools.mapGetKeyIsEmpty(params, "shopNo")) {
			sqlwhere.append(" AND ( shop_no = ?)");
			values.add(params.get("shopNo").toString().trim());
		}
		// 订单类型
		if (StringTools.mapGetKeyIsEmpty(params, "orderType")) {
			sqlwhere.append(" AND ( order_type = ?)");
			values.add(params.get("orderType").toString().trim());
		}
		// 订单状态
		if (StringTools.mapGetKeyIsEmpty(params, "stauts")) {
			sqlwhere.append(" AND ( stauts = ?)");
			values.add(params.get("stauts").toString().trim());
		}
		// 是否扎帐
		if (StringTools.mapGetKeyIsEmpty(params, "balance")) {
			sqlwhere.append(" AND ( balance = ?)");
			values.add(params.get("balance").toString().trim());
		}
		// 支付状态
		if (StringTools.mapGetKeyIsEmpty(params, "payStatus")) {
			sqlwhere.append(" AND ( pay_status = ?)");
			values.add(params.get("payStatus").toString().trim());
		}
		// 销售方式{1合伙,2代销}
		if (StringTools.mapGetKeyIsEmpty(params, "consignment")) {
			sqlwhere.append(" AND ( consignment = ?)");
			values.add(params.get("consignment").toString().trim());
		}

		if (StringTools.isNotBlank(sqlwhere)) {
			hqlCount = hqlCount + sqlwhere.toString();
			sql = sql.toString() + sqlwhere.toString();
		}
		sql = sql + " ORDER BY create_time DESC";

		return this.findPageInfoByQuery(pageNo, pageSize, sql, hqlCount, values.toArray());
	}
}
