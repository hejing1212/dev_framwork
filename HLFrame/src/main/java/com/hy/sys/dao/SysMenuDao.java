package com.hy.sys.dao;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysMenu;

public interface SysMenuDao extends BasicDao<SysMenu> {

	/**
	 * 查询菜单是否添加过，避免重复添加
	 * @param menuname
	 * @return
	 */
	public	SysMenu findByName(String menuname);

	

}
