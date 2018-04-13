package com.hy.cb.dao.seller;

import java.util.Map;

import com.hy.cb.entity.seller.SeCustomer;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface SeCustomerDao extends BasicDao<SeCustomer> {

	 
	public PageInfo<SeCustomer> getPageList(Map<String, Object> params, SeCustomer entity, int pageNo, int pageSize);
}
