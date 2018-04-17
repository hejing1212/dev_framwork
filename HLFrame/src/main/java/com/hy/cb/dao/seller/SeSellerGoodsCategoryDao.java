package com.hy.cb.dao.seller;

import java.util.Map;

import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.cb.entity.seller.SeSellerGoodsCategory;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface SeSellerGoodsCategoryDao extends BasicDao<SeSellerGoodsCategory> {

	public PageInfo<SeGoodsCategory> getPageList(Map<String, Object> params, SeSellerGoodsCategory entity, int pageNo,
			int pageSize);

	/**
	 * 根据系统商品编号查询商家商品编号中是否存在
	 * @param classId
	 * @return
	 */
	public SeSellerGoodsCategory GetSellerGoodsCategoryByClassId(String classId);

	/**
	 * 删除商家自己的商品分类
	 * @param categoryId
	 */
	public void delSellerCategoryByCategoryId(String categoryId);
}
