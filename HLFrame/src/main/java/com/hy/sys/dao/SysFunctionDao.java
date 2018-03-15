package com.hy.sys.dao;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.utils.PageInfo;

public interface SysFunctionDao extends BasicDao<SysFunction>{

	/**
	 * 查询菜单对应的功能
	 * @param menuId
	 * @return
	 */
	public	PageInfo<SysFunction> getMenuFunByMenuid(int pageNo, int pageSize,String menuId);

	/**
	 * 删除菜单对应的功能
	 * @param funids
	 */
	void deleteMenuFun(String[] funids);
}
