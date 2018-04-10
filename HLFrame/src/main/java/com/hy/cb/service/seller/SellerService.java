package com.hy.cb.service.seller;

import java.util.Map;

import com.hy.cb.entity.seller.SeSeller;
import com.hy.sys.core.service.BasicService;

public interface SellerService extends BasicService<SeSeller> {
	/**
	 * 查询商户名是否存在
	 * @param name
	 * @return
	 */
	public SeSeller findByName(String name);

	/**
	 * 添加商家信息
	 * @param entity
	 * @return
	 */
	public Map<String, Object> saveSeller(SeSeller entity);

}
