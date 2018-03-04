package com.hy.sys.service;

import java.util.ArrayList;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysMenu;

public interface SysMenuService extends BasicService<SysMenu> {


	public ArrayList<SysMenu> getMenuTree();
	public SysMenu findByName(String name);
	 
}
