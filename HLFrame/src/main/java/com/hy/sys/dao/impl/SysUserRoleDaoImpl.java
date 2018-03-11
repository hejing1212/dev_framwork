package com.hy.sys.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysUserDao;
import com.hy.sys.dao.SysUserRoleDao;
import com.hy.sys.entity.SysUserRole;
@Repository("sysRoleUserDao")
public class SysUserRoleDaoImpl extends BasicDaoImpl<SysUserRole> implements SysUserRoleDao {

	
	@Override
	public Class<SysUserRole> getEntityClass() {
		// TODO Auto-generated method stub
		return SysUserRole.class;
	}

	@Override
	public void deleteRoleByUserId(String userid) {
		String sql = "DELETE FROM sys_user_role WHERE user_id = :userid";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		this.getNameJdbcTemplate().update(sql, paramMap);
		
	}

}
