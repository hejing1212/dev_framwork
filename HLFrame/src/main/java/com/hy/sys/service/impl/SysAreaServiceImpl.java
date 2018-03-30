package com.hy.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysAreaDao;
import com.hy.sys.entity.SysArea;
import com.hy.sys.service.SysAreaService;
import com.hy.sys.utils.PageInfo;
@Service("sysAreaService")
public class SysAreaServiceImpl extends BasicServiceImpl<SysArea> implements SysAreaService{

	
	@Autowired
	private SysAreaDao sysAreaDao;
	
	@Override
	public PageInfo<SysArea> getPageList(Map<String, Object> params, SysArea entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return sysAreaDao.getList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<SysArea> getBasicDao() {
		// TODO Auto-generated method stub
		return this.sysAreaDao;
	}

}
