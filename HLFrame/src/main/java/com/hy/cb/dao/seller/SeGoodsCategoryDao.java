package com.hy.cb.dao.seller;

import java.util.Map;

import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.cb.entity.seller.SeSeller;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface SeGoodsCategoryDao extends BasicDao<SeGoodsCategory> {

	/**
	 * 检查分类名称是否存在 
	 * @param categoryName
	 * @return
	 */
	public SeGoodsCategory findByName(String categoryName);

	public PageInfo<SeGoodsCategory> getPageList(Map<String, Object> params, SeGoodsCategory entity, int pageNo, int pageSize);
}
