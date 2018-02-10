package com.hy.sys.service;

import java.util.List;
import java.util.Map;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.utils.PageInfo;

public interface SysMenuService extends BasicService<SysMenu> {
	public PageInfo<SysMenu> getList(Map<String, Object> params, SysMenu entity, int pageNo, int pageSize);

	public List<SysMenu> findMenuByUserId(String userid);
	
	 
}
