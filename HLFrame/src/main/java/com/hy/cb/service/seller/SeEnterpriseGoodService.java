package com.hy.cb.service.seller;

import com.hy.cb.entity.seller.SeEnterpriseGood;
import com.hy.sys.core.service.BasicService;

public interface SeEnterpriseGoodService  extends BasicService<SeEnterpriseGood>{

	/**
	 * 查询商家商品中除当前商品外，是否还有其它 商品存在此分类Id,
	 * @param ClassId
	 * @param goodsId
	 * @return
	 */
	public SeEnterpriseGood findGoodsByClassIdNotGoodsId(String ClassId, String goodsId);

	/**
	 * 查询该分类下的商品
	 * @param classId
	 * @return
	 */
	public SeEnterpriseGood findGoodsByClassId(String classId);

}
