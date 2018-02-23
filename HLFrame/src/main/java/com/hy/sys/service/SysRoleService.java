package com.hy.sys.service;

import java.util.List;
import java.util.Map;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;

public interface SysRoleService extends BasicService<SysRole>{

	public PageInfo<SysRole> getList(Map<String, Object> params, SysRole entity, int pageNo, int pageSize);
	
	public List<SysRole> findListByUserId(String userid);

	/**
	 * @param name
	 * @return
	 */
	public SysRole getRoleName(String name);
}
