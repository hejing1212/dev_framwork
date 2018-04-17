package com.hy.cb.dao.seller;

import java.util.Map;

import com.hy.cb.entity.seller.SeEnterpriseGood;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface SeEnterpriseGoodDao extends BasicDao<SeEnterpriseGood> {

	public PageInfo<SeEnterpriseGood> getPageList(Map<String, Object> params, SeEnterpriseGood entity, int pageNo,
			int pageSize);

	/**
	 * 查询该分类下的商品
	 * @param ClassId
	 * @return
	 */
	public SeEnterpriseGood findGoodsByClassId(String ClassId);

	/**
	 * 查询除当前商品外，是否还有其它 商品存在此分类Id,
	 * @param ClassId
	 * @param goodsId
	 * @return
	 */
	public SeEnterpriseGood findGoodsByClassIdNotGoodsId(String ClassId, String goodsId);

}
