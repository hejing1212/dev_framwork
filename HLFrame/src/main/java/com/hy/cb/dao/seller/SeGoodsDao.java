package com.hy.cb.dao.seller;

import com.hy.cb.entity.seller.SeGood;
import com.hy.sys.core.dao.BasicDao;

public interface SeGoodsDao extends BasicDao<SeGood>{

	public SeGood findByName(String goodsName);

}
