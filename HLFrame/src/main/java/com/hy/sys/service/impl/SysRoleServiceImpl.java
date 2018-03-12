package com.hy.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysRoleDao;
import com.hy.sys.entity.SysRole;
import com.hy.sys.service.SysRoleService;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.logs.DeleteLog;
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
		//hql.append("SELECT a.* ");
		hql.append(" FROM SysUserRole  ");
		hql.append(" WHERE 1=1 ");
		
		String hqlCount ="select count(*)  FROM SysUserRole WHERE 1=1";
				
		if (userId != "") {
			hql.append(" AND ( user_id = ?)");
			values.add(userId);
		}
		//hql.append(" ORDER BY create_date DESC");
		return (PageInfo<SysRole>)getBasicDao().findPageInfoByQuery(pageNo, pageSize, hql.toString(),hqlCount, new Object[] {userId});
	}
}
