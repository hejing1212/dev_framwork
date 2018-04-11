package com.hy.cb.service.seller;

import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.sys.core.service.BasicService;

public interface SeGoodsCategoryService extends BasicService<SeGoodsCategory> {

	/**
	 * 查询分类 名称是否存在
	 * @param categoryName
	 * @return
	 */
	public SeGoodsCategory findByName(String categoryName);

}
