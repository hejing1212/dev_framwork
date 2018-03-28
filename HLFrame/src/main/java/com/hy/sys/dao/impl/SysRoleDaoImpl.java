package com.hy.sys.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysRoleDao;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUser;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.logs.LogUtil;

@Repository("sysRoleDao")
public class SysRoleDaoImpl extends BasicDaoImpl<SysRole> implements SysRoleDao{
	@Override
	public Class<SysRole> getEntityClass() {
		// TODO Auto-generated method stub
		return SysRole.class;
	}
	
	/**
	 * 删除用户对应的角色
	 * @param userId
	 * @param roleIds
	 */
	@Override
	public void deleteUserRole(String userId, String[] roleIds) {
		String sql = "DELETE FROM sys_user_role WHERE user_id = ? AND role_id = ? ";		
		if ((roleIds != null) && (roleIds.length > 0)) {
			this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {  
	            public int getBatchSize() {  
	                return roleIds.length; 
	            }  
	            public void setValues(PreparedStatement ps, int i)throws SQLException { 
	                ps.setString(1, userId);  
	                ps.setString(2, roleIds[i]);  	               
	            }  
	        });  
			SysUser user = UserUtils.getUser();
			for(String role:roleIds){
				LogUtil.info("角色删除：被删除人, "+userId+",删除角色ID,"+role+"删除人："+user.getUserid());	               
			}
		}
	}
	
	/**
	 * 根据用户ID查询用户对应角色列表
	 */
	@Override
	public PageInfo<SysRole> getPageListByUser(String userId, int pageNo, int pageSize) {
		StringBuffer hql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		hql.append("SELECT a ");
		hql.append(" FROM SysRole a, SysUserRole b ");
		hql.append(" WHERE a.roleid=b.role_id ");
		
		String hqlCount ="select count(*)  FROM SysUserRole WHERE  user_id = ? ";
				
		if (userId != "") {
			hql.append(" AND ( b.user_id = ?)");
			values.add(userId);
		}
		hql.append(" ORDER BY create_date DESC");
		return this.findPageInfoByQuery(pageNo, pageSize, hql.toString(),hqlCount, values.toArray());
	}
	
	
	/**
	 * 查询用户名是否存在
	 * @param rolename
	 * @return
	 */
	@Override
	public SysRole getRoleName(String rolename) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysRole ");
		sql.append(" WHERE 1=1 ");

		if (rolename != "") {
			sql.append(" AND ( name = ?)");
			values.add(rolename);
		}
		sql.append(" ORDER BY create_date DESC");
		List<SysRole> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SysRole) list.get(0);
		} else {
			return null;
		}
	}

	
	@Override
	public PageInfo<SysRole> getList(Map<String, Object> params, SysRole entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM sys_role   ");
		sql.append(" WHERE 1=1 ");

		if (StringTools.mapGetKeyIsEmpty(params, "queryKey")) {
			sql.append(" AND ( name like ? OR code like ?)");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
			values.add("%" + key + "%");
		}

		sql.append(" ORDER BY create_date DESC");
		return (PageInfo<SysRole>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SysRole.class);
	}
	
	/**
	 * 根据用户了ID查询用户对应的角色列表
	 * @param rolename
	 * @return
	 */
	@Override
	public List<SysRole> getRoleListByUserid(String userId) {
		StringBuffer hql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		hql.append("SELECT a ");
		hql.append(" FROM SysRole a, SysUserRole b ");
		hql.append(" WHERE a.roleid=b.role_id ");
				
		if (userId != "") {
			hql.append(" AND ( b.user_id = ?)");
			values.add(userId);
		}
		hql.append(" ORDER BY create_date DESC");
		return this.findByHql(hql.toString(), values.toArray());
	}
}
