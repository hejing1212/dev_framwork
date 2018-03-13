package com.hy.sys.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysRoleDao;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUser;
import com.hy.sys.service.SysRoleService;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.logs.DeleteLog;
import com.hy.sys.utils.logs.LogUtil;
import com.hy.sys.utils.logs.SaveLog;
import com.hy.sys.utils.logs.SysLogOperationType;
import com.hy.sys.utils.logs.UpdateLog;

@SaveLog(operationName = "添加用户信息", operationType = SysLogOperationType.Add)
@UpdateLog(operationName = "修改用户信息", operationType = SysLogOperationType.Update)
@DeleteLog(operationName = "删除用户信息", operationType = SysLogOperationType.Delete)
@Service("sysRoleService")
public class SysRoleServiceImpl extends BasicServiceImpl<SysRole> implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Override
	public PageInfo<SysRole> getPageList(Map<String, Object> params, SysRole entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<SysRole> getList(Map<String, Object> params, SysRole entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM sys_role   ");
		sql.append(" WHERE 1=1 ");

		if (params.containsKey("queryKey") && params.get("queryKey") != null && !"".equals(params.get("queryKey"))) {
			sql.append(" AND ( name like ?)");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");			
		}

		sql.append(" ORDER BY create_date DESC");
		return (PageInfo<SysRole>) getBasicDao().findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SysRole.class);
	}

	 
	/*
	 * (non-Javadoc)
	 * 
	 * @see  
	 */
	@Override
	public List<SysRole> findListByUserId(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

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
		List<SysRole> list = getBasicDao().findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SysRole) list.get(0);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.hy.sys.core.service.impl.BasicServiceImpl#getBasicDao()
	 */
	@Override
	protected BasicDao<SysRole> getBasicDao() {
		// TODO Auto-generated method stub
		return sysRoleDao;
	}
	
	/**
	 * 根据用户ID查询用户对应角色列表
	 */
	@SuppressWarnings("unchecked")
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
		return getBasicDao().findPageInfoByQuery(pageNo, pageSize, hql.toString(),hqlCount, values.toArray());
	}
	
/*
 * (non-Javadoc)
 * @se删除用户对应的角色
 */
	@Override
	public void deleteUserRole(String userId, String[] roleIds) {
		String sql = "DELETE FROM sys_user_role WHERE user_id = ? AND role_id = ? ";		
		if ((roleIds != null) && (roleIds.length > 0)) {
			sysRoleDao.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {  
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
}
