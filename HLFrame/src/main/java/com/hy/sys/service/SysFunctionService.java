package com.hy.sys.service;

import java.util.List;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.entity.SysRoleMenuFun;
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

	/**
	 * 能记录数量获取菜单对应的功
	 * @param menuId
	 * @return
	 */
	public long getMenuCountByMenuid(String menuId);
	
	/**
	 * 根据用户ID查询当前用户具体的操作功能列表
	 * @param userId
	 * @return
	 */
	public List<SysFunction> findFunctionByUserId(String userId);
}
