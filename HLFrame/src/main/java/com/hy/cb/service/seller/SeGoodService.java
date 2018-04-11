package com.hy.cb.service.seller;

import com.hy.cb.entity.seller.SeGood;
import com.hy.sys.core.service.BasicService;

public interface SeGoodService extends BasicService<SeGood>{

	public SeGood findByName(String goodsName);

}
