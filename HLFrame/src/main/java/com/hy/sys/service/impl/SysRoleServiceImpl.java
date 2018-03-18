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
		 return sysRoleDao.getList(params, entity, pageNo, pageSize);
	}

	 
	/*
	 * (non-Javadoc)
	 * 
	 * @see  
	 */
	@Override
	public List<SysRole> findListByUserId(String userId) {
		List<SysRole> list=sysRoleDao.getRoleListByUserid(userId);
		return list;
	}

	@Override
	public SysRole getRoleName(String rolename) {
		return  sysRoleDao.getRoleName(rolename);
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
		return sysRoleDao.getPageListByUser(userId, pageNo, pageSize);
	}
	
/*
 * (non-Javadoc)
 * @se删除用户对应的角色
 */
	@Override
	public void deleteUserRole(String userId, String[] roleIds) {
		sysRoleDao.deleteUserRole(userId, roleIds);
	}
}
