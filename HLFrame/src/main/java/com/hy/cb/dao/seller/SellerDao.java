package com.hy.cb.dao.seller;

import java.util.Map;

import com.hy.cb.entity.seller.SeSeller;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface SellerDao extends BasicDao<SeSeller>{

	/**
	 * 获取分布列表数据
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<SeSeller> getPageList(Map<String, Object> params, SeSeller entity, int pageNo, int pageSize);

	/**
	 * 查询商户名是否存在
	 * @param username
	 * @return
	 */
	public SeSeller findByName(String username);

	
	
}
