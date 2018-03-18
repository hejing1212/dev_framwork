package com.hy.sys.dao;

import java.util.List;

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
	public  void deleteMenuFun(String[] funids);

	/**
	 * 获取菜单对应的功能数量
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
