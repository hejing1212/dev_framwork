package com.hy.sys.service;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUserRole;
import com.hy.sys.utils.PageInfo;

public interface SysUserRoleService extends BasicService<SysUserRole> {
	public void deleteRoleByUserId(String userId);

 
}
