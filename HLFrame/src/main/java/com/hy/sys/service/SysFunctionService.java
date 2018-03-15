package com.hy.sys.service;

import java.util.ArrayList;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysFunction;

public interface SysFunctionService extends BasicService<SysFunction> {

	/**
	 * 获取菜单对应的功能
	 * @param menuId
	 * @return
	 */
	public ArrayList<SysFunction> getFunListByMenuid(String menuId);

	/**
	 * 删除菜单功能
	 * @param funids
	 */
	public void deleteMenuFun(String[] funids);
}
