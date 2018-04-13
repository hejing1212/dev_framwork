package com.hy.cb.dao.seller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.seller.SeCustomerDao;
import com.hy.cb.entity.seller.SeCustomer;
import com.hy.cb.entity.seller.SeGood;
import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Repository("seCustomerDao")
public class SeCustomerDaoImpl extends BasicDaoImpl<SeCustomer> implements SeCustomerDao {
	@Override
	public Class<SeCustomer> getEntityClass() {
		// TODO Auto-generated method stub
		return SeCustomer.class;
	}

	@Override
	public PageInfo<SeCustomer> getPageList(Map<String, Object> params, SeCustomer entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM se_customer ");
		sql.append(" WHERE 1=1 ");
        //多个关键字查询
		if(StringTools.mapGetKeyIsEmpty(params, "queryKey"))  {
			sql.append(" AND ( ep_name like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
		}	 	 
		 //多个关键字查询
		if(StringTools.mapGetKeyIsEmpty(params, "sellerId"))  {
			sql.append(" AND ( seller_id = ? )");
			values.add(params.get("sellerId").toString().trim());
		}
		sql.append(" ORDER BY sort DESC");
		return (PageInfo<SeCustomer>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SeCustomer.class);
	}
}
