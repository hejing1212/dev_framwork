package com.hy.sys.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysRoleDao;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUser;

@Repository("sysRoleDao")
public class SysRoleDaoImpl extends BasicDaoImpl<SysRole> implements SysRoleDao{
	@Override
	public Class<SysRole> getEntityClass() {
		// TODO Auto-generated method stub
		return SysRole.class;
	}

}
