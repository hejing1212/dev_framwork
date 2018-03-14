package com.hy.sys.service;

import java.util.ArrayList;
import java.util.List;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.TreeNode;

public interface SysMenuService extends BasicService<SysMenu> {


	/**
	 * 下接树
	 * @return
	 */
	public ArrayList<TreeNode> getMenuTree(); 
	/**
	 * 列表展示
	 * @return
	 */
	public ArrayList<SysMenu> getMenuList(); 
	
	/**
	 * 根据菜单名称查询同名菜单是否存在
	 * @param name
	 * @return
	 */
	public SysMenu findByName(String name);
	/**
	 * 根据用户ID获取有权限的菜单
	 * @param userid
	 * @return
	 */
	public List<SysMenu> findMenuByUserId(String userid);
	
	
	/**
	 * 根据菜单的子父级关系生成上下级关系的数据
	 * @param list
	 * @param menuid
	 * @param li
	 * @return
	 */
	ArrayList<SysMenu> CreateMenuList(List<SysMenu> list, String menuid, ArrayList<SysMenu> li);
}
