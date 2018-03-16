package com.hy.sys.dao;



import java.util.List;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysMenu;



public interface SysMenuDao extends BasicDao<SysMenu> {

	/**
	 * 查询菜单是否添加过，避免重复添加
	 * @param menuname
	 * @return
	 */
	public	SysMenu findByName(String menuname);

	/**
	 * 查询该菜单下的子工菜单数量
	 * @param menuid
	 * @return
	 */
	public long getChildCountByMenuid(String menuid);

	/**
	 *  删除菜单 
	 * @param menuid
	 */
	public void deleteMenu(String menuid);

    /**
     * 获取所有显示菜单内容
     * @return
     */
	public List<SysMenu> getAllList();

	

}
