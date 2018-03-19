package com.hy.sys.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysFunctionDao;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.entity.SysUser;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.logs.LogUtil;

@Repository("sysFunctionDao")
public class SysFunctionDaoImpl extends BasicDaoImpl<SysFunction> implements SysFunctionDao {

	/**
	 * 获取菜单对应的功能
	 * 
	 * @param menuId
	 * @return
	 */
	@Override
	public PageInfo<SysFunction> getMenuFunByMenuid(int pageNo, int pageSize,String menuId) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysFunction ");
		sql.append(" WHERE 1=1 ");
		
		String hqlCount="SELECT count(*) FROM SysFunction WHERE menu_id = ?";
		
		if (menuId != "") {
			sql.append(" AND (menu_id = ?)");
			values.add(menuId);
		}
		sql.append(" ORDER BY create_date DESC");
		PageInfo<SysFunction> page = this.findPageInfoByQuery(pageNo,pageSize,sql.toString(),hqlCount, values.toArray());
		return page;
	}

	/**
	 * 获取菜单对应的功能数量
	 * @param menuId
	 * @return
	 */
	@Override
	public long getMenuCountByMenuid(String menuId) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT count(*) FROM SysFunction ");
		sql.append(" WHERE 1=1 ");

		if (menuId != "") {
			sql.append(" AND (menu_id = ?)");
			values.add(menuId);
		}
		sql.append(" ORDER BY create_date DESC");
		long count= this.getTotalCount(sql.toString(), values.toArray());
		return count;
	}
	
	
	
	
	@Override
	public Class<SysFunction> getEntityClass() {
		// TODO Auto-generated method stub
		return SysFunction.class;
	}

	/**
	 * 删除菜单对应的功能
	 * 
	 * @param funids
	 */
	@Override
	public void deleteMenuFun(String[] funids) {
		String sql = "DELETE FROM sys_function WHERE funid = ? ";
		if ((funids != null) && (funids.length > 0)) {
			this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
				public int getBatchSize() {
					return funids.length;
				}

				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, funids[i]);
				}
			});
			SysUser user = UserUtils.getUser();
			for (String funis : funids) {
				LogUtil.info("功能删除：被删除功能ID," + funis + "删除人：" + user.getUserid());
			}
		}
	}
	
	/**
	 * 根据用户ID查询当前用户具体的操作功能列表
	 * @param userId
	 * @return
	 */
	@Override
	public List<SysFunction> findFunctionByUserId(String userId){
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append("SELECT a ");
		sql.append(" FROM SysFunction a, SysUserRole b,SysRoleMenuFun c ");
		sql.append(" WHERE a.funid=c.fun_id and b.role_id=c.role_id ");
				 
		sql.append(" AND (b.user_id = ?)");  
		values.add(userId);
		 		
		sql.append(" ORDER BY a.sort DESC");
		List<SysFunction> list = this.findByHql(sql.toString(), values.toArray());

		return list;		
	}
}
