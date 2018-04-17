package com.hy.cb.service.seller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.seller.SeSellerGoodsCategoryDao;
import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.cb.entity.seller.SeSellerGoodsCategory;
import com.hy.cb.service.seller.SeSellerGoodsCategoryService;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;

@Service("seSellerGoodsCategoryService")
public class SeSellerGoodsCategoryServiceImpl extends BasicServiceImpl<SeSellerGoodsCategory> implements SeSellerGoodsCategoryService{

	@Autowired
	private SeSellerGoodsCategoryDao seSellerGoodsCategoryDao;
	/**
	 * 获取商家商品分类列表
	 */
	@Override
	public PageInfo<SeGoodsCategory> getSellerCategoryPageList(Map<String, Object> params, SeSellerGoodsCategory entity, int pageNo,
			int pageSize) {
		return seSellerGoodsCategoryDao.getPageList(params, entity, pageNo, pageSize);
	}

	
	@Override
	protected BasicDao<SeSellerGoodsCategory> getBasicDao() {
		return seSellerGoodsCategoryDao;
	}
	
	/**
	 * 根据系统商品编号查询商家商品编号中是否存在
	 * @param classId
	 * @return
	 */
	@Override
	public SeSellerGoodsCategory GetSellerGoodsCategoryByClassId(String classId) {
		return seSellerGoodsCategoryDao.GetSellerGoodsCategoryByClassId(classId);
	}
	/**
	 * 删除商家自己的商品分类
	 * @param classId
	 * @return
	 */
	@Override
	public void delSellerCategoryByCategoryId(String categoryId) {
		seSellerGoodsCategoryDao.delSellerCategoryByCategoryId(categoryId);		
	}


	@Override
	public PageInfo<SeSellerGoodsCategory> getPageList(Map<String, Object> params, SeSellerGoodsCategory entity,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}
