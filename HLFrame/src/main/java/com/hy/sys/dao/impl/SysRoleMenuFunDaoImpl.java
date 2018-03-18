package com.hy.sys.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysRoleMenuFunDao;
import com.hy.sys.entity.SysRoleMenuFun;
import com.hy.sys.entity.SysUser;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.logs.LogUtil;

@Repository("sysRoleMenuFunDao")
public class SysRoleMenuFunDaoImpl extends BasicDaoImpl<SysRoleMenuFun> implements SysRoleMenuFunDao {

	@Override
	public Class<SysRoleMenuFun> getEntityClass() {
		// TODO Auto-generated method stub
		return SysRoleMenuFun.class;
	}

	/**
	 * 获取角色对应的功能
	 * 
	 * @param menuId
	 * @return
	 */
	@Override
	public List<SysRoleMenuFun> getFunByRoleid(String roleId) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysRoleMenuFun ");
		sql.append(" WHERE 1=1 ");

		if (roleId != "") {
			sql.append(" AND (role_id = ?)");
			values.add(roleId);
		}
		return this.findByHql(sql.toString(), values.toArray());
	}

	/**
	 * 删除某角色的所有授权，根据角色ID
	 * 
	 * @param funids
	 */
	@Override
	public void deleteRoleAuthFun(String roleId) {
		String sql = "DELETE FROM sys_role_menu_fun WHERE role_id = :roleId";

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		this.getNameJdbcTemplate().update(sql, paramMap);
		SysUser user = UserUtils.getUser();

		LogUtil.info("角色权限被重置，角色ID：," + roleId + "操作人：" + user.getUserid());

	}

}
