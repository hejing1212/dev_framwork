package com.hy.cb.dao.seller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.seller.SeShopDao;
import com.hy.cb.entity.seller.SeShop;
import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;
@Repository("seShopDao")
public class SeShopDaoImpl extends BasicDaoImpl<SeShop> implements SeShopDao{

	@Override
	public Class<SeShop> getEntityClass() {
		// TODO Auto-generated method stub
		return SeShop.class;
	}

	@Override
	public PageInfo<SeShop> getPageList(Map<String, Object> params, SeShop entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM se_shop   ");
		sql.append(" WHERE 1=1 ");
        //多个关键字查询
		if(StringTools.mapGetKeyIsEmpty(params, "queryKey"))  {
			sql.append(" AND ( name like ? OR telephone like ? OR address like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
			values.add("%" + key + "%");
			values.add("%" + key + "%");
		}	 
		sql.append(" ORDER BY create_date DESC");
		return (PageInfo<SeShop>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SeShop.class);
	}

}
