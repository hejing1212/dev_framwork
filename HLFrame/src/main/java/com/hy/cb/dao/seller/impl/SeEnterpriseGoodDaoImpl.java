package com.hy.cb.dao.seller.impl;

import org.springframework.stereotype.Repository;

import com.hy.cb.dao.seller.SeEnterpriseGoodDao;
import com.hy.cb.entity.seller.SeEnterpriseGood;
import com.hy.sys.core.dao.impl.BasicDaoImpl;

@Repository("seEnterpriseGood")
public class SeEnterpriseGoodDaoImpl extends BasicDaoImpl<SeEnterpriseGood> implements SeEnterpriseGoodDao{

	@Override
	public Class<SeEnterpriseGood> getEntityClass() {
		// TODO Auto-generated method stub
		return SeEnterpriseGood.class;
	}

}
