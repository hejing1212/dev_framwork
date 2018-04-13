package com.hy.cb.service.seller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.seller.SeSellerCategoryDao;
import com.hy.cb.entity.seller.SeSellerGoodsCategory;
import com.hy.cb.service.seller.SeSellerCategoryService;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;

@Service("seSellerCategoryService")
public class SeSellerCategoryServiceImpl extends BasicServiceImpl<SeSellerGoodsCategory> implements SeSellerCategoryService{

	@Autowired
	private SeSellerCategoryDao seSellerCategoryDao;
	/**
	 * 获取商家商品分类列表
	 */
	@Override
	public PageInfo<SeSellerGoodsCategory> getPageList(Map<String, Object> params, SeSellerGoodsCategory entity, int pageNo,
			int pageSize) {
		return seSellerCategoryDao.getPageList(params, entity, pageNo, pageSize);
	}

	
	@Override
	protected BasicDao<SeSellerGoodsCategory> getBasicDao() {
		return seSellerCategoryDao;
	}

}
