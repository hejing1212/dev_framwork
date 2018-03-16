package com.hy.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysMenuDao;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.SysUser;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.logs.LogUtil;

@Repository("sysMenuDao")
public class SysMenuDaoImpl extends BasicDaoImpl<SysMenu> implements SysMenuDao {

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
		List<SysMenu> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SysMenu) list.get(0);
		} else {
			return null;
		}
	}
	/**
	 * 获取所有显示菜单内容
	 * @return
	 */
	@Override
	public List<SysMenu> getAllList(){
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysMenu ");
		sql.append(" WHERE 1=1 ");
				 
		sql.append(" AND (del_flag = ?)"); //只查询 isshow=0的
		values.add(0);
		 		
		sql.append(" ORDER BY create_date DESC");
		List<SysMenu> list = this.findByHql(sql.toString(), values.toArray());

		return list;		
	}
	
	/**
	 * 查询该菜单下的子工菜单数量
	 * 
	 * @param menuid
	 * @return
	 */
	@Override
	public long getChildCountByMenuid(String menuid) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append("SELECT count(*) FROM SysMenu ");
		sql.append(" WHERE 1=1 ");
		if (menuid != "") {
			sql.append(" AND (parent_id = ?)");
			values.add(menuid);
		}
		sql.append(" ORDER BY create_date DESC");
		return this.getTotalCount(sql.toString(), values.toArray());

	}

	/**
	 * 删除菜单
	 * 
	 * @param funids
	 */
	@Override
	public void deleteMenu(String menuid) {
		String sql=" DELETE FROM sys_menu WHERE menuid = '"+menuid+"' ";	 
	    this.getJdbcTemplate().execute(sql.toString());
	    
		SysUser user = UserUtils.getUser();
		LogUtil.info("菜单删除：被删除菜单ID," + menuid + "删除人：" + user.getUserid());

	}

}
