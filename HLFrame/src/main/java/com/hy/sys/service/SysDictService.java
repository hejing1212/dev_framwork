package com.hy.sys.service;

import java.util.List;
import java.util.Set;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysDataDict;
import com.hy.sys.entity.SysRole;

public interface SysDictService extends BasicService<SysDataDict> {

	/**
	 * 查询是否存在同名的字典
	 * 
	 * @param dictName
	 * @return
	 */
	public SysDataDict getDictName(String dictName);

	/**
	 * 根据数据字典值查询数据字典及键值
	 * 
	 * @param dictCode
	 * @return
	 */
	public SysDataDict getDictListByDictCode(String dictCode);

	/**
	 * 查询所有数据字典
	 * @return
	 */
	public List<SysDataDict> getAllDictList();

	 

}
