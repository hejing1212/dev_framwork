package com.hy.cb.service.seller;

import com.hy.cb.entity.seller.SeSeller;
import com.hy.sys.core.service.BasicService;

public interface SellerService extends BasicService<SeSeller> {
	/**
	 * 查询商户名是否存在
	 * @param name
	 * @return
	 */
	public SeSeller findByName(String name);

}
