package com.hy.sys.service;

import java.util.List;
import java.util.Map;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysArea;

public interface SysAreaService extends BasicService<SysArea>{

	/**
	 * 查询区域列表
	 * @param params
	 * @param entity
	 * @return
	 */
	public List<SysArea> getAreaList(Map<String, Object> params, SysArea entity);

}
