package com.hy.sys.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysRoleDao;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUser;

@Repository("SysRoleDao")
public class SysRoleDaoImpl extends BasicDaoImpl<SysRole> implements SysRoleDao{

	@Override
	public void save(SysRole entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveBatch(Collection<SysRole> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SysRole entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBatch(Collection<SysRole> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SysRole get(String guid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<SysRole> getEntityClass() {
		// TODO Auto-generated method stub
		return SysRole.class;
	}

}
