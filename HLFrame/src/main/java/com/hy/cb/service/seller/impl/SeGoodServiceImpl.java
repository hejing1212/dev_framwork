package com.hy.cb.service.seller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.seller.SeGoodsDao;
import com.hy.cb.entity.seller.SeGood;
import com.hy.cb.service.seller.SeGoodService;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;

@Service("seGoodService")
public class SeGoodServiceImpl extends BasicServiceImpl<SeGood> implements SeGoodService{

	@Autowired
	private SeGoodsDao seGoodDao;
	
	@Override
	public PageInfo<SeGood> getPageList(Map<String, Object> params, SeGood entity, int pageNo, int pageSize) {
		return seGoodDao.getPageList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<SeGood> getBasicDao() {
		// TODO Auto-generated method stub
		return this.seGoodDao;
	}

	@Override
	public SeGood findByName(String goodsName) {		 
		return seGoodDao.findByName(goodsName);
	}

}
