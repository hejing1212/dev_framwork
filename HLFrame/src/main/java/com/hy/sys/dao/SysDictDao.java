package com.hy.sys.dao;

import java.util.List;
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

	
    /**
     * 根据数据字典值查询数据字典及键值
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
