package com.hy.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hy.sys.core.service.BasicService;
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
	public ArrayList<SysMenu> getMenuList(Map<String, Object> params, SysMenu entity); 
	
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
	
	
	/**
	 * 查询该菜单下的子工菜单数量
	 * @param menuid
	 * @return
	 */
	public long getChildCountByMenuid(String menuid);
	/**
	 * 删除菜单
	 * @param menuid
	 */
	public void deleteMenu(String menuid);
	
	/**
	 * 查询所有可用的菜单
	 * @return
	 */
	public List<SysMenu> getALLMenuList();
	
	/**
	 * 保存用户菜单权限
	 * @param roleId
	 * @param auths
	 * @return
	 */
	public Map<String, Object> roleMenuAuthSave(String roleId, String auths);
	/**
	 * 设置同一个父菜单下的所有菜单只能有一个是默认展开的
	 * @param parentId
	 */
	public void updataMenuCurrent(String parentId);
}
