package com.hy.sys.service;

import java.util.ArrayList;
import java.util.List;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.TreeNode;

public interface SysMenuService extends BasicService<SysMenu> {


	public ArrayList<TreeNode> getMenuTree();//下接树
	public ArrayList<SysMenu> getMenuList(); //列表展示
	
	public SysMenu findByName(String name);
	public List<SysMenu> findMenuByUserId(String userid);
}
