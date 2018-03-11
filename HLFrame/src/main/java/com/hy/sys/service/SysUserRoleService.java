package com.hy.sys.service;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysUserRole;
import com.hy.sys.utils.PageInfo;

public interface SysUserRoleService extends BasicService<SysUserRole> {
	public void deleteRoleByUserId(String userId);

	public PageInfo<SysUserRole> getPageListByUser(String userId, int pageNo, int pageSize);
}
