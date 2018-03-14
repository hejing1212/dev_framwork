package com.hy.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysMenuDao;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.entity.SysMenu;

@Repository("sysMenuDao")
public class SysMenuDaoImpl extends BasicDaoImpl<SysMenu> implements SysMenuDao{

	@Override
	public Class<SysMenu> getEntityClass() {
		// TODO Auto-generated method stub
		return SysMenu.class;
	}
	
	@Override
	public SysMenu findByName(String menuname) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysMenu ");
		sql.append(" WHERE 1=1 ");
		if (menuname != "") {
			sql.append(" AND (name = ?)");
			values.add(menuname);
		}
		sql.append(" ORDER BY create_date DESC");
		List<SysMenu> list =this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SysMenu) list.get(0);
		} else {
			return null;
		}
	}
	
	

}
