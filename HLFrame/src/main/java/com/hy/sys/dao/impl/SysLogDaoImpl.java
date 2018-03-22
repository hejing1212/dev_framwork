package com.hy.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysLogDao;
import com.hy.sys.entity.SysLog;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;

@Repository("logDao")
public class SysLogDaoImpl extends  BasicDaoImpl<SysLog> implements SysLogDao{

	@Override
	public Class<SysLog> getEntityClass() {
		// TODO Auto-generated method stub
		return SysLog.class;
	}

	@Override
	public PageInfo<SysLog> getPageList(Map<String, Object> params, SysLog entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM sys_log   ");
		sql.append(" WHERE 1=1 ");

		if (params.containsKey("queryKey") && params.get("queryKey") != null && !"".equals(params.get("queryKey"))) {
			sql.append(" AND ( opt_content like ?)");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");			
		}

		sql.append(" ORDER BY create_date DESC");
		return (PageInfo<SysLog>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SysLog.class);
	}
	
}
