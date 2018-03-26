package com.hy.sys.dao;

import java.util.Map;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysDataDictItem;
import com.hy.sys.utils.PageInfo;

public interface SysDictItemDao extends BasicDao<SysDataDictItem> {

	/**
	 * 获取键值项
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<SysDataDictItem> getPageList(Map<String, Object> params, SysDataDictItem entity, int pageNo, int pageSize);

	/**
	 * 查询是否存在同名的字典项
	 * @param itemName
	 * @return
	 */
	public SysDataDictItem getDictItemName(String itemName);

	/**
	 *  查询当前字典是否存在键值
	 * @param dict_code
	 * @return
	 */
	public SysDataDictItem getDictItemByDictId(String dict_code);

}
