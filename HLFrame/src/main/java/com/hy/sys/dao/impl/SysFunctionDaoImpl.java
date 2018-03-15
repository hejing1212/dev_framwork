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

	/**
	 * 删除菜单对应的功能
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
	            public void setValues(PreparedStatement ps, int i)throws SQLException { 
	                ps.setString(2, funids[i]);  	               
	            }  
	        });  
			SysUser user = UserUtils.getUser();
			for(String funis:funids){
				LogUtil.info("功能删除：被删除功能ID,"+funis+"删除人："+user.getUserid());	               
			}
		}
	}
}
