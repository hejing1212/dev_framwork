package com.hy.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysLogDao;
import com.hy.sys.dao.SysUserDao;
import com.hy.sys.entity.SysLog;
import com.hy.sys.service.LogService;
import com.hy.sys.utils.PageInfo;
@Service("sysLogService")
public class LogServiceImpl extends BasicServiceImpl<SysLog> implements LogService{

	@Autowired
	private SysLogDao logDao;
	
	 
	
	@Override
	public PageInfo<SysLog> getPageList(Map<String, Object> params, SysLog entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BasicDao<SysLog> getBasicDao() {
		// TODO Auto-generated method stub
		return logDao;
	}
	

}