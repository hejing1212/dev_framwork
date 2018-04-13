package com.hy.cb.dao.seller;

import java.util.Map;

import com.hy.cb.entity.seller.SeEnterpriseGood;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.utils.PageInfo;

public interface SeEnterpriseGoodDao extends BasicDao<SeEnterpriseGood> {

	public PageInfo<SeEnterpriseGood> getPageList(Map<String, Object> params, SeEnterpriseGood entity, int pageNo,
			int pageSize);

}
