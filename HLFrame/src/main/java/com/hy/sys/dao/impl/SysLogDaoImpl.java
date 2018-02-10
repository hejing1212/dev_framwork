package com.hy.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysLogDao;
import com.hy.sys.entity.SysLog;

@Repository("logDao")
public class SysLogDaoImpl extends  BasicDaoImpl<SysLog> implements SysLogDao{

	@Override
	public Class<SysLog> getEntityClass() {
		// TODO Auto-generated method stub
		return SysLog.class;
	}

}
