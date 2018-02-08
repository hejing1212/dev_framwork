package com.hy.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysMenuDao;
import com.hy.sys.entity.SysMenu;

@Repository("SysMenuDao")
public class SysMenuDaoImpl extends BasicDaoImpl<SysMenu> implements SysMenuDao{

	@Override
	public Class<SysMenu> getEntityClass() {
		// TODO Auto-generated method stub
		return SysMenu.class;
	}

}
