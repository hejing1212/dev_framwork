package com.hy.cb.service.seller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.seller.SeEnterpriseGoodDao;
import com.hy.cb.entity.seller.SeEnterpriseGood;
import com.hy.cb.service.seller.SeEnterpriseGoodService;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;

@Service("seEnterpriseGoodService")
public class SeEnterpriseGoodServiceImpl extends BasicServiceImpl<SeEnterpriseGood> implements SeEnterpriseGoodService {

	
	@Autowired
	private SeEnterpriseGoodDao seEnterpriseGoodDao;
	
	@Override
	public PageInfo<SeEnterpriseGood> getPageList(Map<String, Object> params, SeEnterpriseGood entity, int pageNo,
			int pageSize) {
		 return seEnterpriseGoodDao.getPageList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<SeEnterpriseGood> getBasicDao() {
		// TODO Auto-generated method stub
		return this.seEnterpriseGoodDao;
	}

	/**
	 * 查询该分类下的商品
	 * @param ClassId
	 * @return
	 */
	@Override
	public SeEnterpriseGood findGoodsByClassId(String classId) {
		return seEnterpriseGoodDao.findGoodsByClassId(classId);
	}
	/**
	 * 查询商家商品中除当前商品外，是否还有其它 商品存在此分类Id,
	 * @param ClassId
	 * @param goodsId
	 * @return
	 */
	@Override
	public SeEnterpriseGood findGoodsByClassIdNotGoodsId(String ClassId,String goodsId) {
		return seEnterpriseGoodDao.findGoodsByClassIdNotGoodsId(ClassId, goodsId);
	}
}
