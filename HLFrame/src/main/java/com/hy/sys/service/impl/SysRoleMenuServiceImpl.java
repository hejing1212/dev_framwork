package com.hy.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysRoleMenuDao;
import com.hy.sys.entity.SysRoleMenu;
import com.hy.sys.service.SysRoleMenuService;
import com.hy.sys.utils.PageInfo;

@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BasicServiceImpl<SysRoleMenu> implements SysRoleMenuService {

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Override
	public PageInfo<SysRoleMenu> getPageList(Map<String, Object> params, SysRoleMenu entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 删除角色对应的菜单
	 */
	@Override
	public void deleteRoleMenu(String roleId) {
		sysRoleMenuDao.deleteRoleMenu(roleId);
		
	}

	@Override
	protected BasicDao<SysRoleMenu> getBasicDao() {
		// TODO Auto-generated method stub
		return this.sysRoleMenuDao;
	}

}
