package com.hy.sys.service;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.utils.PageInfo;

public interface SysFunctionService extends BasicService<SysFunction> {

	/**
	 * 获取菜单对应的功能
	 * @param menuId
	 * @return
	 */
	public PageInfo<SysFunction> getFunListByMenuid(int pageNo, int pageSize,String menuId);

	/**
	 * 删除菜单功能
	 * @param funids
	 */
	public void deleteMenuFun(String[] funids);
}
