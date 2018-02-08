package com.hy.sys.service;

import java.util.List;
import java.util.Map;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;

public interface SysMenuService extends BasicService<SysMenu> {
	public PageInfo<SysRole> getList(Map<String, Object> params, SysRole entity, int pageNo, int pageSize);
	
	public List<SysMenu> findMenuByUserId(String userId);
}