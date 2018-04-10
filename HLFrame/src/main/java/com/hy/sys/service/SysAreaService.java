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

	/**
	 * 查询各个省的地区子节点列表
	 * @param params
	 * @return
	 */
	public List<SysArea> getAreaListByTopPareatId(String pareatId);

	/**
	 * 获取所有区域
	 * @return
	 */
	public List<SysArea> getAllAreaList(String pareatId);

	/**
	 * 根据父ID获取下级地区
	 * @param pareatId
	 * @return
	 */
	public List<SysArea> getAreaList(String pareatId);
 

}
