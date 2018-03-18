package com.hy.sys.dao;

import java.util.List;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysRoleMenuFun;

public interface SysRoleMenuFunDao extends BasicDao<SysRoleMenuFun>{

	/**
	 * 获取角色对应的功能 
	 * @param roleId
	 * @return
	 */
	public List<SysRoleMenuFun> getFunByRoleid(String roleId);

	/**
	 * 删除某角色的所有授权，根据角色ID
	 * @param roleId
	 */
	public void deleteRoleAuthFun(String roleId);

}
