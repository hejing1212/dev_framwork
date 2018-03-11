package com.hy.sys.dao;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysUserRole;

public interface SysUserRoleDao extends BasicDao<SysUserRole> {
	public void deleteRoleByUserId(String userid);
	 
}
