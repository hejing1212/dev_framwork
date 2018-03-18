package com.hy.sys.dao;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysRoleMenu;

public interface SysRoleMenuDao  extends BasicDao<SysRoleMenu>{

	 
	/**
	 * 删除角色对应的菜单
	 * @param roleId
	 */
	public void deleteRoleMenu(String roleId);
}
