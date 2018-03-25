package com.hy.sys.dao;

import java.util.Map;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysDataDict;
import com.hy.sys.utils.PageInfo;

/**
 * 数据字典管理
 * @author hejing
 *
 */
public interface SysDictDao extends BasicDao<SysDataDict>{

	/**
	 * 获取数据字列表
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public	PageInfo<SysDataDict> getPageList(Map<String, Object> params, SysDataDict entity, int pageNo, int pageSize);

	
	/**
	 * 查询是否存在同名的字典
	 * @param dictName
	 * @return
	 */
	public SysDataDict getDictName(String dictName);

}
