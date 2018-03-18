package com.hy.sys.service;

import java.util.List;
import java.util.Map;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysRoleMenuFun;

public interface SysRoleMenuFunService extends BasicService<SysRoleMenuFun> {

	/**
	 * 返回角色拥有的菜单 功能权限
	 * @param roleId
	 * @return
	 */
	public List<SysRoleMenuFun> getRoleMenuFun(String roleId);

	/**
	 * 保存用户菜单权限
	 * @param roleId
	 * @param auths
	 * @return
	 */
	public Map<String, Object> roleAuthSave(String roleId, String auths);

	
	

	 

}
