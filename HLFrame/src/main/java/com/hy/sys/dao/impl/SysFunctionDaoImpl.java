package com.hy.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysFunctionDao;
import com.hy.sys.entity.SysFunction;

@Repository("sysFunctionDao")
public class SysFunctionDaoImpl extends BasicDaoImpl<SysFunction> implements SysFunctionDao {

	/**
	 * 获取菜单对应的功能
	 * 
	 * @param menuId
	 * @return
	 */
	@Override
	public ArrayList<SysFunction> getMenuFunByMenuid(String menuId) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysFunction ");
		sql.append(" WHERE 1=1 ");
		if (menuId != "") {
			sql.append(" AND (menuid = ?)");
			values.add(menuId);
		}
		sql.append(" ORDER BY create_date DESC");
		ArrayList<SysFunction> list =(ArrayList<SysFunction>)this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public Class<SysFunction> getEntityClass() {
		// TODO Auto-generated method stub
		return  SysFunction.class;
	}

}
