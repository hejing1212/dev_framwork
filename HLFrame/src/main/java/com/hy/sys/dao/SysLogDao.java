package com.hy.sys.dao;

import java.util.Map;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysLog;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;

public interface SysLogDao  extends BasicDao<SysLog>{

	/**
	 * 带分页查询
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<SysLog> getPageList(Map<String, Object> params, SysLog entity, int pageNo, int pageSize);

}
