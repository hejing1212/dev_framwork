package com.hy.cb.dao.seller;

import java.util.Map;

import com.hy.cb.entity.seller.SeShop;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface SeShopDao extends BasicDao<SeShop>{
	public PageInfo<SeShop> getPageList(Map<String, Object> params, SeShop entity, int pageNo, int pageSize);
}
