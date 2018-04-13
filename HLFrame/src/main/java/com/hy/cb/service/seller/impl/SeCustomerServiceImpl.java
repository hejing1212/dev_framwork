package com.hy.cb.service.seller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.cb.dao.seller.SeCustomerDao;
import com.hy.cb.entity.seller.SeCustomer;
import com.hy.cb.service.seller.SeCustomerService;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.utils.PageInfo;

@Service("seCustomerService")
public class SeCustomerServiceImpl extends BasicServiceImpl<SeCustomer> implements SeCustomerService {

	@Autowired
	private SeCustomerDao seCustomerDao;
	
	@Override
	public PageInfo<SeCustomer> getPageList(Map<String, Object> params, SeCustomer entity, int pageNo, int pageSize) {		 
		return 	seCustomerDao.getPageList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<SeCustomer> getBasicDao() {
		// TODO Auto-generated method stub
		return seCustomerDao;
	}

}
