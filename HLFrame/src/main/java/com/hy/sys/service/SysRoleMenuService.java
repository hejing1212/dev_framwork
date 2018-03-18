package com.hy.sys.service;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysRoleMenu;

public interface SysRoleMenuService extends BasicService<SysRoleMenu>{
	/**
	 * 删除角色对应的菜单
	 * @param roleId
	 */
	public void deleteRoleMenu(String roleId);
}
