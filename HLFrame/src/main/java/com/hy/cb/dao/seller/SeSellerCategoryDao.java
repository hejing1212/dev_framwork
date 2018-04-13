package com.hy.cb.dao.seller;

import java.util.Map;

import com.hy.cb.entity.seller.SeSellerGoodsCategory;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface SeSellerCategoryDao extends BasicDao<SeSellerGoodsCategory> {

	public PageInfo<SeSellerGoodsCategory> getPageList(Map<String, Object> params, SeSellerGoodsCategory entity, int pageNo,
			int pageSize);
}
