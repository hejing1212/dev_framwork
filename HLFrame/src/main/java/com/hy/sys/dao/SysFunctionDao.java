package com.hy.sys.dao;

import java.util.ArrayList;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysFunction;

public interface SysFunctionDao extends BasicDao<SysFunction>{

	/**
	 * 查询菜单对应的功能
	 * @param menuId
	 * @return
	 */
	public	ArrayList<SysFunction> getMenuFunByMenuid(String menuId);
}
