package com.hy.sys.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysRoleMenuDao;
import com.hy.sys.entity.SysRoleMenu;
import com.hy.sys.entity.SysUser;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.logs.LogUtil;

@Repository("sysRoleMenuDao")
public class SysRoleMenuDaoImpl extends BasicDaoImpl<SysRoleMenu> implements SysRoleMenuDao {

	/**
	 * 删除菜单
	 * 
	 * @param funids
	 */
	@Override
	public void deleteRoleMenu(String roleId) {
		if (roleId != null && roleId != "") {
			String sql = " DELETE FROM sys_role_menu WHERE role_id =:role_id";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("role_id", roleId);
			this.getNameJdbcTemplate().update(sql, paramMap);

		}
		SysUser user = UserUtils.getUser();
		LogUtil.info("删除角色对应的菜单权限：角色ID," + roleId + "删除人：" + user.getUserid());
	}

	@Override
	public Class<SysRoleMenu> getEntityClass() {
		// TODO Auto-generated method stub
		return SysRoleMenu.class;
	}

}
