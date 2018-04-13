package com.hy.cb.dao.seller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.seller.SeSellerCategoryDao;
import com.hy.cb.entity.seller.SeCustomer;
import com.hy.cb.entity.seller.SeGood;
import com.hy.cb.entity.seller.SeSellerGoodsCategory;
import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;
@Repository("seSellerCategoryDao")
public class SeSellerCategoryDaoImpl   extends BasicDaoImpl<SeSellerGoodsCategory> implements SeSellerCategoryDao{

	@Override
	public Class<SeSellerGoodsCategory> getEntityClass() {
		// TODO Auto-generated method stub
		return SeSellerGoodsCategory.class;
	}

	/**
	 * 得到商家自己的分类列表
	 */
	@Override
	public PageInfo<SeSellerGoodsCategory> getPageList(Map<String, Object> params, SeSellerGoodsCategory entity, int pageNo,
			int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  b.* ");
		sql.append(" FROM se_goods_category a, se_goods_category b");
		sql.append(" WHERE a.category_id=b.category_id ");
        //多个关键字查询
		if(StringTools.mapGetKeyIsEmpty(params, "queryKey"))  {
			sql.append(" AND ( a.category_name like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
		}	 	 
		 //多个关键字查询
		if(StringTools.mapGetKeyIsEmpty(params, "sellerId"))  {
			sql.append(" AND ( a.seller_id = ? )");
			values.add(params.get("sellerId").toString().trim());
		}
		sql.append(" ORDER BY sort DESC");
		return (PageInfo<SeSellerGoodsCategory>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SeSellerGoodsCategory.class);
	}
 
}
