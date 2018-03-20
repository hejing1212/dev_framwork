package com.hy.sys.dao;



import java.util.List;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysMenu;


/**
 * 
 * @author hejing
 *
 */
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

	/**
	 * 根据用户ID获取用户拥有权限 的功能菜单
	 * @param userId
	 * @return
	 */
	public List<SysMenu> findMenuByUserId(String userId);

	/**
	 * 设置同一个父菜单下的所有菜单只能有一个是默认展开的
	 * @param parentId
	 */
	public void updataMenuCurrent(String parentId);

	

}
