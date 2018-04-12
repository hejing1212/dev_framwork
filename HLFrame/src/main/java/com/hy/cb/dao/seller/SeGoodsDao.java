package com.hy.cb.dao.seller;

import java.util.Map;

import com.hy.cb.entity.seller.SeGood;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface SeGoodsDao extends BasicDao<SeGood>{

	public SeGood findByName(String goodsName);
	
	public PageInfo<SeGood> getPageList(Map<String, Object> params, SeGood entity, int pageNo, int pageSize);
}
