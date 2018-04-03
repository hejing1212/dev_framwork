package com.hy.sys.dao;

import java.util.List;
import java.util.Map;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysArea;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;

public interface SysAreaDao extends BasicDao<SysArea>{

	public	PageInfo<SysArea> getList(Map<String, Object> params, SysArea entity, int pageNo, int pageSize);

	/**
	 * 查询列表
	 * @param params
	 * @param entity
	 * @return
	 */
	public List<SysArea> getAreaList(Map<String, Object> params, SysArea entity);

}
