package com.hy.cb.service.seller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.seller.SeShopDao;
import com.hy.cb.entity.seller.SeShop;
import com.hy.cb.service.seller.SeShopService;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;
@Service("seShopService")
public class SeShopServiceImpl  extends BasicServiceImpl<SeShop> implements SeShopService{

	@Autowired
	private SeShopDao seShopDao;
	
	@Override
	public PageInfo<SeShop> getPageList(Map<String, Object> params, SeShop entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return seShopDao.getPageList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<SeShop> getBasicDao() {
		// TODO Auto-generated method stub
		return seShopDao;
	}

}
