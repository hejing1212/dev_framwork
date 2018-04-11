package com.hy.cb.service.seller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.seller.SeGoodsCategoryDao;
import com.hy.cb.entity.seller.SeGoodsCategory;
import com.hy.cb.service.seller.SeGoodsCategoryService;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;

@Service("seGoodsCategoryService")
public class SeGoodsCategoryServiceImpl extends BasicServiceImpl<SeGoodsCategory> implements SeGoodsCategoryService{

	@Autowired
	private SeGoodsCategoryDao seGoodsCategoryDao;
	
	@Override
	public PageInfo<SeGoodsCategory> getPageList(Map<String, Object> params, SeGoodsCategory entity, int pageNo,
			int pageSize) {
		return seGoodsCategoryDao.getPageList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<SeGoodsCategory> getBasicDao() {
		// TODO Auto-generated method stub
		return this.seGoodsCategoryDao;
	}

	/**
	 * 检查分类名称是存在
	 */
	@Override
	public SeGoodsCategory findByName(String categoryName) {
		return seGoodsCategoryDao.findByName(categoryName);
	}

}
