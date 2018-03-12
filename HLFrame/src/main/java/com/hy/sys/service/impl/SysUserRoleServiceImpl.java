package com.hy.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysUserRoleDao;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUserRole;
import com.hy.sys.service.SysUserRoleService;
import com.hy.sys.utils.PageInfo;
@Service("sysRoleUserService")
public class SysUserRoleServiceImpl extends BasicServiceImpl<SysUserRole> implements SysUserRoleService{

	@Autowired
	private SysUserRoleDao sysRoleUserDao;
	@Override
	public PageInfo<SysUserRole> getPageList(Map<String, Object> params, SysUserRole entity, int pageNo, int pageSize) {
			return null; 
	}

	
	@Override
	protected BasicDao<SysUserRole> getBasicDao() {
		// TODO Auto-generated method stub
		return this.sysRoleUserDao;
	}

	@Override
	public void deleteRoleByUserId(String userId) {
		sysRoleUserDao.deleteRoleByUserId(userId);
		
	}

}
